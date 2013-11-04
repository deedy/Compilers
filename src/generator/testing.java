import java.util.*;

class Testing {
	public static void main(String[] args) {
		LName ret = new LName("ret");
		LName input = new LName("input");
		LName i = new LName("i");
		List<LExp> iL = new ArrayList<LExp>();
		iL.add(i);
		LIter iter = new LIter(iL);
		LAppend a = new LAppend(ret, iter);
		LAppend b = new LAppend(iter, a);
		LAssign as = new LAssign(ret, b);
		LFor f = new LFor(input, i, as);
		LReturn ret2 = new LReturn(ret);
		List<LStmt> stmts = new ArrayList<LStmt>();
		stmts.add(new LAssign(ret, new LNull()));
		stmts.add(f);
		stmts.add(ret2);
		List<LName> names = new ArrayList<LName>();
		List<LFunc> funcs = new ArrayList<LFunc>();
		LNode n = new LProg(names, funcs, new LStmts(stmts));
		CGenerator cc = new CGenerator();
		System.out.println(n.accept(cc));
	}
}
