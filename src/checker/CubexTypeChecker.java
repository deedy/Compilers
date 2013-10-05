import java.util.Stack;
import java.util.List;

public class CubexTypeChecker {
	// axioms for thing and nothing
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, Thing t, CubexType ct) {
		return (ct == t);
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

	// cTypes, inheritance
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexCType t1, CubexCType t2) {
		Stack<CubexCName> stack = new Stack<CubexCName>();
		List<CubexType> params = t1.params;
		for(CubexCName name : t1.getClasses()){
			stack.push(name);
		}
		while(!stack.empty()){
			// pop a classname
			CubexCName name = stack.pop();
			// get its class
			CubexObject obj = cc.get(name);
			// see if we have found the same name
			if(name.equals(t2.name)) {
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
			//push extensions onto stack
			for(CubexCName n : obj.type.getClasses()){
				stack.push(n);
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

	public static boolean hasMethod(CubexClassContext cc, CubexKindContext kc, CubexCName c, CubexFunHeader method) {
		return false;
	}

	public static boolean hasMethod(CubexClassContext cc, CubexCName c, CubexFunHeader method) {
		return false;
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

}