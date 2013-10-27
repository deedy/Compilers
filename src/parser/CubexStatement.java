import java.util.List;
import java.util.ArrayList;

public abstract class CubexStatement extends CubexNode {
    public abstract Triple<SymbolTable, Boolean, CubexType> typeCheck(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt);
    public abstract HNode accept(HVisitor v);
}

class CubexBlock extends CubexStatement {
    private List<CubexStatement> stmts;
    public CubexBlock(List<CubexStatement> s) {
        stmts = s;
    }

    public Triple<SymbolTable, Boolean, CubexType> typeCheck(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        // check that any contained statement is does return
        CubexType foldType = new Nothing();
        boolean doesReturn = false;
        SymbolTable currOutgoing = mutableSt;
        for(CubexStatement s : stmts) {
            Triple<SymbolTable, Boolean, CubexType> imm = s.typeCheck(cc, kc, fc, st, currOutgoing);
            if(imm.getMiddle().booleanValue()) {
                doesReturn = true;
            }
            // find the common return type (always)
            foldType = CubexTC.join(cc, kc, foldType, imm.getRight());
            currOutgoing = imm.getLeft();
        }
        return new Triple<SymbolTable, Boolean, CubexType>(currOutgoing, new Boolean(doesReturn), foldType);
    }

    public String toString() {
        String s = ListPrinter.listToString(stmts, " ");
        return String.format("{ %s}", ListPrinter.nullify(s));
    }

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }
}

class CubexAssign extends CubexStatement {
    private CubexVName name;
    private CubexExpression expr;
    public CubexAssign(CubexVName n, CubexExpression e) {
        name = n;
        expr = e;
    }

    public Triple<SymbolTable, Boolean, CubexType> typeCheck(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType curType = st.get(name);
        // No name hiding!
        if (curType != null) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS BOUND TO %s", name, curType));
        }

        SymbolTable mergedSt = st.merge(mutableSt);
        CubexType type = expr.getType(cc, kc, fc, mergedSt);

        // return a mutable ST with the VName bound/rebound
        SymbolTable retTable = mutableSt.set(name, type);
        // assignments never return, so return false, nothing
        return new Triple<SymbolTable, Boolean, CubexType>(retTable, new Boolean(false), new Nothing());
    }

    public String toString() {
        String n = name.toString();
        String e = expr.toString();
        return String.format("%s := %s ;", n, e);
    }

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }
}

class CubexConditional extends CubexStatement {
    private CubexExpression expr;
    private CubexStatement stmt1;
    private CubexStatement stmt2;
    public CubexConditional(CubexExpression e, CubexStatement  s1, CubexStatement s2) {
        expr = e;
        stmt1 = s1;
        stmt2 = s2;
    }

    public Triple<SymbolTable, Boolean, CubexType> typeCheck(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        boolean isBoolean = CubexTC.subType(cc, kc, exprType, new CubexCType("Boolean"));

        if (!isBoolean) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT A BOOLEAN", expr.toString()));
        }

        // get the return types of both sides
        Triple<SymbolTable, Boolean, CubexType> ret1 = stmt1.typeCheck(cc, kc, fc, st, mutableSt);
        Triple<SymbolTable, Boolean, CubexType> ret2 = stmt2.typeCheck(cc, kc, fc, st, mutableSt);

        SymbolTable s1 = ret1.getLeft();
        SymbolTable s2 = ret2.getLeft();

        boolean b1 = ret1.getMiddle().booleanValue();
        boolean b2 = ret2.getMiddle().booleanValue();

        CubexType t1 = ret1.getRight();
        CubexType t2 = ret2.getRight();

        CubexType commonType = CubexTC.join(cc, kc, t1, t2);

        SymbolTable merged = s1.intersection(s2, cc, kc);
        return new Triple<SymbolTable, Boolean, CubexType>(merged, new Boolean(b1 && b2), commonType);
    }

    public String toString() {
        String e = expr.toString();
        String s1 = stmt1.toString();
        String s2 = stmt2.toString();
        return String.format("if ( %s ) %s else %s", e, s1, s2);
    }

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }
}

class CubexWhileLoop extends CubexStatement {
    private CubexExpression expr;
    private CubexStatement stmt;
    public CubexWhileLoop(CubexExpression e, CubexStatement s) {
        expr = e;
        stmt = s;
    }


    public Triple<SymbolTable, Boolean, CubexType> typeCheck(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        boolean isBoolean = CubexTC.subType(cc, kc, exprType, new CubexCType("Boolean"));

        if (!isBoolean) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT A BOOLEAN", expr.toString()));
        }

        // check that the statement type checks
        Triple<SymbolTable, Boolean, CubexType> imm = stmt.typeCheck(cc, kc, fc, st, mutableSt);

        // while returns same type as statement but not guarunteed to return
        return new Triple<SymbolTable, Boolean, CubexType>(mutableSt.intersection(imm.getLeft(), cc, kc), new Boolean(false), imm.getRight());
    }

    public String toString() {
        String e = expr.toString();
        String s = stmt.toString();
        return String.format("while ( %s ) %s", e, s);
    }

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }
}

class CubexForLoop extends CubexStatement {
    private CubexVName name;
    private CubexExpression expr;
    private CubexStatement stmt;
    public CubexForLoop(CubexVName n, CubexExpression e, CubexStatement  s) {
        name = n;
        expr = e;
        stmt = s;
    }

    public Triple<SymbolTable, Boolean, CubexType> typeCheck(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        // System.out.println(exprType);

        if (!exprType.isIterable(cc, kc)) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT AN ITERABLE", expr.toString()));
        }

        // get the iterable type by joining with iterable<nothing>
        ArrayList<CubexType> params = new ArrayList<CubexType>();
        params.add(new Nothing());
        CubexType testIter = new CubexCType(new CubexCName("Iterable"), params);
        // this cast should be safe
        // System.out.println(expr);
        // System.out.println(exprType);
        CubexCType foundIter = (CubexCType) CubexTC.join(cc, kc, testIter, exprType);
        SymbolTable tmp = mutableSt.set(name, foundIter.params.get(0));

        Triple<SymbolTable, Boolean, CubexType> ret = stmt.typeCheck(cc, kc, fc, st, tmp);
        // for returns same type as statement but not guarunteed to return
        return new Triple<SymbolTable, Boolean, CubexType>(mutableSt.intersection(ret.getLeft(), cc, kc), new Boolean(false), ret.getRight());
    }

    public String toString() {
        String n = name.toString();
        String e = expr.toString();
        String s = stmt.toString();
        return String.format("for ( %s in %s ) %s", n, e, s);   
    }

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }
}

class CubexReturn extends CubexStatement {
    private CubexExpression expr;
    public CubexReturn(CubexExpression e) {
        expr = e;
    }

    public Triple<SymbolTable, Boolean, CubexType> typeCheck(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        return new Triple<SymbolTable, Boolean, CubexType>(mutableSt, new Boolean(true), exprType);
    }

    public String toString() {
        String e = expr.toString();
        return String.format("return %s ;", e);   
    }

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }
}