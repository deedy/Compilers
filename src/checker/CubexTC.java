import java.util.Stack;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class CubexTC {
	static class UnexpectedTypeHierarchyException extends Exception {
		UnexpectedTypeHierarchyException(String message) {
			super(message);
		}
	}

	public static class TypeCheckException extends RuntimeException {
		public TypeCheckException(String message) {
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
			for (CubexType ct : immpair.getRight().immediateSuperTypes(cc)) {
				superTypeQueue.add(new ImmutablePair(level+1, ct));
			}
		}
		return allSuperTypes;
	}


	public static CubexType join (CubexClassContext cc, CubexKindContext kc, CubexType t1, CubexType t2)
			throws UnexpectedTypeHierarchyException {
		if (t1 instanceof Nothing) {
			return t2;
		} else if (t2 instanceof Nothing) {
			return t1;
		} else if (t1 instanceof Thing) {
			return t1;
		} else if (t2 instanceof Thing) {
			return t2;
		}
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

	public static Collection<CubexType> immediateSuperTypes(CubexClassContext cc, CubexKindContext kc, CubexType t){
		HashSet<CubexType> ret = new HashSet<CubexType>();
		Stack<CubexType> stack = new Stack<CubexType>();
		for(CubexType name : t.immediateSuperTypes(cc)){
			stack.push(name);
		}
		while(!stack.empty()){
			CubexType ty = stack.pop();
			if(ret.contains(ty)) continue;
			ret.add(ty);
			for(CubexType n : ty.immediateSuperTypes(cc)){
				stack.push(n);
			}
		}
		return ret;
	}

	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexCType t1, CubexCType t2) {
		if(t1.name.equals(t2.name)){
			// check that params are equivalent
			List<CubexType> params1 = t1.params;
			List<CubexType> params2 = t2.params;
			if(params1.size() == params2.size()) {
				// check that t_i <: t'_i
				for(int i = 0; i < params1.size(); i++) {
					if(!(subType(cc,kc,params1.get(i), params2.get(i)))) return false;
				}
				// check that t'_i <: t_i
				if(!(t1.isIterable(cc, kc) && t2.isIterable(cc, kc))){
					for(int i = 0; i < params1.size(); i++) {
						if(!(subType(cc,kc,params2.get(i), params1.get(i)))) return false;
					}
				}
				// everything checks
				return true;
			}
			// same name, different number of params
			return false;
		}
		// names do not match, try supertype
		return false;
	}

	// recursively check superclasses
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexCType t1, CubexType t2) {
		if(cc.contains(t1)){
			CubexObject obj = cc.get(t1);
			// get the ordering of generics
			List<CubexPName> generics = obj.kCont;
			// get the extended type
			CubexType ext = obj.type;
			// create a new type by swapping generics for types in the extended type
			CubexType swapped = replaceGenerics(generics, t1.params, ext);
			// see if the new type is a successful subtype, else recurse up
			if(subType(cc,kc, swapped, t2)) return true;
			for(CubexType t : t1.immediateSuperTypes(cc)){
				if(subType(cc, kc, t, t2)) return true;
			}
		}
		return false;
	}

	// overloaded for non ctypes
	public static CubexType swapParams(List<CubexPName> generics, CubexCType t1, CubexType t2) {
		return t2;
	}


	// method lookup
	public static CubexTypeScheme method(CubexClassContext cc, CubexKindContext kc, CubexCType t, CubexVName v) {
		// get the scheme from the class
		CubexTypeScheme s = method(cc, t, v);
		if(s == null){
			// no mthod in this class, try the supertypes
			// we only need to return the first one we see
			// because the spec says if we see more than one
			// they must be equal
			for(CubexType et : t.immediateSuperTypes(cc)){
				CubexTypeScheme es = method(cc, kc, et, v);
				if(es != null) return es;
			}
			// supertypes give no luck
			return null;
		}
		// make type replacements
		CubexObject obj = cc.get(t);
		List<CubexPName> generics = obj.kCont;
		return replaceGenerics(generics, t.params, s);
	}

	// only makes sense for CTypes to have methods
	public static CubexTypeScheme method(CubexClassContext cc, CubexKindContext kc, CubexType t, CubexVName v) {
		// nothing definable here
		return null;
	}

	public static CubexTypeScheme method(CubexClassContext cc, CubexCType c, CubexVName v) {
		if(cc.contains(c)){
			CubexObject obj = cc.get(c);
			CubexFunHeader f = null;
			for(CubexFunHeader g : obj.funList){
				if(g.name.equals(v)){
					// need exactly one match
					if(f != null) return null;
					f = g;
				}
			}
			// no match
			if(f == null) return null;
			return f.scheme; 
		}
		return null;
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
		CubexType ret = constructable(cc, kc, a);
		if(ret == null) return null;
		// b constructs thing
		if(!constructable(cc, kc, b).equals(CubexType.getThing())) return null;
		// get the intersection of their superclasses
		// pairwise check all supertypes for compatibility
		for(CubexType v1 : immediateSuperTypes(cc, kc, a)) {
			for(CubexType v2 : immediateSuperTypes(cc, kc, b)) {
				if(!compatible(cc, kc, a, b, v1, v2)) return null;
			}
		}
		// get all methods of a and b
		Collection<CubexVName> aMethods = allMethods(cc, kc, a);
		Collection<CubexVName> bMethods = allMethods(cc, kc, b);
		// check that methods with the same name have same type scheme
		for(CubexVName n1 : aMethods) {
			for(CubexVName n2 : bMethods) {
				if(n1.equals(n2)) {
					// get the typeschemes
					CubexTypeScheme s1 = method(cc, kc, a, n1);
					CubexTypeScheme s2 = method(cc, kc, b, n2);
					// signatures must be exactly the same
					if(!s1.equals(s2)) return null;
				}
			}
		}
		// intersection passes all tests
		return ret;
	}

	// used to compute the second line of intersection validation
	private static boolean compatible(CubexClassContext cc, CubexKindContext kc, CubexType t1, CubexType t2, CubexCType c1, CubexCType c2) {
		// classes are compatible if they have different names, or they have the same name and compatible types
		if(!c1.name.equals(c2.name)) return true;
		return subType(cc, kc, t1, c2) && subType(cc, kc, t2, c1);
	}

	// non-classes are compatible by default
	private static boolean compatible(CubexClassContext cc, CubexKindContext kc, CubexType t1, CubexType t2, CubexType t3, CubexType t4) {
		return true;
	}

	// return all method names of t and its supertypes
	public static Collection<CubexVName> allMethods(CubexClassContext cc, CubexKindContext kc, CubexType t) {
		HashSet<CubexVName> methods = new HashSet<CubexVName>();
		for(CubexType v : immediateSuperTypes(cc, kc, t)) {
			methods.addAll(allMethods(cc, v));
		}
		return methods;
	}

	public static List<CubexVName> allMethods(CubexClassContext cc, CubexCType c) {
		ArrayList<CubexVName> names = new ArrayList<CubexVName>();
		for(CubexFunHeader f : cc.get(c).funList) {
			names.add(f.name);
		}
		return names;
	}

	public static List<CubexVName> allMethods(CubexClassContext cc, CubexType c) {
		// non-ctypes have no methods
		return new ArrayList<CubexVName>();
	}

	// everything else cannot construct anything
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

	// returns a copy of c2 with members of generics replaced with members of repTypes
	public static CubexType replaceGenerics(List<CubexPName> generics, List<CubexType> repTypes, CubexCType c) {
		// new arraylist is a copy of the old one
		ArrayList<CubexType> ret = new ArrayList<CubexType>(c.params);
		for(int j = 0; j < ret.size(); j++) {
			// recursive
			ret.set(j, replaceGenerics(generics, repTypes, ret.get(j)));
		}
		return new CubexCType(c.name, ret);
	}

	public static CubexType replaceGenerics(List<CubexPName> generics, List<CubexType> repTypes, CubexIType t) {
		CubexType a = replaceGenerics(generics, repTypes, t.a);
		CubexType b = replaceGenerics(generics, repTypes, t.b);
		return new CubexIType(a, b);
	}

	public static CubexType replaceGenerics(List<CubexPName> generics, List<CubexType> repTypes, CubexPType p) {
		int i = generics.indexOf(p.name);
		if(i != -1){
			return repTypes.get(i);
		}
		return p;
	}

	public static CubexType replaceGenerics(List<CubexPName> generics, List<CubexType> repTypes, CubexType t) {
		return t;
	}

	public static CubexTypeScheme replaceGenerics(List<CubexPName> generics, List<CubexType> repTypes, CubexTypeScheme ts) {
		// save function generics
		List<CubexPName> funGen = ts.kCont;
		// replace type context
		List<CubexVName> names = ts.tCont.names;
		List<CubexType> types = ts.tCont.types;
		ArrayList<CubexType> ret = new ArrayList<CubexType>(types);
		for(int j = 0; j < ret.size(); j++) {
			// reset each type
			ret.set(j, replaceGenerics(generics, repTypes, ret.get(j)));
		}
		// replace the return type
		CubexType retType = replaceGenerics(generics, repTypes, ts.type);
		return new CubexTypeScheme(funGen, new CubexTypeContext(names, ret), retType);
	}
}