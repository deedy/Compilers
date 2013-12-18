import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class CParser {
	public static CubexProg parse(CubexLexer lex) {
		CubexParser par = null;
		try {
	        CommonTokenStream tokens = new CommonTokenStream(lex);
	        par = new CubexParser(tokens);
	        par.removeErrorListeners();
	        par.setBuildParseTree(true);
	        CubexProg prog = par.prog().cu;
	        return prog;
	    } catch (Exception e) {
	    	par.reset();
			ParserRuleContext tree = par.prog();
	        tree.inspect(par);
	        par.reset();	    	
	        System.out.println("Parser error");
	    	return null;
	    }
	}
}