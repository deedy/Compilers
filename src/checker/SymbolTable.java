import org.pcollections.PMap;
import org.pcollections.HashTreePMap;

class SymbolTable {
	PMap<CubexName, CubexType> map;
	public SymbolTable(){
		map = HashTreePMap.empty();
	}

	public SymbolTable(PMap<CubexName, CubexType> m) {
		map = m;
	}

	// functional
	public SymbolTable set(CubexName name, CubexType t) {
		return new SymbolTable(map.plus(name, t));
	}

	public CubexType get(CubexName name) {
		return map.get(name);
	}

	public boolean contains(CubexName name) {
		return map.containsKey(name);
	}

}