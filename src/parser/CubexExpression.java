public abstract class CubexExpression {
	private CubexType mType = null;
	//public abstract CubexLocation getStart();
	//public abstract CubexLocation getEnd();
	public final CubexType getType(CubexContext context) throws NoSuchTypeException {
		if (mType == null)
			mType = calculateType(context);
		return mType;
	}
	protected abstract CubexType calculateType(CubexContext context) throws NoSuchTypeException;
}