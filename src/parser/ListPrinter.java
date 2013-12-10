import java.lang.StringBuilder;
import java.util.List;

public class ListPrinter {
	public static String listToString(List<?> l, String sep) {
		StringBuilder build = new StringBuilder();
        if(l.size() == 1){
			build.append(l.get(0).toString());
		}
    	if(l.size() > 1){
    		build.append(l.get(0).toString());
    		for(int i = 1; i < l.size(); i++) {
    			String s = l.get(i).toString();
    			String t = String.format("%s%s", sep, s);
    			build.append(t);
    		}
    	}
    	return build.toString();
	}

	public static String enclose(String open, String s, String close) {
		if(!s.equals("")){
			s = s + " ";
		}
		return String.format("%s %s%s", open, s, close);
	}

	public static String listEnclose(List<?> l, String open, String close, String sep)
	{
		String s = listToString(l, sep);
		return enclose(open, s, close);
	}

	public static String nullify(String s) {
		if(!s.equals("")){
			s += " ";
		}
		return s;
	}
}
