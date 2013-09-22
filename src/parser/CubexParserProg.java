import org.antlr.v4.runtime.*;
import java.lang.StringBuilder;
import java.util.BitSet;


public class CubexParserProg {
    public static void main(String[] args) throws Exception {
        // lex the file
        CubexLexer lex = new CubexLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        CubexParser par = new CubexParser(tokens);
        System.out.print(assignment2(lex, par));
    }

    public static String assignment2(CubexLexer lex, CubexParser par) {
        if (hasLexerError(lex)) {
            return "lexer error";
        }
        return "parser error";
    }

    public static boolean hasLexerError(CubexLexer lex) {
         for (Token token = lex.nextToken();
             token.getType() != Token.EOF;
             token = lex.nextToken()){
            int type = token.getType();
            String rule = lex.ruleNames[token.getType()-1];
            if (rule.equals("ERRORCHAR")) {
                return true;
            } 
        }
        return false;
    }
}