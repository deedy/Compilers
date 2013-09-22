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
    	StringBuilder build = new StringBuilder();
    	if(params.size() > 0){
    		build.append(params.get(0).toString());
    		for(int i = 1; i < params.size(); i++) {
    			String s = params.get(i).toString();
    			String t = String.format(" , %s", s);
    			build.append(t);
    		}
    	}
    	return build.toString();
    }
}