import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// t ::= v_p | v_c <t,...,t> | t & t | thing | nothing
public abstract class CubexType extends CubexNode {
	private static CubexType _Nothing = new Nothing();
	public static CubexType getNothing() { return _Nothing;}
	private static CubexType _Thing = new Thing();
	public static CubexType getThing() { return _Thing;}

	public abstract List<CubexCName> getClasses();
	public abstract List<CubexType> immediateSuperTypes(CubexClassContext cc);
    public abstract String toString();

    public boolean equals(Object obj) {
  		if(!(obj instanceof CubexType)) return false;
		CubexType ct = (CubexType) obj;
    	return this.toString().equals(ct.toString());
  	}
  	public int hashCode() {
    	return this.toString().hashCode();
  	}

  	public boolean isIterable(CubexClassContext cc, CubexKindContext kc) {
  		List<CubexType> p = new ArrayList<CubexType>();
  		p.add(new Thing());
  		return CubexTC.subType(cc, kc, this, new CubexCType(new CubexCName("Iterable"), p));
  	}

  	public abstract HNode accept(HVisitor c);
}

class Nothing extends CubexType {
	public Nothing(){
	}

	public String toString() {
		return "Nothing";
	}

	public List<CubexCName> getClasses() {
		return new ArrayList<CubexCName>();
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>();
	}

	public HNode accept(HVisitor c) {
        return null;
    }
}

class Thing extends CubexType {
	public Thing(){;
	}

	public String toString() {
		return "Thing";	
	}

	public List<CubexCName> getClasses() {
		return new ArrayList<CubexCName>();
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>();
	}

	public HNode accept(HVisitor c) {
        return null;
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

	public List<CubexCName> getClasses() {
		return new ArrayList<CubexCName>();
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>();
	}

	public HNode accept(HVisitor c) {
        return null;
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

	public List<CubexCName> getClasses() {
    return new ArrayList<CubexCName>(Arrays.asList(name));
	}

	public boolean isInterface(CubexClassContext cc) {
		return cc.get(name) instanceof CubexInterface;
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		CubexObject obj = cc.get(name);
		return new ArrayList<CubexType>(Arrays.asList(obj.type));
	}

	public boolean isIterable(CubexClassContext cc, CubexKindContext kc) {
		if(this.name.name.equals("Iterable")) return true;
		return super.isIterable(cc, kc);
	}

	public HNode accept(HVisitor c) {
        return null;
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

	public List<CubexCName> getClasses() {
		List<CubexCName> l = new ArrayList<CubexCName>();
		l.addAll(a.getClasses());
		l.addAll(b.getClasses());
		return l;
	}

	public List<CubexType> immediateSuperTypes(CubexClassContext cc) {
		return new ArrayList<CubexType>(Arrays.asList(a, b));
	}

	public HNode accept(HVisitor c) {
        return null;
    }
}