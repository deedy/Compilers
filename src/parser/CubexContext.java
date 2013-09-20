import java.util.Map;
import java.util.HashMap;

public class CubexContext {
	// map variable names to types
	private Map<String,CubexType> mVariables = new HashMap<String,CubexType>();
	// map function names to types
	private Map<String,CubexFunction> mFunctions = new HashMap<String,CubexFunction>();

	// get the type of a variable
	public CubexType getVariable(String name) { return mVariables.get(name); }
	// get the function associated with a nmae
	public CubexFunction getFunction(String name) { return mFunctions.get(name); }
}