import java.lang.StringBuilder;
import java.util.*;

interface LVisitor {

	public String visit(LExps lst);

	public String visit(LStmts lst);

	public String visit(LFunc f);

	public String visit(LConstructor f);

	public String visit(LNum n);

	public String visit(LString s);

	public String visit(LBool b);

	public String visit(LNull n);

	public String visit(LName n);

	public String visit(LIter n);

	public String visit(LFunCall fc);

	public String visit(LAppend app);

	public String visit(LFieldAccess f);

	public String visit(LFor f);

	public String visit(LWhile w);

	public String visit(LCond c);

	public String visit(LAssign a);

	public String visit(LReturn r);

	public String visit(LProg p);

	public String visit(LId i);

	public String visit(LIncr i);

	public String visit(LDecr i);
}

public class CGenerator implements LVisitor {
	int iterCount = 0;
	int tabCount = 0;
	HashSet<String> localVars = new HashSet<String>();
	List<String> funHeaders = new ArrayList<String>();

	String indent(String s) {
		StringBuilder out = new StringBuilder();
		for (String sub : s.split("\n")) {
			if (!sub.equals("")) {
				out.append("\t" + sub + "\n");
			}
		}
		return out.toString();
	}

	/* helper functions */
	String join(Collection<?> s, String delimiter) {
		StringBuilder builder = new StringBuilder();
		Iterator<?> iter = s.iterator();
		while (iter.hasNext()) {
			builder.append(iter.next());
			if (!iter.hasNext()) break;
			builder.append(delimiter);
		}
		return builder.toString();
	}

	List<String> visitAll(List<? extends LNode> lst) {
		List<String> ret = new ArrayList<String>();
		for(LNode n : lst) {
			ret.add(n.accept(this));
		}
		return ret;
	}

	public String visit(LExps lst) {
		return join(visitAll(lst.exps), ", ");
	}

	public String visit(LStmts lst) {
		return join(visitAll(lst.stmts), "");
	}

	public String visit(LId i) {
		String name = i.name.accept(this);
		int id = i.id;
		return String.format("Boolean_construct(((Object) %s)->id == %d)", name, id);
	}

	public String visit(LFunc f) {
		localVars = new HashSet<String>();
		String name = f.name.accept(this);

		// create the function header
		int argCount = 0;
		List<String> params = new ArrayList<String>();
		for(int i = 0; i < f.args.size(); i++) {
			params.add("_object _o" + argCount);
			localVars.add(f.args.get(i).name);
			argCount += 1;
		}
		String args = join(params, ", ");
		String def = String.format("_object %s(%s)", name, args);
		funHeaders.add(def + ";");

		// translate and indent the statement
		tabCount += 1;

		LStmt sP = f.stmts;
		ArrayList<LName> initial = new ArrayList<LName>();
		initial.addAll(f.args);
		// CFG cfg = new CFG(sP, initial);
		// cfg.root.buildSubGraph();
		// cfg.solveLiveness();
		// LStmt sO = cfg.translate();
		// String stmts = indent(sO.accept(this));
		String stmts = indent(sP.accept(this));
		ArrayList<String> toIncr = new ArrayList<String>();
		// Collection<LName> usedVars = cfg.root.in.plusAll(cfg.root.use);
		for (LName n : initial) {
			toIncr.add(indent(String.format("_incr(%s);\n", n)));
		}

		String incrs = join(toIncr, "");
		// String incrs = "";

		int count = 0;
		List<String> varDefs = new ArrayList<String>();
		List<String> vArgs = visitAll(f.args);

		// declare the arguments
		for(String arg : vArgs) {
			varDefs.add(indent(String.format("Object %s = _o%d;\n",arg, count)));
			count += 1;
		}
		// declare all other variables null
		for(String varName : localVars) {
			if (vArgs.contains(varName)) {
				continue;
			}
			varDefs.add(indent(String.format("Object %s = NULL;\n", varName)));
		}

		tabCount -= 1;

		String locals = join(varDefs, "");

		localVars = new HashSet<String>();
		return String.format("%s {\n%s%s%s}\n", def, locals, incrs, stmts);
	}

	public String visit(LConstructor f) {
		String name = f.name.accept(this);
		String parent = f.parent.accept(this);

		List<String> params = new ArrayList<String>();
		int argCount = 0;
		for(int i = 0; i < f.args.size(); i++) {
			params.add("_object _o" + argCount);
			argCount += 1;
		}
		String args = join(params, ", ");
		String def = String.format("_object %s(%s)", name, args);


		String stmts = indent(f.stmts.accept(this));
		int count = 0;
		List<String> varDefs = new ArrayList<String>();
		for(String arg : visitAll(f.args)) {
			varDefs.add(indent("Object " + arg + " = _o" + count + ";\n"));
			count += 1;
		}

		ArrayList<LStmt> fieldIncrs = new ArrayList<LStmt>();
		for (int i = 0; i < f.fields; i++) {
			fieldIncrs.add(new LIncr(new LFieldAccess(new LName("_obj"), i)));
		}

		String incrs = indent(join(visitAll(fieldIncrs),""));

		varDefs.add(indent("Object _tmp = NULL;"));
		varDefs.add(indent("Object _ret = NULL;"));
		varDefs.add(indent(String.format("Object _obj = _allocate(%d, %d);", f.id, f.fields)));

		String locals = join(varDefs, "");

		localVars = new HashSet<String>();
		return String.format("_object %s(%s) {\n%s%s%s\treturn _obj;\n}\n", name, args, locals, stmts, incrs);
	}

