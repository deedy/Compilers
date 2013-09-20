import org.antlr.v4.runtime.*;
import java.lang.StringBuilder;
import java.util.BitSet;


public class CubexParserProg {
    public static void main(String[] args) throws Exception {
        // lex the file
        CubexLexer lex = new CubexLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        CubexParser par = new CubexParser(tokens);
        System.out.print(assignment2(par));
    }

    public static String assignment2(CubexParser par) {
        System.out.println("assignment 2 not implemented");
        return "";
    }
}