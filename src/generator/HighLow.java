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
		// assert each undefined function
		for (Map.Entry<String, HFunction> f : i.funs.entrySet()) {
			f.getValue().args.add(0, "_obj");
			LFunc lf = (LFunc)f.getValue().accept(this);
			lf.name.name = i.name + "_" + lf.name.name;
			funcs.add(lf);
		}

		return null;
	}

	public LNode visit(HClass c) {
		int fieldNum = 0;
		// Create the mapping from field name to field num
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// System.out.println(c.fields);
		for (String s : c.fields) {
			map.put(s, fieldNum);
			fieldNum += 1; 
		}

		// System.out.println(map);
		// get the list of arguments
		List<LStmt> stmts = new ArrayList<LStmt>();
		List<LName> args = new ArrayList<LName>();
		int count = 1;
		for(CubexName n : c.tCont.names) {
			args.add(new LName(n.name));
			stmts.add(new LAssign(new LFieldAccess(new LName("_obj"), c.fields.indexOf(n.name)), new LName(n.name)));
		}

		// get the parent 

		LExp parentCall = new LNull();
		if (c.parent != null) {
			parentCall = (LExp) c.parent.accept(this);
		}

		classID += 1;

		// convert list of statements to use field accesses
		for (HStatement s : c.stmts) {
			LStmt st = (LStmt)s.accept(this);
			stmts.add(st.convertFields(map));
		}

		funcs.add(new LConstructor(new LName(c.name), args, classID, fieldNum, parentCall, new LStmts(stmts)));

		// add all methods of the class to the global fun list
		// convert functions to use field access
		for (Map.Entry<String, HFunction> f : c.funs.entrySet()) {
			f.getValue().args.add(0, "_obj");
			LFunc lf = (LFunc)f.getValue().accept(this);
			lf.name.name = c.name + "_" + lf.name.name;
			funcs.add(lf);
			lf.stmts = lf.stmts.convertFields(map);
		}

		return null;
	}

	public LNode visit(HConditional c) {
		LExp exp = (LExp) c.expr.accept(this);
		LStmt s1 = (LStmt) c.stmt1.accept(this);
		LStmt s2 = (LStmt) c.stmt2.accept(this);
		LCond cond = new LCond(exp, s1, s2);
		if (c.newDeclarations.stmts.size() > 0) {
			LStmts stmts = (LStmts) c.newDeclarations.accept(this);
			stmts.stmts.add(cond);
			return stmts;
		}
		return cond;
	}

	public LNode visit(HForLoop f) {
		LExp iter = (LExp) f.expr.accept(this);
		LName elem = new LName(f.name);
		LStmt stmt = (LStmt) f.stmt.accept(this);
		LFor fo = new LFor(iter, elem, stmt);
		if (f.newDeclarations.stmts.size() > 0) {
			LStmts stmts = (LStmts) f.newDeclarations.accept(this);
			stmts.stmts.add(fo);
			return stmts;
		}
		return fo;
	}

	public LNode visit(HWhileLoop w) {
		LExp cond = (LExp) w.expr.accept(this);
		LStmt stmt = (LStmt) w.stmt.accept(this);
		LWhile wh = new LWhile(cond, stmt);
		if (w.newDeclarations.stmts.size() > 0) {
			LStmts stmts = (LStmts) w.newDeclarations.accept(this);
			stmts.stmts.add(wh);
			return stmts;
		}
		return wh;
	}

	public LNode visit(HReturn r) {
		LExp e = (LExp) r.expr.accept(this);
		LReturn ret = new LReturn(e);
		if (r.newDeclarations.stmts.size() > 0) {
			LStmts stmts = (LStmts) r.newDeclarations.accept(this);
			stmts.stmts.add(ret);
			return stmts;
		}
		return ret;
	}

	public LNode visit(HBlock b) {
		List<LStmt> stmts = new ArrayList<LStmt>();
		for (HStatement s : b.stmts) {
			stmts.add((LStmt) s.accept(this));
		}
		for (HStatement s : b.newDeclarations.stmts) {
			stmts.add((LStmt) s.accept(this));
		}

		return new LStmts(stmts);
	}

	public LNode visit(HAssign a) {
		LExp exp = (LExp) a.expr.accept(this);
		LName name = new LName(a.name);
		LAssign ass = new LAssign(name, exp);
		if (a.newDeclarations.stmts.size() > 0) {
			LStmts stmts = (LStmts) a.newDeclarations.accept(this);
			stmts.stmts.add(ass);
			return stmts;
		}
		return ass;
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
		LName name = new LName(f.name);
		List<LName> args = new ArrayList<LName>();
		for(String s : f.args) {
			args.add(new LName(s));
		}
		LExp exArgs = new LExps(args);
		// create a conditional chain
		LStmt fold = new LReturn(new LNull());
		for (Integer i : f.defs.keySet()) {
			int j = i.intValue();
			HFunction hc = f.defs.get(i);
			// make a function call
			LFunCall lc = new LFunCall(new LName(hc.declassedName), exArgs);
			fold = new LCond(new LId(new LName("_obj"), j), 
				new LReturn(lc), fold);
		}
		return new LFunc(name, args, fold);
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
		if (a.newExpr != null) {
			return new LName(a.newExpr.var);
		}
		LExp left = (LExp) a.left.accept(this);
		LExp right = (LExp) a.right.accept(this);
		return new LAppend(left, right);
	}

	public LNode visit(HIterable i) {
		if (i.newExpr != null) {
			return new LName(i.newExpr.var);
		}
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