import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// t ::= v_p | v_c <t,...,t> | t & t | thing | nothing
public abstract class CubexType {
	private static CubexType _Nothing = new Nothing();
	public static CubexType getNothing() { return _Nothing;}
	private static CubexType _Thing = new Thing();
	public static CubexType getThing() { return _Thing;}

	public abstract List<CubexCName> getClasses();
	public abstract List<CubexType> immediateSuperTypes(CubexClassContext cc);
  public abstract String toString();
  public int hashCode() {
    return new HashCodeBuilder(17, 37).
       append(this.toString()).
       toHashCode();
  }
}

class Nothing extends CubexType {
	public Nothing(){
	}

	public String toString() {
		return "Nothing";
	}

	public boolean equals (CubexType t) {
		return false;
	}

	public boolean equals (Nothing n) {
		return true;
	}

	public List<CubexCName> getClasses() {
		return new ArrayList<CubexCName>();
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>(
    		Arrays.asList(this));
	}
}

class Thing extends CubexType {
	public Thing(){;
	}

	public String toString() {
		return "Thing";	
	}

	public boolean equals (CubexType t) {
		return false;
	}

	public boolean equals (Thing n) {
		return true;
	}

	public List<CubexCName> getClasses() {
		return new ArrayList<CubexCName>();
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>();
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

	public boolean equals (CubexType t) {
		return false;
	}

	public boolean equals (CubexPType t) {
		return name.equals(t.name);
	}

	public List<CubexCName> getClasses() {
		return new ArrayList<CubexCName>();
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>();
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

	public CubexCType(String s) {
		name = new CubexCName(s);
		params = new ArrayList<CubexType>();
	}

	public String toString() {
		String l = ListPrinter.listToString(params, " , ");
		return String.format("%s < %s>", name.toString(), ListPrinter.nullify(l));	
	}

	public boolean equals (CubexType t) {
		return false;
	}

	public boolean equals (CubexCType t) {
		boolean a = name.equals(t.name);
		if(!a) return false;
		return(params.equals(t.params));
	}

	public List<CubexCName> getClasses() {
		List<CubexCName> l = new ArrayList<CubexCName>();
		l.add(name);
		return l;
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		CubexObject obj = cc.get(name);
		return new ArrayList<CubexType>(
    		Arrays.asList(obj.type));
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

	public boolean equals (CubexType t) {
		return false;
	}

	public boolean equals (CubexIType t) {
		return (a.equals(t.a)) && (b.equals(t.b));
	}

	public List<CubexCName> getClasses() {
		List<CubexCName> l = new ArrayList<CubexCName>();
		l.addAll(a.getClasses());
		l.addAll(b.getClasses());
		return l;
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>(
    		Arrays.asList(a, b));
	}

}