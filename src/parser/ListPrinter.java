import java.lang.StringBuilder;
import java.util.List;

public class ListPrinter {
	public static String listToString(List<?> l, String sep) {
		StringBuilder build = new StringBuilder();
    	if(l.size() > 0){
    		build.append(l.get(0).toString());
    		for(int i = 1; i < l.size(); i++) {
    			String s = l.get(i).toString();
    			String t = String.format("%s%s", sep, s);
    			build.append(t);
    		}
    	}
    	return build.toString();
	}
}