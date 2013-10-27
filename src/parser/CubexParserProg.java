import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.lang.StringBuilder;
import java.util.BitSet;
import java.util.List;


public class CubexParserProg extends CubexNode implements HVisitor {
    public static void main(String[] args) throws Exception {
        // lex the file
        CubexLexer lex = new CubexLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        CubexParser par = new CubexParser(tokens);
        System.out.print(assignment2(lex, par));
    }

    public static String assignment2(CubexLexer lex, CubexParser parser) {
        if (hasLexerError(lex)) {
            return "lexer error";
        }
        StringBuilder output = new StringBuilder();
        parser.setBuildParseTree(true);
        try {
            CubexProg prog = parser.prog().cu;
            return prog.toString();
        } catch(Exception e){
            // e.printStackTrace();
            // a divide by zero error was thrown by the error char
            // any other exception we did not make
            // reset the parser and view tree
            // parser.reset();
            // viewTree(parser);
            return "parser error";
        }
    }

    public static void viewTree(CubexParser parser) {
        ParserRuleContext tree = parser.prog();
        tree.inspect(parser);
        parser.reset();
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

    public HNode visit(CubexNode c) {
        return null;
    }
}