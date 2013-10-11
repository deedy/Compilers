import org.pcollections.PMap;
import org.pcollections.HashTreePMap;
import java.util.Set;

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

	public boolean contains(CubexCType c) {
		if(map.containsKey(c.name)){
			if(map.get(c.name).kCont.size() == c.params.size()){
				return true;
			}
		}
		return false;
	}

	public CubexObject get(CubexCType c) {
		return map.get(c.name);
	}

	public boolean containsClass(CubexCType c) {
		return map.containsKey(c.name) && get(c.name) instanceof CubexClass;
	}

	public boolean containsInterface(CubexCType c) {
		return map.containsKey(c.name) && get(c) instanceof CubexInterface;
	}

	public CubexClassContext merge(CubexClassContext cc) {
		return new CubexClassContext(map.plusAll(cc.map));
	}
}