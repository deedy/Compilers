import java.util.List;
import java.util.ArrayList;


abstract class CubexProg extends CubexNode {
	public CubexProg prog;
	public abstract boolean typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st);
	public abstract HNode accept(HVisitor c);
}

class CubexStatementProg extends CubexProg {
	public CubexStatement stmt;
	public CubexStatementProg(CubexStatement stat){
		stmt = stat;
	}

	public String toString() {
		return stmt.toString();
	}

	public boolean typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
		// lone statements must return iterable of string
		Triple<SymbolTable, Boolean, CubexType> imm = stmt.typeCheck(cc, new CubexKindContext(), fc, st, new SymbolTable());
		if(!imm.getMiddle().booleanValue()){
			throw new CubexTC.TypeCheckException(
                String.format("MAIN STATEMENT %s MUST ALWAYS RETURN", 
                    stmt.toString())
                );
		}
		// create an iterable of string
		ArrayList<CubexType> param = new ArrayList<CubexType>();
		param.add(new CubexCType("String"));
		CubexType itString = new CubexCType(new CubexCName("Iterable"), param);
		if(!CubexTC.subType(cc,  new CubexKindContext(), imm.getRight(), itString)) {
			throw new CubexTC.TypeCheckException(
                String.format("%s DOES NOT RETURN ITERABLE OF STRING", 
                    stmt.toString())
                );
		}
		return true;
	}
	public HNode accept(HVisitor c) {
        return null;
    }
}

class CubexStatementsProg extends CubexProg {
	public List<CubexStatement> stmts;

	public CubexStatementsProg(List<CubexStatement> stats, CubexProg prog){
		stmts = stats;
		this.prog = prog;
	}

	public String toString() {
		return ListPrinter.listToString(stmts, " ") + " " + prog.toString();
	}

	public boolean typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
		// create a new block
		CubexBlock block = new CubexBlock(stmts);
		Triple<SymbolTable, Boolean, CubexType> imm = block.typeCheck(cc, new CubexKindContext(), fc, st, new SymbolTable());
		// create an iterable of string
		ArrayList<CubexType> param = new ArrayList<CubexType>();
		param.add(new CubexCType("String"));
		CubexType itString = new CubexCType(new CubexCName("Iterable"), param);
		if(!CubexTC.subType(cc,  new CubexKindContext(), imm.getRight(), itString)) {
			throw new CubexTC.TypeCheckException(
                String.format("%s DOES NOT RETURN ITERABLE OF STRING", 
                    block.toString())
                );
		}
		return prog.typeCheck(cc, fc, st.merge(imm.getLeft()));	
	}
	public HNode accept(HVisitor c) {
        return null;
    }
}

class CubexFuncsProg extends CubexProg {
	public List<CubexFunction> funcs;

	public CubexFuncsProg(List<CubexFunction> funs, CubexProg prog){
		funcs = funs;
		this.prog = prog;
	}

	public String toString() {
		return ListPrinter.listToString(funcs, " ") + " " + prog.toString();
	}

	public boolean typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
		// add all functions to fc
		CubexFunctionContext fold = fc;
		for(CubexFunction fun : funcs) {
			fold = fold.set(fun.name, fun);
		}
		for(CubexFunction fun : funcs) {
			CubexTypeScheme scheme = fun.scheme;
			CubexKindContext kc = new CubexKindContext(scheme.kCont);
			CubexTypeContext tc = scheme.tCont;
			SymbolTable mutable = new SymbolTable(tc);
			CubexType retType = scheme.type;
			// check type context
			if(!CubexTC.isValid(cc, kc, tc)) {
				throw new CubexTC.TypeCheckException(
	                String.format("FUNCTION %s HAS INVALID TYPE CONTEXT %s", 
	                    fun.name.toString(), tc.toString())
	                );
			}

			// check return type
			if(!CubexTC.isValid(cc, kc, retType)) {
				throw new CubexTC.TypeCheckException(
	                String.format("FUNCTION %s HAS INVALID RETURN TYPE %s", 
	                    fun.name.toString(), retType.toString())
	                );
			}

			// check the statement
            Triple<SymbolTable, Boolean, CubexType> ret = fun.body.typeCheck(cc, kc, fold, st, mutable);
            // check that it returns
            if(!ret.getMiddle().booleanValue()){
                throw new CubexTC.TypeCheckException(
                    String.format("FUNCTION %s HAS NON-RETURNING BODY", 
                        fun.name.toString())
                    ); 
            }
            // check that it returns a subtype of its declared type
            if(!CubexTC.subType(cc, kc, ret.getRight(), retType)){
                throw new CubexTC.TypeCheckException(
                    String.format("FUNCTION %s RETURNS %s EXPECTED %s", 
                        fun.name.toString(), ret.getRight().toString(), retType.toString())
                    ); 
            }
		}
		return prog.typeCheck(cc, fold, st);	
	}
	public HNode accept(HVisitor c) {
        return null;
    }
}

class CubexInterfaceProg extends CubexProg {
	public CubexInterface intf;

	public CubexInterfaceProg(CubexInterface inter, CubexProg prog){
		intf = inter;
		this.prog = prog;
	}

	public String toString() {
		return intf.toString() + " " + prog.toString();
	}

	public boolean typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
		Pair<CubexClassContext, CubexFunctionContext> imm = intf.typeCheck(cc, fc, st);
		return prog.typeCheck(cc.merge(imm.getLeft()), fc, st);
	}
	public HNode accept(HVisitor c) {
        return null;
    }
}

class CubexClassProg extends CubexProg {
	public CubexClass cls;

	public CubexClassProg(CubexClass clss, CubexProg prog){
		cls = clss;
		this.prog = prog;
	}

	public String toString() {
		return cls.toString() + " " + prog.toString();
	}

	public boolean typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
		Pair<CubexClassContext, CubexFunctionContext> imm = cls.typeCheck(cc, fc, st);
		return prog.typeCheck(cc.merge(imm.getLeft()), fc.merge(imm.getRight()), st);
	}
	public HNode accept(HVisitor c) {
        return null;
    }
}