import org.pcollections.PMap;
import org.pcollections.HashTreePMap;
import java.util.Map;

class SymbolTable {
	PMap<CubexVName, CubexType> map;
	public SymbolTable(){
		map = HashTreePMap.empty();
	}

	public SymbolTable(PMap<CubexVName, CubexType> m) {
		map = m;
	}

	// functional
	public SymbolTable set(CubexVName name, CubexType t) {
		return new SymbolTable(map.plus(name, t));
	}

	public CubexType get(CubexVName name) {
		return map.get(name);
	}

	public boolean contains(CubexVName name) {
		return map.containsKey(name);
	}

	public SymbolTable merge(SymbolTable st) {
		return new SymbolTable(map.plusAll(st.map));
	}
	public SymbolTable intersection(SymbolTable st) {
		PMap<CubexVName, CubexType> newMap = map;
		for (Map.Entry<CubexVName, CubexType> e : map.entrySet()) {
			newMap = newMap.plus(e.getKey(), e.getValue());
		}
		return new SymbolTable(newMap);
	}
}