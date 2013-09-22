import java.util.Collection;

public class CubexFunctionCall extends CubexExpression {
    private CubexName name;
    private Collection<CubexType> typeList;
    private Collection<CubexExpression> exprList;
    public CubexFunctionCall(CubexName n, Collection<CubexType>  tl, Collection<CubexExpression> el) {
        name = n;
        typeList = tl;
        exprList = el;
    }
}