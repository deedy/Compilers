import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.dfa.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.*;
import java.lang.StringBuilder;
import java.util.BitSet;


class ErrorListener implements ANTLRErrorListener {

    public boolean hasError = false;

    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
    }
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
    }
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
    }
    public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        hasError = true;
    }
}

public class CubexLexerProg {
    public static void main(String[] args) throws Exception {
        // lex the file
        CubexLexer lex = new CubexLexer(new ANTLRFileStream(args[0]));
        System.out.print(assignment1(lex));
    }

    public static String assignment1(CubexLexer lex) {
        StringBuilder output = new StringBuilder();
        // consume next token until EOF
        for (Token token = lex.nextToken();
             token.getType() != Token.EOF;
             token = lex.nextToken()){
            
            int type = token.getType();
            String rule = lex.ruleNames[token.getType()-1];
            if (rule.equals("NAME")) {
                output.append("name ");
            } else if (rule.equals("CLASSNAME")
            || rule.equals("TYPEPARAM")
            || rule.equals("THING")
            || rule.equals("NOTHING")) {
                output.append("Name ");
            } else if (rule.equals("STRING")) {
                output.append("\"\" ");
            } else if (rule.equals("INT")) {
                output.append("0 ");
            } else if (rule.equals("BOOL")) {
                output.append("true ");
            } else if (rule.equals("ERRORCHAR")) {
                return "error";
            } else {
                output.append(token.getText()+" ");
            }
        }

        if (output.length() > 0 && output.charAt(output.length()-1) == ' ') {
            output.deleteCharAt(output.length()-1);
        }
        return output.toString();
    }
}