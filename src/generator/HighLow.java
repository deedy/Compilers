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
	int classID = 5;

	public LNode visit(HInterface i) {
		return null;
	}

	public LNode visit(HClass c) {
		List<LFieldAccess> fa = new ArrayList<LFieldAccess>();
		int fieldNum = 0;
		for (String s : c.fields) {
			LFieldAccess f = new LFieldAccess(new LName(s), fieldNum);
			fa.add(f);
			fieldNum++;
		}

		// Create the mapping from field name to field num
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (LFieldAccess a : fa) {
			map.put(a.obj.name, a.field); 
		}

		// get the list of arguments
		List<LName> args = new ArrayList<LName>();
		for(CubexName n : c.tCont.names) {
			args.add(new LName(n.name));
		}

		// get the parent 

		LExp parentCall = new LNull();
		if (c.parent != null) {
			parentCall = (LExp) c.parent.accept(this);
		}

		classID += 1;

		// convert list of statements to use field accesses

		List<LStmt> stmts = new ArrayList<LStmt>();
		for (HStatement s : c.stmts) {
			LStmt st = (LStmt)s.accept(this);
			stmts.add(st);
			st.convertFields(map);
		}

		funcs.add(new LConstructor(new LName(c.name), args, classID - 1, fieldNum, parentCall, new LStmts(stmts)));

		// add all methods of the class to the global fun list
		// convert functions to use field access
		for (Map.Entry<String, HFunction> f : c.funs.entrySet()) {
			LFunc lf = (LFunc)f.getValue().accept(this);
			lf.name.name = c.name + "_" + lf.name.name;
			funcs.add(lf);
			lf.convertFields(map);
		}

		classID += 1;

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
		LName name = new LName(f.name);
		List<LName> args = new ArrayList<LName>();
		for(String s : f.args) {
			args.add(new LName(s));
		}
		LStmt body = (LStmt) f.body.accept(this);
		return new LFunc(name, args, body);
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
		// this will add functions on its own
		LNode visited = c.cls.accept(this);
		if(c.prog == null) {
			return new LProg(globals, funcs, new LStmts(topLevels));
		} else {
			return c.prog.accept(this);
		}
	}

	public LNode visit(HFunProg f) {
		List<LFunc> funs = new ArrayList<LFunc>();
		for (HFunction fn : f.funs) {
			funs.add((LFunc) fn.accept(this));
		}
		funcs.addAll(funs);
		if(f.prog == null) {
			return new LProg(globals, funcs, new LStmts(topLevels));
		} else {
			return f.prog.accept(this);
		}
	}
}