	public String visit(LNum n) {
		return String.format("Integer_construct(%d)", n.value);
	}

	public String visit(LString s) {
		return String.format("String_construct(%s)", s.value);
	} 

	public String visit(LBool b) {
		return String.format("Boolean_construct(%d)", b.value ? 1 : 0);
	}

	public String visit(LNull n) {
		return "NULL";
	}

	public String visit(LName n) {
		return n.name;
	}

	public String visit(LIter i) {
		String items = join(visitAll(i.items), ",");
		return String.format("Iterable_construct((_object[]){%s}, %d)", items, i.items.size());
	}

	String chain(List<LExp> l, int index) {
		if (index == l.size()) {
			return "NULL";
		} else if (index == l.size() - 1) {
			String item = l.get(index).accept(this);
			return String.format("Iterable_construct(%s)", item);
		} else {
			String item = l.get(index).accept(this);
			String iter = String.format("Iterable_construct(%s)", item);
			String next = chain(l, index + 1);
			return String.format("_append(%s, %s)", iter, next);
		}
	}

	public String visit(LFunCall fc) {
		String name = fc.name.accept(this);
		String args = fc.args.accept(this);
		return String.format("%s(%s)", name, args);
	}

	public String visit(LAppend app) {
		// make call to the built-in append function
		String l1 = app.iter1.accept(this);
		String l2 = app.iter2.accept(this);
		return String.format("_append(%s, %s)", l1, l2);
	}

	public String visit(LFieldAccess f) {
		String name = f.obj.accept(this);
		return String.format("%s->fields[%d]", name, f.field);
	}

	public String visit(LFor f) {
		String iter = f.iter.accept(this);
		String iterName = "_iter" + iterCount;
		String ableName = "_iterable" + iterCount;
		iterCount += 1;
		String dec1 = String.format("Iterable %s = (Iterable)%s;\n", ableName, iter);
		String dec2 = String.format("_Iterator %s = %s->iter(%s);\n", iterName, ableName, ableName);
		String elem = f.elem.accept(this);
		String stmt = f.stmt.accept(this);
		localVars.add(elem);
		String cond = String.format("%s = %s->next(%s)", elem, iterName, iterName);
		return String.format("%s%swhile (%s) {\n%s}\n",
							dec1, dec2, cond, indent(stmt));
	}

	public String visit(LWhile w) {
		String cond = w.cond.accept(this);
		String stmts = indent(w.stmt.accept(this));
		return String.format("while (((Boolean) %s)->value) {\n%s}\n", cond, stmts);
	}

	public String visit(LCond c) {
		String cond = c.cond.accept(this);;
		String ifBlock = indent(c.ifBlock.accept(this));
		String elseBlock = indent(c.elseBlock.accept(this));
		return String.format("if (((Boolean) %s)->value) {\n%s} else {\n%s}\n", cond, ifBlock, elseBlock);
	}

	public String visit(LAssign a) {
		String name = a.var.accept(this);
		String val = a.val.accept(this);

		if(!(a.var instanceof LFieldAccess)) {
			localVars.add(name);
		}

		return String.format("%s = (Object) %s;\n", name, val);
	}

	public String visit(LReturn r) {
		String ret = r.ret.accept(this);
		return String.format("return %s;\n", ret);
	}

	public String visit(LIncr i) {
		String ret = i.arg.accept(this);
		return String.format("_incr(%s);\n", ret);
	}

	public String visit(LDecr d) {
		String ret = d.arg.accept(this);
		return String.format("_decr(%s);\n", ret);
	}

	public String visit(LProg p) {
		List<String> globals = visitAll(p.globals);
		localVars = new HashSet<String>(globals);
		LFunc prog_main = new LFunc(new LName("_prog_main"),
									new ArrayList<LName>(), 
									p.stmts);
		p.funcs.add(prog_main);
		List<String> funcs = visitAll(p.funcs);
		StringBuilder globDecs = new StringBuilder();
		for(String s : globals){
			globDecs.append("_object " + s + ";\n");
		}
		String funHeads = join(funHeaders, "\n");
		String funDecs = join(funcs, "\n");
		String baseProg = "#include \"cubex_main.h\"\n"
                + "#include \"cubex_external_functions.h\"\n"
                + "#include \"cubex_lib.h\"\n"
                + "%s\n"
                + "%s\n"
                + "%s\n"
                + "void cubex_main() {\n"
                	+ "\t__init();\n"
              		+ "\t_object _i = _prog_main();\n"
                    + "\t_Iterator _i_iter = ((Iterable)_i)->iter(_i);\n"
                    + "\t_object _j;"
                    + "\twhile(_j = _i_iter->next(_i_iter)) {\n"
                    	+ "\t\t_print(_j);\n"
                    + "\t}\n"
                    + "\t_free_all_the_things();\n"
                + "}\n";
    	return String.format(baseProg, globDecs, funHeads, funDecs);
	}
}
