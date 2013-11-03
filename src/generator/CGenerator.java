import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.Iterator;
import java.util.HashSet;

interface LVisitor {
	public String visit(LNode n);
}

public class CGenerator implements LVisitor {

	int iterCount = 0;
	HashSet<String> localVars;

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

	/* implements a visitor */
	public String visit(LNode n) {
		return null;
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

		List<String> params = new ArrayList<String>();
		for(int i = 0; i < f.args.size(); i++) {
			params.add("_object o");
		}
		String args = join(params, ", ");

		int count = 0;
		List<String> varDefs = new ArrayList<String>();
		for(String arg : visitAll(f.args)) {
			varDefs.add(String.format("Object %s = o%d; _incr(%s);",
				arg, count, arg));
		}
		for(String varName : localVars) {
			varDefs.add(String.format("Object %s = NULL;", varName));
		}
		varDefs.add("Object _tmp = NULL;");
		varDefs.add("Object _ret = NULL;");

		String locals = join(varDefs, "\n");

		localVars = null;
		return String.format("_object %s ( %s ) {\n%s\n%s\n}", name, args, locals, stmts);
	}

	public String visit(LConstructor f) {
		String name = f.name.accept(this);
		String stmts = f.stmts.accept(this);

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

		localVars = null;
		return String.format("_object %s ( %s ) {\n%s\n%s\n}", name, args, locals, stmts);
	}

	public String visit(LAlloc a) {
		return String.format("_allocate(%d, %d)", a.id, a.num_fields);
	}

	public String visit(LNum n) {
		return String.format("Integer_construct(%d)", n.value);
	}

	public String visit(LString s) {
		return String.format("String_construct(%s)", s.value);
	} 

	String LBool(LBool b) {
		return String.format("Boolean_construct(%d)", b.value ? 1 : 0);
	}

	public String visit(LNull n) {
		return "NULL";
	}

	public String visit(LName n) {
		return n.name;
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

		localVars.add(iterName);
		localVars.add(elem);
		// make a call to the built-in next function
		return String.format(
			"_IterNode %s = _iterator(%s); while (%s) { %s = %s->curr; %s %s = next(%s);}",
			iterName, iter, iterName, elem, iterName, stmt, iterName, iterName);

	}

	public String visit(LWhile w) {
		String cond = w.cond.accept(this);
		String stmts = w.stmt.accept(this);
		return String.format("while (((Boolean) %s)->value) { %s }", cond, stmts);
	}

	public String visit(LCond c) {
		String cond = c.cond.accept(this);
		String ifBlock = c.ifBlock.accept(this);
		String elseBlock = c.elseBlock.accept(this);
		return String.format("if (((Boolean) %s)->value) { %s } else { %s }", cond, ifBlock, elseBlock);
	}

	public String visit(LAssign a) {
		String name = a.var.accept(this);
		String val = a.val.accept(this);

		if(!(a.var instanceof LFieldAccess)) {
			localVars.add(name);
		}

		return String.format("_tmp = %s; %s = %s; _incr(%s); _decr(tmp);", name, name, val, name);
	}

	public String visit(LReturn r) {
		String ret = r.ret.accept(this);
		StringBuilder decrs = new StringBuilder();
		for(String var : localVars) {
			decrs.append(String.format("_decr(%s);", var));
		}
		return String.format("_ret = %s; %s return _ret;", ret, decrs);
	}
}