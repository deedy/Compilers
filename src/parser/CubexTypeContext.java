import java.util.Map;
import java.util.HashMap;

// maps vnames to types
public class CubexTypeContext {
    HashMap<CubexVName, CubexType> tbl;
    public CubexTypeContext() {
        tbl = new HashMap<CubexVName, CubexType>();
    }
    public void add(CubexVName n, CubexType t) {
        tbl.put(n, t);
    }
}
