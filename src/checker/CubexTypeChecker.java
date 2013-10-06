import java.util.Stack;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class CubexTypeChecker {
	static class UnexpectedTypeHierarchyException extends Exception {
		UnexpectedTypeHierarchyException(String message) {
			super(message);
		}
	}

	private static ArrayList<HashSet<CubexType>> findLevelPathToRoot(CubexClassContext cc, CubexKindContext kc, CubexType t) {
		// Not Sure what CubexKindContext should be doing here (deedy)
		ArrayList<HashSet<CubexType>> allSuperTypes = new ArrayList<HashSet<CubexType>>();
		Queue<ImmutablePair<Integer, CubexType>> superTypeQueue = new LinkedList<ImmutablePair<Integer, CubexType>>();
		superTypeQueue.add(new ImmutablePair(0,t));
		while (!superTypeQueue.isEmpty()) {
			ImmutablePair<Integer,CubexType> immpair = superTypeQueue.poll();
			int level = immpair.getLeft();
			if (level < allSuperTypes.size()) {
				allSuperTypes.get(level).add(immpair.getRight());
			} else {
				HashSet<CubexType> hs = new HashSet<CubexType>();
				hs.add(immpair.getRight());
				allSuperTypes.add(hs);
			}
			for (CubexType ct : immpair.getRight().superTypes(cc)) {
				superTypeQueue.add(new ImmutablePair(level+1, ct));
			}
		}
		return allSuperTypes;
	}

	public static CubexType join (CubexClassContext cc, CubexKindContext kc, CubexType t1, CubexType t2)
			throws UnexpectedTypeHierarchyException {
		ArrayList<HashSet<CubexType>> levelPathToRootOne = findLevelPathToRoot(cc, kc, t1);
		ArrayList<HashSet<CubexType>> levelPathToRootTwo = findLevelPathToRoot(cc, kc, t2);

		int pointOne = levelPathToRootOne.size()-1;
		int pointTwo = levelPathToRootTwo.size()-1;
		HashSet<CubexType> common = null;
		for (int i = Math.min(pointOne, pointTwo); i >= 0; i--) {
			HashSet<CubexType> intersection = new HashSet<CubexType>(levelPathToRootOne.get(pointOne));
			intersection.retainAll(levelPathToRootTwo.get(pointTwo));
			if (intersection.size() > 0) {
				common = intersection;
				pointOne--;
				pointTwo--;
			} else {
				break;
			}
		}
		if (common == null) {
			throw new UnexpectedTypeHierarchyException("No common super class found");
		}
		if (common.size() > 1) {
			throw new UnexpectedTypeHierarchyException("Ambiguous common super class"); 
		}
		// Return first element of intersection 
		for (CubexType ct : common) {
			return ct;
		}
		return null;
	}

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

	public static Collection<CubexType> superTypes(CubexClassContext cc, CubexKindContext kc, CubexType t){
		HashSet<CubexType> ret = new HashSet<CubexType>();
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
		return constructable(cc,kc, t) != null;
	}

	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, Nothing t) {
		return true;
	}

	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexPType t) {
		return kc.contains(t.name);
	}

	public static CubexType constructable(CubexClassContext cc, CubexKindContext kc, CubexCType c) {
		if(cc.containsClass(c)){
			for(CubexType t : c.params) {
				if(!(isValid(cc, kc, t))) return null;
			}
			return c;
		}
		if(cc.containsInterface(c)){
			for(CubexType t : c.params) {
				if(!(isValid(cc, kc, t))) return null;
			}
			return CubexType.getThing();
		}
		return null;
	}

	public static CubexType constructable(CubexClassContext cc, CubexKindContext kc, Thing t2) {
		return t2;
	}

	public static CubexType constructable(CubexClassContext cc, CubexKindContext kc, CubexIType t) {
		CubexType a = t.a;
		CubexType b = t.b;
		// make sure b contains only interfaces
		CubexType  

		return null;
	}

	public static CubexType constructable(CubexClassContext cc, CubexKindContext kc, CubexType t) { 
		return null;
	}


	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexTypeContext tc) {
		for(CubexType t : tc.types) {
			if(!isValid(cc, kc, t)) return false;
		}
		return true;
	}

	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexTypeScheme s) {
		CubexKindContext hat = new CubexKindContext(s.kCont);
		CubexKindContext kc2 = kc.merge(hat);
		return isValid(cc, kc2, s.tCont) && isValid(cc, kc2, s.type);
	}
}