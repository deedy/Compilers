public class CubexVariable extends CubexExpression {
	private String mName;
	public CubexVariable(String name) { mName = name; }
	protected CubexType calculateType(CubexContext context) throws NoSuchTypeException {
		CubexType type = context.getVariable(mName);
		if (type == null)
			throw new NoSuchTypeException();
		else
			return type;
	}
}