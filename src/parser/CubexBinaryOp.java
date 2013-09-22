public abstract class CubexBinaryOp extends CubexExpression{
    CubexExpression left;
    CubexExpression right;
}

class CubexAppend extends CubexBinaryOp {
    public CubexAppend(CubexExpression l, CubexExpression r) {
        left = l;
        right = r;
    }
}