import java.util.*;

interface HLVisitor {
	public LNode visit(HInterface i);
	public LNode visit(HClass c);
	public LNode visit(HConditional c);
	public LNode visit(HForLoop f);
	public LNode visit(HWhileLoop w);
	public LNode visit(HReturn r);
	public LNode visit(HBlock b);
	public LNode visit(HAssign a);
	public LNode visit(HFunction f);
	public LNode visit(HUndefFunction f);
	public LNode visit(HFunctionCall fc);
	public LNode visit(HAppend a);
	public LNode visit(HIterable i);
	public LNode visit(HBoolean b);
	public LNode visit(HInt i);
	public LNode visit(HString s);
	public LNode visit(HVar v);
	public LNode visit(HStatementProg s);
	public LNode visit(HClassProg c);
	public LNode visit(HFunProg f);
}

public class HighLow implements HLVisitor {
	List<LStmt> topLevels = new ArrayList<LStmt>();
	List<LName> globals = new ArrayList<LName>();
	List<LFunc> funcs = new ArrayList<LFunc>();

	public LNode visit(HInterface i) {
		return null;
	}

	public LNode visit(HClass c) {
		return null;
	}

	public LNode visit(HConditional c) {
		LExp exp = (LExp) c.expr.accept(this);
		LStmt s1 = (LStmt) c.stmt1.accept(this);
		LStmt s2 = (LStmt) c.stmt2.accept(this);
		return new LCond(exp, s1, s2);
	}

	public LNode visit(HForLoop f) {
		LExp iter = (LExp) f.expr.accept(this);
		LName elem = new LName(f.name);
		LStmt stmt = (LStmt) f.stmt.accept(this);
		return new LFor(iter, elem, stmt);
	}

	public LNode visit(HWhileLoop w) {
		LExp cond = (LExp) w.expr.accept(this);
		LStmt stmt = (LStmt) w.stmt.accept(this);
		return new LWhile(cond, stmt);
	}

	public LNode visit(HReturn r) {
		LExp ret = (LExp) r.expr.accept(this);
		return new LReturn(ret);
	}

	public LNode visit(HBlock b) {
		List<LStmt> stmts = new ArrayList<LStmt>();
		for (HStatement s : b.stmts) {
			stmts.add((LStmt) s.accept(this));
		}
		return new LStmts(stmts);
	}

	public LNode visit(HAssign a) {
		LExp exp = (LExp) a.expr.accept(this);
		LName name = new LName(a.name);
		return new LAssign(name, exp);
	}

	public LNode visit(HFunction f) {
		return null;
	}

	public LNode visit(HUndefFunction f) {
		return null;
	}

	public LNode visit(HFunctionCall fc) {
		List<LExp> exprs = new ArrayList<LExp>();
		for(HExpression e : fc.args) {
			exprs.add((LExp) e.accept(this));
		}
		LExps args = new LExps(exprs);
		LName name = new LName(fc.name);
		return new LFunCall(name, args);
	}

	public LNode visit(HAppend a) {
		LExp left = (LExp) a.left.accept(this);
		LExp right = (LExp) a.right.accept(this);
		return new LAppend(left, right);
	}

	public LNode visit(HIterable i) {
		List<LExp> exprs = new ArrayList<LExp>();
		for(HExpression e : i.elems) {
			exprs.add((LExp) e.accept(this));
		}
		return new LIter(exprs);
	}

	public LNode visit(HBoolean b) {
		if(b.val.equals("true")) {
			return new LBool(true);
		} else {
			return new LBool(false);
		}
	}

	public LNode visit(HInt i) {
		return new LNum(i.val);
	}

	public LNode visit(HString s) {
		return new LString(s.val);
	}

	public LNode visit(HVar v) {
		return new LName(v.var);
	}

	public LNode visit(HStatementProg s) {
		List<LStmt> stmts = new ArrayList<LStmt>();
		for (HStatement st : s.stmts) {
			stmts.add((LStmt) st.accept(this));
		}
		topLevels.addAll(stmts);
		if(s.prog == null) {
			return new LProg(globals, funcs, new LStmts(topLevels));
		} else {
			return s.prog.accept(this);
		}

	}
	public LNode visit(HClassProg c) {
		return null;
	}

	public LNode visit(HFunProg f) {
		return null;
	}
}