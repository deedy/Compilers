import java.util.Map;
import java.util.List;
import java.util.ArrayList;

abstract class LNode {
	public abstract String accept(LVisitor v);
}

abstract class LExp extends LNode {

}

class LExps extends LExp {
	List<? extends LExp> exps;

	public LExps(List<? extends LExp> lst) {
		exps = lst;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

/* null value */
class LNull extends LExp {

	public LNull () {

	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

/* variables */
class LName extends LExp {
	String name;

	public LName(String s) {
		name = s;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LFieldAccess extends LName {
	LName obj;
	int field;

	public LFieldAccess(LName n, int f) {
		super(n.name);
		obj = n;
		field = f;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

/* Integer literal */
class LNum extends LExp {
	int value;

	public LNum(int val) {
		value = val;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

/* Boolean literal */
class LBool extends LExp {
	boolean value;

	public LBool (boolean b) {
		value = b;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

/* String literal */
class LString extends LExp {
	String value;

	public LString(String s) {
		value = s;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LFunCall extends LExp {
	LName name;
	LExp args;

	public LFunCall(LName n, LExp e) {
		name = n;
		args = e;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LIter extends LExp {
	List<LExp> items;

	public LIter(List<LExp> i) {
		items = i;
	}

	public LIter(LExp e) {
		items = new ArrayList<LExp>();
		items.add(e);
	}

	public LIter(LExps e) {
		items = new ArrayList<LExp>();
		items.addAll(e.exps);
	}

	public LIter() {
		items = new ArrayList<LExp>();
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}

}

class LAppend extends LExp {
	LExp iter1;
	LExp iter2;

	public LAppend(LExp i1, LExp i2) {
		iter1 = i1;
		iter2 = i2;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

abstract class LStmt extends LNode {

}

class LStmts extends LStmt {
	List<? extends LStmt> stmts;

	public LStmts(List<? extends LStmt> lst) {
		stmts = lst;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LFor extends LStmt {
	LExp iter;
	LName elem;
	LStmt stmt;

	public LFor(LExp i, LName e, LStmt s) {
		iter = i;
		elem = e;
		stmt = s;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LWhile extends LStmt {
	LExp cond;
	LStmt stmt;

	public LWhile(LExp c, LStmt s) {
		cond = c;
		stmt = s;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LCond extends LStmt {
	LExp cond;
	LStmt ifBlock;
	LStmt elseBlock;

	public LCond(LExp c, LStmt i, LStmt e) {
		cond = c;
		ifBlock = i;
		elseBlock = e;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LAssign extends LStmt {
	LName var;
	LExp val;

	public LAssign(LName n, LExp v) {
		var = n;
		val = v;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LReturn extends LStmt {
	LExp ret;

	public LReturn(LExp r) {
		ret = r;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

/* define a function by id, list  */
class LFunc extends LNode {
	LName name;
	List<LName> args;
	LStmt stmts;

	public LFunc(LName n, List<LName> a, LStmt s) {
		name = n;
		args = a;
		stmts = s;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LConstructor extends LFunc {
	LName name;
	List<LName> args;
	LStmt stmts;
	int id;
	int fields;
	LExp parent;

	public LConstructor(LName n, List<LName> a, int i, int f, LExp p, LStmt s) {
		super(n, a, s);
		id = i;
		fields = f;
		parent = p;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}

class LProg extends LNode {
	List<LName> globals;
	List<LFunc> funcs;
	LStmt stmts;

	public LProg(List<LName> g, List<LFunc> f, LStmt s) {
		globals = g;
		funcs = f;
		stmts = s;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}