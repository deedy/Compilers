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

	public static List<CubexType> immediateSuperTypes(CubexClassContext cc, CubexKindContext kc, CubexType t){
		ArrayList<CubexType> ret = new ArrayList<CubexType>();
		Stack<CubexType> stack = new Stack<CubexType>();
		for(CubexType name : t.immediateSuperTypes(cc)){
			stack.push(name);
		}
		while(!stack.empty()){
			CubexType ty = stack.pop();
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
				if(!t1.name.name.equals("Iterable")){
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
			CubexType swapped = swapParams(generics, t1, ext);
			// see if the new type is a successful subtype, else recurse up
			return subType(cc,kc, swapped, t2) || subType(cc, kc, t1.immediateSuperTypes(cc).get(0), t2);
		}
		return false;
	}

	// exchange generics in t2 for types in t1
	public static CubexType swapParams(List<CubexPName> generics, CubexCType t1, CubexCType t2){
		// t1 and generics should have same params length
		List<CubexType> newParams = new ArrayList<CubexType>(t2.params);
		for(int i = 0; i < generics.size(); i++){
			// for each generic g_i, replace all instances of g_i in t2 with t1_i
			CubexPType g = new CubexPType(generics.get(i));
			for(int j = 0; j < newParams.size(); j++){
				if(newParams.get(i).equals(g)) {
					newParams.set(i, t1.params.get(i));
				}
			}
		}
		return new CubexCType(t2.name, newParams);
	}

	// overloaded for non ctypes
	public static CubexType swapParams(List<CubexPName> generics, CubexCType t1, CubexType t2) {
		return t2;
	}


	// method lookup
	public static CubexTypeScheme method(CubexClassContext cc, CubexKindContext kc, CubexType t, CubexVName v) {
		//todo: dom
		return null;
	}

	public static CubexTypeScheme method(CubexClassContext cc, CubexKindContext kc, Nothing n, CubexVName v) {
		// nothing definable here
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
		// make sure b contains only interfaces
		//CubexType  

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