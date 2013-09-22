import java.util.List;

// essentially a function signature, 
// maps type params and function params to a type

public class CubexTypeScheme {
    List<CubexPName> kCont;
    CubexTypeContext tCont;
    CubexType type;
	public CubexTypeScheme(List<CubexPName> k, CubexTypeContext tc, CubexType t){
        kCont = k;
        tCont = tc;
        type = t;
	}

    public String toString() {
    	return String.format("< %s > ( %s ) : %s", 
    						ListPrinter.listToString(kCont, " , "), 
    						tCont.toString(),
    						type.toString());
    }
}