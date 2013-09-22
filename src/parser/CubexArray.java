import java.util.Collection;

public class CubexArray extends XiExpression {
    private Collection<? extends CubexExpression> mElements;
    public CubexArray(Collection<? extends CubexExpression> elems) {
        mElements = elems;
    }
}