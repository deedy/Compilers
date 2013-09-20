import java.util.Collection;

public class CubexFunction {
	private CubexType[] mParameterTypes;
	private CubexType[] mReturnTypes;

	public Collection<? extends CubexType> getTypes(Collection<? extends CubexType> argTypes) throws NoSuchTypeException {
		if (argTypes.size() != mParameterTypes.length)
			throw new NoSuchTypeException();
		int i = 0;
		for (CubexType argType : argTypes) {
			if (!argType.isSubtypeOf(mParameterTypes[i]))
				throw new NoSuchTypeException();
			i++;
		}
		return java.util.Arrays.asList(mReturnTypes);
	}
}