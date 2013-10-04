import org.pcollections.PMap;
import org.pcollections.HashTreePMap;

class CubexClassContext {
	PMap<CubexName, CubexObject> map;
	public CubexClassContext(){
		map = HashTreePMap.empty();
	}

	public CubexClassContext(PMap<CubexName, CubexObject> m) {
		map = m;
	}

	public CubexClassContext set(CubexName name, CubexObject fun) {
		return new CubexClassContext(map.plus(name, fun));
	}

	public CubexObject get(CubexName name) {
		return map.get(name);
	}

	public boolean contains(CubexName name) {
		return map.containsKey(name);
	}

}