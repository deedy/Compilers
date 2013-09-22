public class CubexFunction {
    CubexVName name;
    CubexTypeScheme scheme;
    CubexStatement body;
    public CubexFunction(CubexVName n, CubexTypeScheme s, CubexStatement b) {
        name = n;
        scheme = s;
        body = b;
    }
}