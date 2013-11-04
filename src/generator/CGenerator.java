import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.Iterator;
import java.util.HashSet;

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
}

public class CGenerator implements LVisitor {

	int iterCount = 0;
	HashSet<String> localVars = new HashSet<String>();

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
		return join(visitAll(lst.stmts), "\n");
	}

	public String visit(LFunc f) {
		// invariant: anything that assigns will add names to this
		localVars = new HashSet<String>();
		String name = f.name.accept(this);
		String stmts = f.stmts.accept(this);

		int argCount = 0;
		List<String> params = new ArrayList<String>();
		for(int i = 0; i < f.args.size(); i++) {
			params.add("_object o" + argCount);
			argCount += 1;
		}
		String args = join(params, ", ");

		int count = 0;
		List<String> varDefs = new ArrayList<String>();
		List<String> vArgs = visitAll(f.args);
		for(String arg : vArgs) {
			varDefs.add(String.format("Object %s = o%d;\n_incr(%s);\n",
				arg, count, arg));
			count += 1;
		}
		for(String varName : localVars) {
			if (vArgs.contains(varName)) {
				continue;
			}
			varDefs.add(String.format("Object %s = NULL;\n", varName));
		}
		varDefs.add("Object _tmp = NULL;\n");
		varDefs.add("Object _ret = NULL;\n");

		String locals = join(varDefs, "\n");

		localVars = new HashSet<String>();
		return String.format("_object %s(%s) {\n%s\n%s\n}\n", name, args, locals, stmts);
	}

	public String visit(LConstructor f) {
		String name = f.name.accept(this);
		String stmts = f.stmts.accept(this);
		String parent = f.parent.accept(this);

		List<String> params = new ArrayList<String>();
		for(int i = 0; i < f.args.size(); i++) {
			params.add("_object o");
		}
		String args = join(params, ", ");

		int count = 0;
		List<String> varDefs = new ArrayList<String>();
		for(String arg : visitAll(f.args)) {
			varDefs.add("Object " + arg + " = o" + count + ";");
		}

		varDefs.add("Object _tmp = NULL;");
		varDefs.add("Object _ret = NULL;");

		String locals = join(varDefs, "\n");

		localVars = new HashSet<String>();
		return String.format("_object %s(%s) {\n%s\n%s\n}\n", name, args, locals, stmts);
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
		return chain(i.items, 0);
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
		iterCount += 1;
		String elem = f.elem.accept(this);
		String stmt = f.stmt.accept(this);
		// make a call to the built-in next function
		return String.format(
			"_IterNode %s = _iterator(%s);\nwhile (%s) {\n_object %s = %s->curr;\n%s\n%s = %s->next(%s);\n} x3free(%s);",
			iterName, iter, iterName, elem, iterName, stmt, iterName, iterName, iterName, iterName);

	}

	public String visit(LWhile w) {
		String cond = w.cond.accept(this);
		String stmts = w.stmt.accept(this);
		return String.format("while (((Boolean) %s)->value) {\n%s\n}", cond, stmts);
	}

	public String visit(LCond c) {
		String cond = c.cond.accept(this);
		String ifBlock = c.ifBlock.accept(this);
		String elseBlock = c.elseBlock.accept(this);
		return String.format("if (((Boolean) %s)->value) {\n%s\n} else {\n%s\n}", cond, ifBlock, elseBlock);
	}

	public String visit(LAssign a) {
		String name = a.var.accept(this);
		String val = a.val.accept(this);

		if(!(a.var instanceof LFieldAccess)) {
			localVars.add(name);
		}

		return String.format("_tmp = %s;\n%s = (Object) %s;\n_incr(%s);\n_decr(_tmp);\n", name, name, val, name);
	}

	public String visit(LReturn r) {
		String ret = r.ret.accept(this);
		StringBuilder decrs = new StringBuilder();
		for(String var : localVars) {
			decrs.append(String.format("_decr(%s);", var));
		}
		return String.format("_ret = %s;\n_incr(_ret);\n%s\nreturn _ret;", ret, decrs);
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
		String funDecs = join(funcs, "\n");
		String baseProg = "#include \"cubex_main.h\"\n"
                + "#include \"cubex_external_functions.h\"\n"
                + "#include \"cubex_lib.h\"\n"
                + "%s\n"
                + "%s\n"
                + "void cubex_main() {\n"
                	+ "__init();\n"
              		+ "_object _i = _prog_main();\n"
                    + "_IterNode _i_iter = _iterator(_i);\n"
                    + "while(_i_iter) {\n"
                    	+ "_print(_i_iter->curr);\n"
                    	+ "_i_iter = _i_iter->next(_i_iter);\n"
                    + "}\n"
                    + "x3free(_i_iter);\n"
                + "}\n";
    return String.format(baseProg, globDecs, funDecs);
	}
}
