import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class CParser {
	public static CubexProg parse(CubexLexer lex) {
		try {
	        CommonTokenStream tokens = new CommonTokenStream(lex);
	        CubexParser par = new CubexParser(tokens);
	        par.removeErrorListeners();
	        par.setBuildParseTree(true);
	        CubexProg prog = par.prog().cu;
	        return prog;
	    } catch (Exception e) {
	    	System.out.println("Parser error");
	    	return null;
	    }
	}
}