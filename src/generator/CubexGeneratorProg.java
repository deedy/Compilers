import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.lang.StringBuilder;
import java.util.BitSet;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.*;

public class CubexGeneratorProg {

    public static void main(String[] args) {
        // lex it
        CubexLexer lex = CLexer.lex(args[0]);
        if (lex == null) {
            writeOut(reject());
        } else {
            // parse it
            CubexParser parser = CParser.parse(lex);
            if (parser == null) {
                writeOut(reject());
            } else {
                // check it
                CubexNode root = CChecker.check(parser);
                if (root == null) {
                    writeOut(reject());
                } else {
                    // work it
                    writeOut(generate(root));
                }
            }
        }
    }

    public static String generate(CubexNode node) {
        return "";
    }

    public static void writeOut(String s) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("out.c"));
            writer.write(s);
        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) writer.close( );
            } catch ( IOException e) {
            }
        }
    }
 
    public static String reject() {
        return "#include \"cubex_main.h\"\n"
                + "#include \"cubex_external_functions.h\"\n"
                + "#include \"cubex_lib.h\"\n"
                + "void cubex_main() {\n"
                    + "_IterNode _i_iter = _iterator(input);\n"
                    + "_print(\"reject\");\n"
                + "}\n";
    }
}