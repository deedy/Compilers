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
        tree.inspect(parser);
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
        traverse(tree);
        return tree.toStringTree(parser);
    }

    public static void traverse(ParserRuleContext tree) {
        System.out.printn
        for (ParseTree pt : tree.children) {
            System.out.println("TREE");
            traverseTree(0,pt);
        }
    }

    public static void traverseTree(int  j, ParseTree t) {
        System.out.println(j+"\t"+t.getText()+"\t");
        for (int i = 0;i < t.getChildCount(); i++) {
            traverseTree(j+1, t.getChild(i));
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