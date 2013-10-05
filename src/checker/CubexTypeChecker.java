import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CubexTypeChecker {
	// axioms for thing and nothing
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, Thing t, CubexType ct) {
		return (ct.equals(t));
	}

	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexType ct, Thing t) {
		return true;
	}

	public static boolean subType(CubexClassContext cc, CubexKindContext kc, Nothing t, CubexType ct) {
		return true;
	}

	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexType ct , Nothing t) {
		return ct.equals(t);
	}

	// axiom for identity
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexType t1, CubexType t2) {
		// fix this)
		return (t1.equals(t2));
	}

	// intersection rules
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexIType i, CubexType t) {
		return subType(cc, kc, i.a, t) || subType(cc, kc, i.b, t);
	}

	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexType t, CubexIType i) {
		return subType(cc, kc, t, i.a) && subType(cc, kc, t, i.b);
	}

	public static List<CubexType> superTypes(CubexClassContext cc, CubexKindContext kc, CubexType t){
		ArrayList<CubexType> ret = new ArrayList<CubexType>();
		Stack<CubexType> stack = new Stack<CubexType>();
		for(CubexType name : t.superTypes(cc)){
			stack.push(name);
		}
		while(!stack.empty()){
			CubexType ty = stack.pop();
			ret.add(ty);
			for(CubexType n : ty.superTypes(cc)){
				stack.push(n);
			}
		}
		return ret;
	}

	public static Collection<CubexCName> superClasses(CubexClassContext cc, CubexKindContext kc, CubexCType c){
		Collection<CubexCName> ret = new HashSet<CubexCName>();
		Stack<CubexCName> stack = new Stack<CubexCName>();
		for(CubexCName name : c.getClasses()){
			stack.push(name);
		}
		while(!stack.empty()){
			// pop a classname
			CubexCName name = stack.pop();
			if(ret.contains(name)) continue;
			// get its class
			CubexObject obj = cc.get(name);	
			ret.add(name);
			for(CubexCName n : obj.type.getClasses()){
				stack.push(n);
			}
		}
		return ret;
	}

	public static List<CubexCName> superClasses(CubexClassContext cc, CubexKindContext kc, CubexType c) {
		return new ArrayList<CubexCName>();
	}

	// is t1 a subtype of t2?
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexCType t1, CubexCType t2) {
		List<CubexType> params = t1.params;
		for(CubexCName name : superClasses(cc, kc, t1)){
			CubexObject obj = cc.get(name);
			if(name.equals(t2.name)) {
				// must have same number of parameters
				if(obj.kCont.size() == t2.params.size()) {
					boolean out = true;
					for(int i = 0; i < t2.params.size(); i++) {
						if (!subType(cc, kc, params.get(i), t2.params.get(i))) {
							out = false;
							break;
						}
						// don't do this is types are iterable
						if(!(obj.name.name.equals("Iterable") && t2.name.name.equals("Iterable"))) {
							if (!subType(cc, kc, t2.params.get(i), params.get(i))) {
								out = false;
								break;
							}
						}
					}
					return out;
				}
			}
		}
		return false;
	}

	public static boolean extend (CubexClassContext cc, CubexCType t1, CubexType t2) {
		// check that t1 in class context
		CubexObject obj = cc.get(t1.name);
		// see that obj extends t2
		return subType(cc, new CubexKindContext(obj.kCont), obj.type, t2);
	}

	// method lookup
	public static boolean hasMethod(CubexClassContext cc, CubexKindContext kc, CubexType c, CubexFunHeader method) {
		// enumerate all superclasses
		for(CubexCName name : superClasses(cc, kc, c)){
			if(hasMethod(cc, name, method)) return true;
		}
		return false;
	}

	public static boolean hasMethod(CubexClassContext cc, CubexKindContext kc, Nothing n, CubexFunHeader method) {
		return true;
	}

	public static boolean hasMethod(CubexClassContext cc, CubexCName c, CubexFunHeader method) {
		return (cc.contains(c)) && (cc.get(c).funList.contains(method));
	}


	// type validity
	// valid type
	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexType t) {
		return constructable(cc,kc, t);
	}

	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, Nothing t) {
		return true;
	}

	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexPType t) {
		return kc.contains(t.name);
	}

	public static boolean constructable(CubexClassContext cc, CubexKindContext kc, CubexCType c) {
		if(cc.containsClass(c) || cc.containsInterface(c)){
			for(CubexType t : c.params) {
				if(!(isValid(cc, kc, t))) return false;
			}
			return true;
		}
		return false;
	}

	public static boolean constructable(CubexClassContext cc, CubexKindContext kc, Thing t2) {
		return true;
	}

	public static boolean constructable(CubexClassContext cc, CubexKindContext kc, CubexIType t) {
		CubexType a = t.a;
		CubexType b = t.b;
		// the following cannot be false:
		// a is constructable but 
		return false;
	}

	public static boolean constructable(CubexClassContext cc, CubexKindContext kc, CubexType t) { 
		return false;
	}


	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexTypeContext tc) {
		for(CubexType t : tc.types) {
			if(!isValid(cc, kc, t)) return false;
		}
		return true;
	}

	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexTypeScheme s) {
		return false;
	}
}