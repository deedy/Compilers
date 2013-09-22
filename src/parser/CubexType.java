import java.util.Map;
import java.util.HashMap;
import java.util.List;

// t ::= v_p | v_c <t,...,t> | t & t | thing | nothing
public class CubexType {
	private static CubexType _Nothing = new Nothing();
	public static CubexType getNothing() { return _Nothing;}
	private static CubexType _Thing = new Nothing();
	public static CubexType getThing() { return _Nothing;}
}

class Nothing extends CubexType {
	public Nothing(){
	}

	public String toString() {
		return "Nothing";
	}
}

class Thing extends CubexType {
	public Thing(){
	}

	public String toString() {
		return "Thing";	
	}	
}
class CubexPType extends CubexType{
	CubexPName name;
	// accept a type name
	public CubexPType(CubexPName n){
		name = n;
	}

	public String toString() {
		return name.toString();	
	}
}

class CubexCType extends CubexType{
	// accept a class name and list of type params
	CubexCName name;
	List<CubexType> params;
	public CubexCType(CubexCName n, List<CubexType> l){
		name = n;
		params = l;
	}

	public String toString() {
		String l = ListPrinter.listToString(params, " , ");
		return String.format("%s < %s >", name.toString(), l);	
	}
}

class CubexIType extends CubexType{
	// accept intersection of two types
	CubexType a, b;
	public CubexIType(CubexType a, CubexType b){
		this.a = a;
		this.b = b;
	}

	public String toString() {
		return String.format("%s & %s", a.toString(), b.toString());
	}

}