import org.pcollections.PMap;
import org.pcollections.HashTreePMap;

class CubexFunctionContext {
	PMap<CubexVName, CubexFunHeader> map;
	public CubexFunctionContext(){
		map = HashTreePMap.empty();
	}

	public CubexFunctionContext(PMap<CubexVName, CubexFunHeader> m) {
		map = m;
	}

	// functional
	public CubexFunctionContext set(CubexVName name, CubexFunHeader fun) {
		return new CubexFunctionContext(map.plus(name, fun));
	}

	public CubexFunHeader get(CubexVName name) {
		return map.get(name);
	}

	public boolean contains(CubexVName name) {
		return map.containsKey(name);
	}

}