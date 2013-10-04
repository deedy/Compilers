import org.pcollections.PMap;
import org.pcollections.HashTreePMap;

class CubexClassContext {
	PMap<CubexCName, CubexObject> map;
	public CubexClassContext(){
		map = HashTreePMap.empty();
	}

	public CubexClassContext(PMap<CubexCName, CubexObject> m) {
		map = m;
	}

	public CubexClassContext set(CubexCName name, CubexObject fun) {
		return new CubexClassContext(map.plus(name, fun));
	}

	public CubexObject get(CubexCName name) {
		return map.get(name);
	}

	public boolean contains(CubexCName name) {
		return map.containsKey(name);
	}

}