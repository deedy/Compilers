import java.util.Map;
import java.util.HashMap;
import java.util.List;

// t ::= v_p | v_c <t,...,t> | t & t | thing | nothing
public abstract class CubexType {
	// accept a type name
	public CubexType(CubexPName n){
	}

	// accept a class name and list of type params
	public CubexType(CubexCName n, List<CubexType> l){
	}

	// accept intersection of two types
	public CubexType(CubexType a, CubexType b){
	}
}
