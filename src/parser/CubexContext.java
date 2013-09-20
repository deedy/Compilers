import java.util.Map;
import java.util.HashMap;

public class CubexContext {
	private Map<String,CubexType> mVariables = new HashMap<String,CubexType>();
	private Map<String,CubexFunction> mFunctions = new HashMap<String,CubexFunction>();

	public CubexType getVariable(String name) { return mVariables.get(name); }
	public CubexFunction getFunction(String name) { return mFunctions.get(name); }
}