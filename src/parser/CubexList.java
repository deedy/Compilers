import java.util.Collection;

public class CubexExpression extends CubexExpression {
    private XiType mType;
    private Collection<? extends XiExpression> mElements;
    public CubexExpression(Collection<? extends XiExpression> elems) {
        mType = type;
        mElements = elems;
    }
}