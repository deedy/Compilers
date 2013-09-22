public class CubexFunHeader {
    CubexVName name;
    CubexTypeScheme scheme;
    public CubexFunHeader(CubexVName n, CubexTypeScheme s) {
        name = n;
        scheme = s;
    }

    public String toString() {
 		return String.format("fun %s %s", name, scheme.toString());   	
    }
}