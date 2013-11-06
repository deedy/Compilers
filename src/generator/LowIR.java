import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

abstract class LNode {
	public abstract String accept(LVisitor v);
}

abstract class LExp extends LNode {
	public abstract LExp convertFields(Map<String, Integer> map);
}

class LExps extends LExp {
	List<? extends LExp> exps;

	public LExps(List<? extends LExp> lst) {
		exps = lst;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}

	public LExp convertFields(Map<String, Integer> map) {
		List<LExp> out = new ArrayList<LExp>();
		for (LExp e : exps) {
			out.add(e.convertFields(map));
		}
		return new LExps(out);
	}	
}

/* null value */
class LNull extends LExp {

	public LNull () {

	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}

	public LExp convertFields(Map<String, Integer> map) {
		return this;
	}
}

/* variables */
class LName extends LExp {
	String name;
	static final List<String> badNames = Arrays.asList("auto","break", "case", "char", "const","continue","default","do","double","else"
		,"enum","extern","float","for","goto","if","int","long","register","return","short","signed"
		,"sizeof","static","struct","switch","typedef","union","unsigned","void","volatile","while");
	public LName(String s) {
		if (badNames.contains(s)) {
			name =  "sexxxpanther_default_swag_"+s+ "_";
		} else {
			name = s;
		}
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}

	public LExp convertFields(Map<String, Integer> map) {
		if(map.containsKey(name)){
			return new LFieldAccess(new LName("_obj"), map.get(name).intValue());
		} else {
			return this;
		}
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

	public LExp convertFields(Map<String, Integer> map) {
		return this;
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

	public LExp convertFields(Map<String, Integer> map) {
		return this;
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

	public LExp convertFields(Map<String, Integer> map) {
		return this;
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

	public LExp convertFields(Map<String, Integer> map) {
		return this;
	}
}

class LId extends LExp {
	LName name;
	int id;

	public LId(LName n, int i) {
		name = n;
		id = i;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}

	public LExp convertFields(Map<String, Integer> map) {
		return this;
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

	public LExp convertFields(Map<String, Integer> map) {
		return new LFunCall(name, args.convertFields(map));
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
	public LExp convertFields(Map<String, Integer> map) {
		List<LExp> out = new ArrayList<LExp>();
		for (LExp e : items) {
			out.add(e.convertFields(map));
		}
		return new LIter(out);
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

	public LExp convertFields(Map<String, Integer> map) {
		return new LAppend(iter1.convertFields(map), iter2.convertFields(map));
	}
}

abstract class LStmt extends LNode {
	public abstract LStmt convertFields(Map<String, Integer> map);
}

class LStmts extends LStmt {
	List<? extends LStmt> stmts;

	public LStmts(List<? extends LStmt> lst) {
		stmts = lst;
	}

	public String accept(LVisitor v) {
		return v.visit(this);
	}

	public LStmt convertFields(Map<String, Integer> map) {
		List<LStmt> out = new ArrayList<LStmt>();
		for (LStmt s : stmts) {
			out.add(s.convertFields(map));
		}
		return new LStmts(out);
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

	public LStmt convertFields(Map<String, Integer> map) {
		return new LFor(iter.convertFields(map), elem, stmt.convertFields(map));
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

	public LStmt convertFields(Map<String, Integer> map) {
		return new LWhile(cond.convertFields(map), stmt.convertFields(map));
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

	public LStmt convertFields(Map<String, Integer> map) {
		LStmt s1 = ifBlock.convertFields(map);
		LStmt s2 = elseBlock.convertFields(map);
		LExp c = cond.convertFields(map);
		return new LCond(c, s1, s2);
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

	public LStmt convertFields(Map<String, Integer> map) {
		return new LAssign((LName) var.convertFields(map), val.convertFields(map));
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

	public LStmt convertFields(Map<String, Integer> map) {
		return new LReturn(ret.convertFields(map));
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

	public void convertFields(Map<String, Integer> map) {
		stmts.convertFields(map);
		for (LName n : args) {
			n.convertFields(map);
		}
	}
}

class LConstructor extends LFunc {
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

	public void convertFields(Map<String, Integer> map) {
		name.convertFields(map);
		stmts.convertFields(map);
		parent.convertFields(map);
		for (LName n : args) {
			n.convertFields(map);
		}
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

	public void convertFields(Map<String, Integer> map) {
		return;
	}
}