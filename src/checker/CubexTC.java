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

public class CubexTC {
	static class UnexpectedTypeHierarchyException extends RuntimeException {
		UnexpectedTypeHierarchyException(String message) {
			super(message);
		}
	}

	public static class TypeCheckException extends RuntimeException {
		public TypeCheckException(String message) {
			super(message);
		}
	}

	public static ArrayList<HashSet<CubexType>> findLevelPathToRoot(CubexClassContext cc, CubexKindContext kc, CubexType t) {
		// Not Sure what CubexKindContext should be doing here (deedy)
		ArrayList<HashSet<CubexType>> allSuperTypes = new ArrayList<HashSet<CubexType>>();
		Queue<Pair<Integer, CubexType>> superTypeQueue = new LinkedList<Pair<Integer, CubexType>>();
		superTypeQueue.add(new Pair<Integer, CubexType>(0,t));
		while (!superTypeQueue.isEmpty()) {
			Pair<Integer,CubexType> immpair = superTypeQueue.poll();
			int level = immpair.getLeft();
			if (level < allSuperTypes.size()) {
				allSuperTypes.get(level).add(immpair.getRight());
			} else {
				HashSet<CubexType> hs = new HashSet<CubexType>();
				hs.add(immpair.getRight());
				allSuperTypes.add(hs);
			}
			for (CubexType ct : immpair.getRight().immediateSuperTypes(cc)) {
				superTypeQueue.add(new Pair<Integer, CubexType>(level+1, ct));
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
		// System.out.printf("The supertypes of %s are %s\n", t1,levelPathToRootOne);

		ArrayList<HashSet<CubexType>> levelPathToRootTwo = findLevelPathToRoot(cc, kc, t2);
		// System.out.printf("The supertypes of %s are %s\n", t2,levelPathToRootTwo);
		HashSet<CubexType> common = new HashSet<CubexType>();
		// System.out.println(levelPathToRootOne);
		// System.out.println(levelPathToRootTwo);
		for(HashSet<CubexType> hs : levelPathToRootOne) {
			for(CubexType a : hs) {
				if(subType(cc, kc, t1, a) && subType(cc, kc, t2, a)) {
					common.add(a);
				}
				for(HashSet<CubexType> hs2 : levelPathToRootTwo) {
					for(CubexType b : hs2) {
						if(!(a instanceof Thing) && !(b instanceof Thing)) {
							if(isValid(cc, kc, new CubexIType(a,b))) {
								common.add(new CubexIType(a,b));
							}
							if(isValid(cc, kc, new CubexIType(b,a))) {
								common.add(new CubexIType(b,a));
							}
						}
					}
				}
			}
		}
		for(HashSet<CubexType> hs : levelPathToRootTwo) {
			for(CubexType a : hs) {
				if(subType(cc, kc, t1, a) && subType(cc, kc, t2, a)) {
					common.add(a);
				}
			}
		}
		// System.out.printf("The common supertypes of %s and %s are %s\n", t1, t2, common);
		CubexType lowest = new Thing();
		for(CubexType test : common) {
			if(subType(cc, kc, test, lowest)) {
				if(!containsNothing(test)) {
					lowest = test;
				} else {
					if(containsNothing(lowest) || lowest instanceof Thing) {
						lowest = test;
					}
				}
			} else {
				if (containsNothing(lowest)) {
					lowest = test;
				}
			}
		}
		// System.out.printf("And the join is %s\n", lowest);
	 	return lowest;
	}

	private static boolean containsNothing(CubexType t) {
		if(t instanceof Nothing) return true;
		if(t instanceof CubexCType) {
			CubexCType c = (CubexCType) t;
			for(CubexType param : c.params) {
				if(containsNothing(param)) return true;
			}
		}
		return false;
	}

	// axiom for identity
	public static boolean subType(CubexClassContext cc, CubexKindContext kc, CubexType t1, CubexType t2) {
		if(t1.equals(t2)) return true;
		if(t1 instanceof CubexIType) {
			CubexIType left = (CubexIType) t1;
			if(t2 instanceof CubexIType) {
				CubexIType right = (CubexIType) t2;
				return subType(cc, kc, t1, right);
			} else if (t2 instanceof CubexCType) {
				CubexCType right = (CubexCType) t2;
				return subType(cc, kc, left, right);
			} else if (t2 instanceof Thing) {
				return true;
			} else if (t2 instanceof Nothing){
				return t1.equals(t2);
			} else return (left.equals(t2));

		} else if (t1 instanceof CubexCType) {
			CubexCType left = (CubexCType) t1;
			if(t2 instanceof CubexIType) {
				CubexIType right = (CubexIType) t2;
				return subType(cc, kc, t1, right);
			} else if (t2 instanceof CubexCType) {
				CubexCType right = (CubexCType) t2;
				return subType(cc, kc, left, right);
			} else if (t2 instanceof Thing) {
				return true;
			} else if (t2 instanceof Nothing){
				return t1.equals(t2);
			} else return (left.equals(t2));

		} else if (t1 instanceof Thing) {
			return t2 instanceof Thing;
		} else if (t2 instanceof Thing) {
			return true;
		} else if (t1 instanceof Nothing){
			return true;
		} else return false;
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
		// System.out.println(t);
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
		return subType(cc, kc, t1, (CubexType) t2);
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

	public static boolean subType(CubexClassContext cc, CubexKindContext kc, SymbolTable st1, SymbolTable st2) {
		// get the keys of st1
		Collection<CubexVName> vars = st1.keys();
		for(CubexVName name : vars) {
			if(st2.contains(name)){
				CubexType t1 = st1.get(name);
				CubexType t2 = st2.get(name);
				if(!subType(cc, kc, t1, t2)){
					return false;
				}
			}
		}
		return true;
	}


	public static boolean equiv(CubexClassContext cc, CubexKindContext kc, CubexTypeScheme ts1, CubexTypeScheme ts2) {
		// check equivalence of type contexts
		SymbolTable st1 = new SymbolTable(ts1.tCont);
		SymbolTable st2 = new SymbolTable(ts2.tCont);
		if(!subType(cc, kc, st1, st2)) return false;
		if(!subType(cc, kc, st2, st1)) return false;
		// check type equivalence
		CubexType t1 = ts1.type;
		CubexType t2 = ts2.type;
		if(!subType(cc, kc, t1, t2)) return false;
		if(!subType(cc, kc, t2, t1)) return false;
		// all tests pass
		return true;
	}


	// method lookup
	public static CubexTypeScheme method(CubexClassContext cc, CubexKindContext kc, CubexCType t, CubexVName v) {
		// System.out.printf("1 : %s.%s\n", t, v);
		// get the scheme from the class
		CubexTypeScheme s = method(cc, t, v);
		if(s == null){
			// no mthod in this class, try the supertypes
			// we only need to return the first one we see
			// because the spec says if we see more than one
			// they must be equal
			// System.out.println(t.immediateSuperTypes(cc));
			for(CubexType et : t.immediateSuperTypes(cc)){
				CubexTypeScheme es = method(cc, kc, et, v);
				if(es != null) return es;
			}
			return null;
           }
		// make type replacements
		CubexObject obj = cc.get(t);
		List<CubexPName> generics = obj.kCont;
		return replaceGenerics(generics, t.params, s);
	}

	// only makes sense for CTypes to have methods
	public static CubexTypeScheme method(CubexClassContext cc, CubexKindContext kc, CubexType t, CubexVName v) {
		// System.out.printf("2 : %s.%s\n", t, v);
		if(t instanceof CubexCType) {
			return method(cc, kc, (CubexCType) t, v);
		} else if(t instanceof CubexIType) {
			CubexIType it = (CubexIType) t;
			CubexTypeScheme s1 = method(cc, kc, it.a, v);
			CubexTypeScheme s2 = method(cc, kc, it.b, v);
			if(s1 == null) return s2;
			else if(s2 != null) {
				// neither are null, assert equiv
				if(equiv(cc, kc, s1, s2)) {
					return s1;
				} else return null;
			} else return s1;
		} else if (t instanceof Nothing) {
			return null;
		}
		else {
			return null;
		}
	}

	public static CubexTypeScheme method(CubexClassContext cc, CubexCType c, CubexVName v) {
		// System.out.printf("3 : %s.%s\n", c, v);
		if(cc.contains(c)){
			CubexObject obj = cc.get(c);
			CubexFunHeader f = null;
			// System.out.println(obj);
			if(obj instanceof CubexClass) {
				for(CubexFunHeader g : ((CubexClass) obj).funList){
					if(g.name.equals(v)){
						// need exactly one match
						if(f != null) {
				           	throw new CubexTC.TypeCheckException(
				                String.format("%s HAS METHOD %s MORE THAN ONCE", 
				                    c.toString(), v.toString())
				                );
						}
						f = g;
					}
				}
				// no match
				if(f == null) {
					// System.out.println(c.immediateSuperTypes(cc));
					return null;
				}
				return f.scheme; 
			} else {
				// System.out.println(((CubexInterface) obj).funList);
				for(CubexFunHeader g : ((CubexInterface) obj).funList){
					if(g.name.equals(v)){
						// need exactly one match
						// if(!(g instanceof CubexFunction)) continue;
						// System.out.println((CubexFunction) g);
						if(f != null) {
				           	throw new CubexTC.TypeCheckException(
				                String.format("%s HAS METHOD %s MORE THAN ONCE", 
				                    c.toString(), v.toString())
				                );
						}
						f = g;
					}
				}
				// no match
				if(f == null) {
					return null;
				}
				return f.scheme; 
			}
		}
       	throw new CubexTC.TypeCheckException(
            String.format("%s NOT IN CLASS CONTEXT WHEN CHECKING METHOD %s", 
                c.toString(), v.toString())
            );
	}


	// type validity
	// valid type
	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexType t) {
		if(t instanceof CubexPType) {
			 return kc.contains(((CubexPType) t).name);
		}
		if(t instanceof Nothing) {
			return true;
		}
		return constructable(cc,kc, t) != null;
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
		Collection<CubexType> st1 = immediateSuperTypes(cc, kc, a);
		st1.add(a);
		// System.out.printf("%s <: %s\n", a, st1);
		for(CubexType v : st1) {
			if(subType(cc, kc, b, v) || b.equals(v)){
				if(!(subType(cc, kc, new Thing(), v))) {
					return null;
				}
			}
		}
		// intersection passes all tests
		return ret;
	}

	// return all method names of t and its supertypes
	public static Collection<CubexVName> allMethods(CubexClassContext cc, CubexKindContext kc, CubexType t) {
		// System.out.println("1");
		HashSet<CubexVName> methods = new HashSet<CubexVName>();
		for(CubexType v : immediateSuperTypes(cc, kc, t)) {
			methods.addAll(allMethods(cc, v));
		}
		methods.addAll(allMethods(cc, t));
		return methods;
	}

	public static List<CubexVName> allMethods(CubexClassContext cc, CubexCType c) {
		// System.out.println("2");
		ArrayList<CubexVName> names = new ArrayList<CubexVName>();
		CubexObject got = cc.get(c);
		if(got != null) {
			for(CubexFunHeader f : cc.get(c).funList) {
				names.add(f.name);
			}
		}
		return names;
	}

	public static List<CubexVName> allMethods(CubexClassContext cc, CubexType c) {
		// System.out.println("3");
		// non-ctypes have no methods
		if( c instanceof CubexCType) return allMethods(cc, (CubexCType) c);
		return new ArrayList<CubexVName>();
	}

	// everything else cannot construct anything
	public static CubexType constructable(CubexClassContext cc, CubexKindContext kc, CubexType t) {
		if(t instanceof CubexIType) {
			return constructable(cc, kc, (CubexIType) t);
		} else if (t instanceof CubexCType) {
			return constructable(cc, kc, (CubexCType) t);
		} else if (t instanceof Thing) {
			return constructable(cc, kc, (Thing) t);
		} else {
			return null;
		}
	}


	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexTypeContext tc) {
		for(CubexType t : tc.types) {
			if(!isValid(cc, kc, t)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValid(CubexClassContext cc, CubexKindContext kc, CubexTypeScheme s) {
		CubexKindContext hat = new CubexKindContext(s.kCont);
		CubexKindContext kc2 = kc.merge(hat);
		boolean a = isValid(cc, kc2, s.tCont);
		boolean b =  isValid(cc, kc2, s.type);
		return a && b;
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
		if(t instanceof CubexIType) {
			return replaceGenerics(generics, repTypes, (CubexIType) t);
		} else if (t instanceof CubexCType) {
			return replaceGenerics(generics, repTypes, (CubexCType) t);
		} else if (t instanceof CubexPType) {
			return replaceGenerics(generics, repTypes, (CubexPType) t);
		} else {
			return t;
		}
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