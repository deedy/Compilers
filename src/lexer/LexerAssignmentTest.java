import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;

public class LexerAssignmentTest {
    public static void main(String[] args) throws Exception {
    	// lex the file
        CubexLexer lex = new CubexLexer(new ANTLRFileStream(args[0]));
        // make a token stream (may be unnecessary for now)
        CommonTokenStream tokens = new CommonTokenStream(lex);
		// consume next token until EOF
		for (Token token = lex.nextToken();
		     token.getType() != Token.EOF;
		     token = lex.nextToken()){
			
            int type = token.getType();
            if(type > 0){
                System.out.print(lex.ruleNames[token.getType()]);
            } 
            System.out.printf(" %s\n", token.getText());
        }
    }
}