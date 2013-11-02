import java.util.Map;
import java.util.List;

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

/* only garbage collect args, not locals */
class LConstructor extends LNode {
	LName name;
	List<LName> args;
	LStmt stmts;
	int id;
	int fields;

	public LConstructor(LName n, List<LName> a, int i, int f, LStmt s) {
		name = n;
		args = a;
		stmts = s;
		id = i;
		fields = f;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}
}