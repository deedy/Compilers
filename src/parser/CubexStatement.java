import java.util.List;
import java.util.ArrayList;

public abstract class CubexStatement {
    /*
        Returns the outgoing of this class expression or throws an error
    */
    public abstract SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt);
}

class CubexBlock extends CubexStatement {
    private List<CubexStatement> stmts;
    public CubexBlock(List<CubexStatement> s) {
        stmts = s;
    }

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        return null;
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

        SymbolTable mergedSt = st.merge(mutableSt);
        CubexType type = expr.getType(cc, kc, fc, mergedSt);

        // return a ST with the VName bound/rebound
        return mergedSt.set(name, type);
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

        SymbolTable s1OutgoingTypes = stmt1.getOutgoingTypes(cc, kc, fc, st, mutableSt);
        SymbolTable s2OutgoingTypes = stmt2.getOutgoingTypes(cc, kc, fc, st, mutableSt);

        if (!isBoolean) {
            throw new CubexTC.TypeCheckException(toString());
        }
        
        return mutableSt;
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
            throw new CubexTC.TypeCheckException(toString());
        }
        
        return mutableSt;
    }

    public String toString() {
        String e = expr.toString();
        String s = stmt.toString();
        return String.format("while ( %s ) %s", e, s);
    }
}

class CubexForLoop extends CubexStatement {
    private CubexName name;
    private CubexExpression expr;
    private CubexStatement stmt;
    public CubexForLoop(CubexName n, CubexExpression e, CubexStatement  s) {
        name = n;
        expr = e;
        stmt = s;
    }

    public SymbolTable getOutgoingTypes(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st, SymbolTable mutableSt) {
        return null;
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
        return null;
    }

    public String toString() {
        String e = expr.toString();
        return String.format("return %s ;", e);   
    }
}