import java.util.Collection;

public abstract class CubexStatement {
}

class CubexBlock extends CubexStatement {
    private Collection<CubexStatement> stmts;
    public CubexBlock(Collection<CubexStatement> s) {
        stmts = s;
    }
}

class CubexAssign extends CubexStatement {
    private CubexName name;
    private CubexExpression expr;
    public CubexAssign(CubexName n, CubexExpression e) {
        name = n;
        expr = e;
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
}

class CubexWhileLoop extends CubexStatement {
    private CubexExpression expr;
    private CubexStatement stmt;
    public CubexWhileLoop(CubexExpression e, CubexStatement s) {
        expr = e;
        stmt = s;
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
}

class CubexReturn extends CubexStatement {
    private CubexExpression expr;
    public CubexReturn(CubexExpression e) {
        expr = e;
    }
}