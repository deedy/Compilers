import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.tuple.ImmutablePair;

public abstract class CubexStatement {
    public abstract SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt);

    // get the return type if possible. non-returning statements return Nothing
    public abstract ImmutablePair<Boolean, CubexType> getReturn(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt);
}

class CubexBlock extends CubexStatement {
    private List<CubexStatement> stmts;
    public CubexBlock(List<CubexStatement> s) {
        stmts = s;
    }

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        SymbolTable curOutgoing = mutableSt;
        for (CubexStatement s : stmts) {
            curOutgoing = s.getOutgoingTypes(cc, kc, fc, st, curOutgoing);
        }
        return curOutgoing;
    }

    public ImmutablePair<Boolean, CubexType> getReturn(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        // check that any contained statement is does return
        CubexType foldType = new Nothing();
        boolean doesReturn = false;
        for(CubexStatement s : stmts) {
            ImmutablePair<Boolean, CubexType> imm = s.getReturn(cc, kc, fc, st, mutableSt);
            if(imm.getLeft().booleanValue()) {
                doesReturn = true;
            }
            // find the common return type (always)
            foldType = CubexTC.join(cc, kc, foldType, imm.getRight());
        }
        return new ImmutablePair<Boolean, CubexType>(new Boolean(doesReturn), foldType);
    }

    public String toString() {
        String s = ListPrinter.listToString(stmts, " ");
        return String.format("{ %s}", ListPrinter.nullify(s));
    }
}

class CubexAssign extends CubexStatement {
    private CubexVName name;
    private CubexExpression expr;
    public CubexAssign(CubexVName n, CubexExpression e) {
        name = n;
        expr = e;
    }

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
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
        return mutableSt.set(name, type);
    }

    public ImmutablePair<Boolean, CubexType> getReturn(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        // assignments never return
        return new ImmutablePair<Boolean, CubexType>(new Boolean(false), new Nothing());
    }

    public String toString() {
        String n = name.toString();
        String e = expr.toString();
        return String.format("%s := %s ;", n, e);
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

    // // constructor with no else block
    // public CubexConditional(CubexExpression e, CubexStatement s1) {
    //     expr = e;
    //     stmt1 = s1;
    //     // else block is empty list
    //     stmt2 = new CubexBlock(new ArrayList<CubexStatement>());
    // }

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        boolean isBoolean = CubexTC.subType(cc, kc, exprType, new CubexCType("Boolean"));

        if (!isBoolean) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT A BOOLEAN", expr.toString()));
        }

        SymbolTable s1OutgoingTypes = stmt1.getOutgoingTypes(cc, kc, fc, st, mutableSt);
        SymbolTable s2OutgoingTypes = stmt2.getOutgoingTypes(cc, kc, fc, st, mutableSt);
        
        return s1OutgoingTypes.intersection(s2OutgoingTypes, cc, kc);
    }

    public ImmutablePair<Boolean, CubexType> getReturn(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        // get the return types of both sides
        ImmutablePair<Boolean, CubexType> ret1 = stmt1.getReturn(cc, kc, fc, st, mutableSt);
        ImmutablePair<Boolean, CubexType> ret2 = stmt2.getReturn(cc, kc, fc, st, mutableSt);

        boolean b1 = ret1.getLeft().booleanValue();
        boolean b2 = ret2.getLeft().booleanValue();

        CubexType t1 = ret1.getRight();
        CubexType t2 = ret2.getRight();

        CubexType commonType = CubexTC.join(cc, kc, t1, t2);
 
        return new ImmutablePair<Boolean, CubexType>(new Boolean(b1 && b2), commonType);
    }

    public String toString() {
        String e = expr.toString();
        String s1 = stmt1.toString();
        String s2 = stmt2.toString();
        return String.format("if ( %s ) %s else %s", e, s1, s2);
    }
}

class CubexWhileLoop extends CubexStatement {
    private CubexExpression expr;
    private CubexStatement stmt;
    public CubexWhileLoop(CubexExpression e, CubexStatement s) {
        expr = e;
        stmt = s;
    }

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        boolean isBoolean = CubexTC.subType(cc, kc, exprType, new CubexCType("Boolean"));

        if (!isBoolean) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT A BOOLEAN", expr.toString()));
        }

        // check that the statement type checks
        SymbolTable nst = stmt.getOutgoingTypes(cc, kc, fc, st, mutableSt);
        
        return mutableSt;
    }

    public ImmutablePair<Boolean, CubexType> getReturn(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        ImmutablePair<Boolean, CubexType> ret = stmt.getReturn(cc, kc, fc, st, mutableSt);
        // while returns same type as statement but not guarunteed to return
        return new ImmutablePair<Boolean, CubexType>(new Boolean(false), ret.getRight());
    }

    public String toString() {
        String e = expr.toString();
        String s = stmt.toString();
        return String.format("while ( %s ) %s", e, s);
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

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));

        if (!exprType.isIterable(cc, kc)) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT AN ITERABLE", expr.toString()));
        }

        // get the iterable type by joining with iterable<nothing>
        ArrayList<CubexType> params = new ArrayList<CubexType>();
        params.add(new Nothing());
        CubexType testIter = new CubexCType(new CubexCName("Iterable"), params);
        // this cast should be safe
        CubexCType foundIter = (CubexCType) CubexTC.join(cc, kc, testIter, exprType);

        SymbolTable tmp = mutableSt.set(name, foundIter.params.get(0));

        // check that the statement type checks
        SymbolTable nst = stmt.getOutgoingTypes(cc, kc, fc, st, tmp);
        
        return mutableSt;
    }

    public ImmutablePair<Boolean, CubexType> getReturn(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {

        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        // get the iterable type by joining with iterable<nothing>
        ArrayList<CubexType> params = new ArrayList<CubexType>();
        params.add(new Nothing());
        CubexType testIter = new CubexCType(new CubexCName("Iterable"), params);
        // this cast should be safe
        CubexCType foundIter = (CubexCType) CubexTC.join(cc, kc, testIter, exprType);

        SymbolTable tmp = mutableSt.set(name, foundIter.params.get(0));

        ImmutablePair<Boolean, CubexType> ret = stmt.getReturn(cc, kc, fc, st, tmp);
        // for returns same type as statement but not guarunteed to return
        return new ImmutablePair<Boolean, CubexType>(new Boolean(false), ret.getRight());
    }

    public String toString() {
        String n = name.toString();
        String e = expr.toString();
        String s = stmt.toString();
        return String.format("for ( %s in %s ) %s", n, e, s);   
    }
}

class CubexReturn extends CubexStatement {
    private CubexExpression expr;
    public CubexReturn(CubexExpression e) {
        expr = e;
    }

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        // check that expr type checks
        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        return mutableSt;
    }

    public ImmutablePair<Boolean, CubexType> getReturn(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        CubexType exprType = expr.getType(cc, kc, fc, st.merge(mutableSt));
        return new ImmutablePair<Boolean, CubexType>(new Boolean(true), exprType);
    }

    public String toString() {
        String e = expr.toString();
        return String.format("return %s ;", e);   
    }
}