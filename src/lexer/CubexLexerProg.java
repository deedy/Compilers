import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import java.lang.StringBuilder;

public class CubexLexerProg {
    public static void main(String[] args) throws Exception {
        // lex the file
        CubexLexer lex = new CubexLexer(new ANTLRFileStream(args[0]));
        System.out.print(assignment1(lex));
    }

    public static String assignment1(CubexLexer lex) {
        lex.removeErrorListeners();
        StringBuilder output = new StringBuilder();
        // consume next token until EOF
        for (Token token = lex.nextToken();
             token.getType() != Token.EOF;
             token = lex.nextToken()){
            
            int type = token.getType();
            // if (type == 0) {
            //     output.delete(0, output.length());
            //     output.append("error");
            //     break;
            // }
            String rule = lex.ruleNames[token.getType()-1];
            if (rule.equals("NAME")) {
                output.append("name ");
            } else if (rule.equals("TYPE")) {
                output.append("Name ");
            } else if (rule.equals("STRING")) {
                output.append("\"\" ");
            } else if (rule.equals("INT")) {
                output.append("0 ");
            } else if (rule.equals("BOOL")) {
                output.append("true ");
            } else {
                output.append(token.getText()+" ");
            }
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }
}