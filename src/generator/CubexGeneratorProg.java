import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.lang.StringBuilder;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.*;

public class CubexGeneratorProg {

    public static void main(String[] args) {
        // lex it
        CubexLexer lex;
        CubexProg prog;
        try {
            if ((lex = CLexer.lex(args[0])) == null) {
                writeOut(reject());
                return;
            } 
            if ((prog = CParser.parse(lex)) == null) {
                writeOut(reject());
                return;
            }
            if (!CChecker.check(prog)) {
                writeOut(reject());
                return;
            }
            // System.out.println(prog);
            String out = generate(prog);
            writeOut(out);
        } catch (Exception e) {
            writeOut(reject());
            e.printStackTrace();
        }
    }

    public static String generate(CubexProg node) {
        HVisitor hVisitor = new HVisitor();
        HNode hRoot = node.accept(hVisitor);
        // Implement unimplemented 
        for (Map.Entry<String, HInterface> i : hVisitor.classes.entrySet()) {
            HInterface in = i.getValue();
            in.implementSuperInterfaces(hVisitor.classes);
            in.convertFuns(in.funs);
        }
        
        HighLow hl = new HighLow();
        LNode lRoot = hRoot.accept(hl);
        
        CGenerator cc = new CGenerator();
        return lRoot.accept(cc);
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
                    + "_print(String_construct(\"reject\"));\n"
                + "}\n";
    }
}