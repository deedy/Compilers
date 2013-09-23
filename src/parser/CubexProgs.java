import java.util.List;
// this class holds the list of all progs parsed
public class CubexProgs{
	public List<CubexProg> progs;
	public CubexProgs(List<CubexProg> l){
		progs = l;
	}
	public String toString(){
		return ListPrinter.listToString(progs, " ");
	}
}

class CubexProg {
}

class CubexStatementProg extends CubexProg {
	public CubexStatement stmt;
	public CubexStatementProg(CubexStatement stat){
		stmt = stat;
	}

	public String toString() {
		return stmt.toString();
	}
}

class CubexStatementsProg extends CubexProg {
	public List<CubexStatement> stmts;
	public CubexStatementsProg(List<CubexStatement> stats){
		stmts = stats;
	}

	public String toString() {
		return ListPrinter.listToString(stmts, " ");
	}
}

class CubexFunctionProg extends CubexProg {
	public CubexFunction func;
	public CubexFunctionProg(CubexFunction fun){
		func = fun;
	}

	public String toString() {
		return func.toString();
	}
}

class CubexFuncsProg extends CubexProg {
	public List<CubexFunction> funcs;
	public CubexFuncsProg(List<CubexFunction> funs){
		funcs = funs;
	}

	public String toString() {
		return ListPrinter.listToString(funcs, " ");
	}
}

class CubexInterfaceProg extends CubexProg {
	public CubexInterface intf;
	public CubexInterfaceProg(CubexInterface inter){
		intf = inter;
	}

	public String toString() {
		return intf.toString();
	}
}

class CubexClassProg extends CubexProg {
	public CubexClass cls;
	public CubexClassProg(CubexClass clss){
		cls = clss;
	}

	public String toString() {
		return cls.toString();
	}
}