import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class CLexer {
	public static CubexLexer lex (String fileName) {
		try {
			CubexLexer l = new CubexLexer(new ANTLRFileStream(fileName));
			l.removeErrorListeners();
			if (hasLexerError(l)) {
				System.out.println("Lexer error");
				return null;
			} else {
				return l;
			}
		} catch (Exception e) {
			System.out.println("Could not read file " + fileName);
			return null;
		}
	}

	public static boolean hasLexerError(CubexLexer lex) {
         for (Token token = lex.nextToken();
             token.getType() != Token.EOF;
             token = lex.nextToken()){
            int type = token.getType();
            String rule = lex.ruleNames[token.getType()-1];
            if (rule.equals("ERRORCHAR")) {
                lex.reset();
                return true;
            }
        }
        lex.reset();
        return false;
    }
}