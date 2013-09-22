public class CubexFunction {
    CubexVName name;
    CubexTypeScheme scheme;
    CubexStatement body;
    public CubexFunction(CubexVName n, CubexTypeScheme s, CubexStatement b) {
        name = n;
        scheme = s;
        body = b;
    }

    // constructor for immediate return
    public CubexFunction(CubexVName n, CubexTypeScheme s, CubexExpression e) {
        name = n;
        scheme = s;
        body = new CubexReturn(e);
    }
    public String toString() {
    	String n = name.toString();
    	String s = scheme.toString();
    	String b = body.toString();
    	return String.format("fun %s %s %s", n, s, b);
    }
}