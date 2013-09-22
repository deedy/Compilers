import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.lang.StringBuilder;
import java.util.BitSet;
import java.util.List;


public class CubexParserProg {
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
        // System.out.println("assignment 2 not implemented");
        StringBuilder output = new StringBuilder();
        parser.setBuildParseTree(true);
        ParserRuleContext tree = parser.prog();
        // List<ParseTree> l = parser.prog().children;
        // if(l.size() > 0){
        //     output.append(l.get(0));
        //     for(int i = 1; i < l.size(); i++){
        //         ParseTree s = l.get(i);
        //         output.append(" ");
        //         output.append(s.toString());
        //     }
        // }
        // return output.toString();

        return tree.toStringTree(parser);
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