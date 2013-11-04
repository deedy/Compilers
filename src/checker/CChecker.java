import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CChecker {
	
	public static CubexProg check(CubexParser par) {
		try {
            CubexProg prog = par.prog().cu;
            // System.out.println(prog);
            Triple<CubexClassContext, CubexFunctionContext, SymbolTable> trip = buildBase();
            if(prog.typeCheck(trip.getLeft(), trip.getMiddle(), trip.getRight())) {
            	return prog;
            } else {
            	System.out.println("Checker Error");
            	return null;
            }
        } catch(Exception e){
            System.out.println("Checker Error");
            e.printStackTrace();
            return null;
        }		
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
}