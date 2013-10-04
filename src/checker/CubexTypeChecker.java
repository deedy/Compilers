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
		return (ct == t);
	}

	// axiom for identity
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexType t1, CubexType t2) {
		// fix this
		return (t1 == t2);
	}

	// intersection rules
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexIType i, CubexType t) {
		return subType(cc, kc, i.a, t) || subType(cc, kc, i.b, t);
	}

	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexType t, CubexIType i) {
		return subType(cc, kc, t, i.a) && subType(cc, kc, t, i.b);
	}

	// cTypes
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexCType t1, CubexCType t2) {
		if(t1.name == t2.name) {
			if(t1.params.size() == t2.params.size()) {
				int l = t1.params.size();
				for(int i = 0; i < t2.params.size(); i++) {
					if (!subType(cc, kc, t1.params.get(i), t2.params.get(i))) {
						return false;
					}
					if (!subType(cc, kc, t2.params.get(i), t1.params.get(i))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}