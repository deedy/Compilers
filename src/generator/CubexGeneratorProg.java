import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.lang.StringBuilder;
import java.util.BitSet;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.*;

public class CubexGeneratorProg {

    public static void main(String[] args) throws Exception {
        // lex it
        CubexLexer lex = new CubexLexer(new ANTLRFileStream(args[0]));
        lex.removeErrorListeners();        
        if (hasLexerError(lex)) {
            writeOut(reject());
        } else {
            try {
                // parse it
                CommonTokenStream tokens = new CommonTokenStream(lex);
                CubexParser par = new CubexParser(tokens);
                par.removeErrorListeners();
                // check it
                CubexProg prog = par.prog().cu;
                Triple<CubexClassContext, CubexFunctionContext, SymbolTable> trip = buildBase();
                if(prog.typeCheck(trip.getLeft(), trip.getMiddle(), trip.getRight())) {
                    // generate it
                    writeOut(generate(prog));
                }
            } catch (Exception e) {
                e.printStackTrace();
                writeOut(reject());
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

    public static Triple<CubexClassContext, CubexFunctionContext, SymbolTable> buildBase() {
        try {
            StringBuffer sb = new StringBuffer();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.class.getResourceAsStream("/checker/base_classes.cx"), "UTF-8"));
            for (int c = br.read(); c != -1; c = br.read()) sb.append((char)c);
  
            CubexLexer baseLex = new CubexLexer(new ANTLRInputStream(sb.toString()));
            CommonTokenStream baseTokens = new CommonTokenStream(baseLex);
            CubexParser basePar = new CubexParser(baseTokens);
            basePar.setBuildParseTree(true);
            CubexProg based = basePar.prog().cu;

            CubexClassContext cc = new CubexClassContext();
            CubexFunctionContext fc = new CubexFunctionContext();
            SymbolTable st = new SymbolTable();

            // follow until end
            while(based.prog != null) {
                // check instance
                if(based instanceof CubexClassProg) {
                    CubexClassProg b = (CubexClassProg) based;
                    cc = cc.set(b.cls.name, b.cls);
                } else if (based instanceof CubexInterfaceProg) {
                    CubexInterfaceProg b = (CubexInterfaceProg) based;
                    cc = cc.set(b.intf.name, b.intf);
                } else if (based instanceof CubexFuncsProg) {
                    List<CubexFunction> funs = ((CubexFuncsProg) based).funcs;
                    for(CubexFunction fun : funs) {
                        fc = fc.set(fun.name, fun);
                    }
                }
                ArrayList<CubexType> param = new ArrayList<CubexType>();
                param.add(new CubexCType("String"));
                CubexType itString = new CubexCType(new CubexCName("Iterable"), param);
                st = st.set(new CubexVName("input"), itString);
                based = based.prog;
            }
            return new Triple<CubexClassContext, CubexFunctionContext, SymbolTable>(cc, fc, st);
            } catch(Exception e) {
                e.printStackTrace();
                return null;
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