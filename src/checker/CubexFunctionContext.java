import org.pcollections.PMap;
import org.pcollections.HashTreePMap;

class CubexFunctionContext {
	PMap<CubexName, CubexFunHeader> map;
	public CubexFunctionContext(){
		map = HashTreePMap.empty();
	}

	public CubexFunctionContext(PMap<CubexName, CubexFunHeader> m) {
		map = m;
	}

	// functional
	public CubexFunctionContext set(CubexName name, CubexFunHeader fun) {
		return new CubexFunctionContext(map.plus(name, fun));
	}

	public CubexFunHeader get(CubexName name) {
		return map.get(name);
	}

	public boolean contains(CubexName name) {
		return map.containsKey(name);
	}

}