import java.util.Collection;

public class CubexMethodCall extends CubexExpression {
    private CubexExpression expr;
    private CubexName name;
    private Collection<CubexType> typeList;
    private Collection<CubexExpression> exprList;
    public CubexMethodCall(CubexExpression e, CubexName n, Collection<CubexType>  tl, Collection<CubexExpression> el) {
        expr = e;
        name = n;
        typeList = tl;
        exprList = el;
    }
}