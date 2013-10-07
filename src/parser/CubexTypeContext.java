import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

// maps vnames to types
public class CubexTypeContext {
    List<CubexVName> names;
    List<CubexType> types;
    public CubexTypeContext() {
        names = new ArrayList<CubexVName>();
        types = new ArrayList<CubexType>();
    }

    public CubexTypeContext( List<CubexVName> n, List<CubexType> t) {
        names = n;
        types = t;
    }

    public void add(CubexVName n, CubexType t) {
        names.add(n);
        types.add(t);
    }

    public String toString() {
    	StringBuilder build = new StringBuilder();
        if(names.size() > 0){
            String init = String.format("%s : %s", 
                        names.get(0).toString(),
                        types.get(0).toString());  
            build.append(init);
            for(int i = 1; i < names.size(); i++){
                String t = String.format(" , %s : %s", 
                    names.get(i).toString(),
                    types.get(i).toString());
                build.append(t);
            }
        }
    	return build.toString();
    }

    public boolean equals(CubexTypeContext tc) {
        return (names.equals(tc.names)) && (types.equals(tc.types));
    }

}
