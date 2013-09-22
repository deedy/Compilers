import java.util.List;
// this class holds the list of all progs parsed
public class CubexProgs{
	public List<CubexProg> progs;
	public CubexProgs(List<CubexProg> l){
		progs = l;
	}
	public String toString(){
		return "You found me!";
	}
}

class CubexProg {
}

class CubexStatementProg extends CubexProg {
	public CubexStatement stmt;
	public CubexStatementProg(CubexStatement stat){
		stmt = stat;
	}
}

class CubexStatementsProg extends CubexProg {
	public List<CubexStatement> stmts;
	public CubexStatementsProg(List<CubexStatement> stats){
		stmts = stats;
	}
}

class CubexFuncsProg extends CubexProg {
	public List<CubexFunction> funcs;
	public CubexFuncsProg(List<CubexFunction> funs){
		funcs = funs;
	}
}

class CubexInterfaceProg extends CubexProg {
	public CubexInterface intf;
	public CubexInterfaceProg(CubexInterface inter){
		intf = inter;
	}
}

class CubexClassProg extends CubexProg {
	public CubexClass cls;
	public CubexClassProg(CubexClass clss){
		cls = clss;
	}
}