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

	public CubexFunctionContext set(CubexName name, CubexFunHeader fun) {
		return new CubexFunctionContext(map.plus(name, fun));
	}

	public CubexFunHeader get(CubexName name) {
		return map.get(name);
	}

	public boolean contains(CubexName name) {
		return map.containsKey(name);
	}

	public CubexFunctionContext merge(CubexFunctionContext fc) {
		return new CubexFunctionContext(map.plusAll(fc.map));
	}

}