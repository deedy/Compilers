import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.lang.StringBuilder;

// a list of type parameters
public class CubexKindContext {
	public List<CubexPName> params;
	public CubexKindContext(List<CubexPName> l) {
		params = l;
	}

    public String toString() {
        return ListPrinter.listToString(params, " , ");
    }
}