import org.pcollections.HashTreePSet;
import org.pcollections.MapPSet;
import java.util.Collection;

class CubexKindContext {
	private MapPSet<CubexPName> set;
	public CubexKindContext(){
		set = HashTreePSet.empty();
	}

	public CubexKindContext(Collection<CubexPName> c) {
		set = HashTreePSet.from(c);
	}

	private CubexKindContext(MapPSet<CubexPName> h){
		set = h;
	}
	// functionally set a principal type
	public CubexKindContext add(CubexPName name) {
		return new CubexKindContext(set.plus(name));
	}

	public boolean contains(CubexPName name) {
		return set.contains(name);
	}
}