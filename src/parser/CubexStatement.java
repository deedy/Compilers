import java.util.Collection;

public abstract class CubexStatement {
}

public class CubexBlock extends CubexStatement {
    private Collection<CubexStatement> stmts;
    public CubexBlock(Collection<CubexStatement> s) {
        stmts = s;
    }
}

public class CubexConditional extends CubexStatement {
    private CubexExpression expr;
    private CubexStatement stmt1;
    private CubexStatement stmt2;
    public CubexConditional(CubexExpression e, CubexStatement  s1, CubexStatement s2) {
        expr = e;
        stmt1 = s1;
        stmt2 = s2;
    }
}

public class CubexWhileLoop extends CubexStatement {
    private CubexExpression expr;
    private CubexStatement s;
    public CubexConditional(CubexExpression e, CubexStatement  s) {
        expr = e;
        stmt = s;
    }
}

public class CubexForLoop extends CubexStatement {
    private CubexName name;
    private CubexExpression expr;
    private CubexStatement stmt;
    public CubexConditional(CubexName n, CubexExpression e, CubexStatement  s) {
        name = n;
        expr = e;
        stmt = s;
    }
}

public class CubexReturn extends CubexStatement {
    private CubexExpression expr;
    public CubexReturn(CubexExpression e) {
        expr = e;
    }
}