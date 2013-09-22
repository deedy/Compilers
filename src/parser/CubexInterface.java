import java.util.List;

public class CubexInterface {
    CubexCName name;
    List<CubexPName> kCont;
    CubexType type;
    List<CubexFunHeader> funList;
    public CubexInterface(CubexCName c, List<CubexPName> k, CubexType t, List<CubexFunHeader> f) {
        name = c;
        kCont = k;
        type = t;
        funList = f;
    }
}