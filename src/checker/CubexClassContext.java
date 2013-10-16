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
		CubexObject ret = map.get(name);
		if(ret == null){
        	throw new CubexTC.TypeCheckException(
                String.format("%s NOT IN CLASS CONTEXT", 
                    name.toString())
                ); 
		}
		return ret;
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
		CubexObject found = get(c.name);
		if(found == null){
        	throw new CubexTC.TypeCheckException(
                String.format("%s NOT IN CLASS CONTEXT", 
                    c.toString())
                ); 
		}
		return found;
	}

	public boolean containsClass(CubexCType c) {
		return map.containsKey(c.name) && get(c.name) instanceof CubexClass;
	}

	public boolean containsInterface(CubexCType c) {
		return map.containsKey(c.name) && get(c.name) instanceof CubexInterface;
	}

	public CubexClassContext merge(CubexClassContext cc) {
		return new CubexClassContext(map.plusAll(cc.map));
	}

	public String toString() {
		return map.toString();
	}
}