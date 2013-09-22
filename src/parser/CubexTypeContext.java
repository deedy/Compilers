import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.lang.StringBuilder;

// maps vnames to types
public class CubexTypeContext {
    LinkedHashMap<CubexVName, CubexType> tbl;
    public CubexTypeContext() {
        tbl = new LinkedHashMap<CubexVName, CubexType>();
    }
    public void add(CubexVName n, CubexType t) {
        tbl.put(n, t);
    }

    public String toString() {
    	StringBuilder build = new StringBuilder();
    	// put keys and values into arraylist
    	ArrayList<String> keys = new ArrayList<String>();
    	ArrayList<String> vals = new ArrayList<String>();
    	for (Map.Entry<CubexVName, CubexType> entry : tbl.entrySet()) {
		    String key = entry.getKey().toString();
		    String value = entry.getValue().toString();
		    keys.add(key);
		    vals.add(value);
		}
    	if(keys.size() > 0){
    		String init = String.format("%s : %s", 
								keys.get(0).toString(),
								vals.get(0).toString());
    		build.append(init);
    		for(int i = 1; i < keys.size(); i++) {
    			String t = String.format(" , %s : %s", 
								keys.get(i).toString(),
								vals.get(i).toString());
    			build.append(t);
    		}
    	}
    	return build.toString();
    }

}
