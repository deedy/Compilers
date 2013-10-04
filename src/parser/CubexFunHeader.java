import java.util.ArrayList;

public class CubexFunHeader {
    CubexVName name;
    CubexTypeScheme scheme;
    public CubexFunHeader(CubexVName n, CubexTypeScheme s) {
        name = n;
        scheme = s;
    }

    public String toString() {
 		return String.format("fun %s %s ;", name, scheme.toString());   	
    }
}

class CubexFunction extends CubexFunHeader{
    CubexStatement body;

    public CubexFunction(CubexVName n, CubexTypeScheme s, CubexStatement b) {
        super(n, s);
        body = b;
    }

    // constructor for immediate return
    public CubexFunction(CubexVName n, CubexTypeScheme s, CubexExpression e) {
        super(n, s);
        body = new CubexReturn(e);
    }
    public String toString() {
    	String n = name.toString();
    	String s = scheme.toString();
    	String b = body.toString();
    	return String.format("fun %s %s %s", n, s, b);
    }
}