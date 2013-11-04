import java.util.*;

class Testing {
	public static void main(String[] args) {
		LName i = new LName("i");
		LName r = new LName("r");
		LName input = new LName("input");
		LIter ri = new LIter();
		LStmt as = new LAssign(r, ri);
		LStmt body = new LAssign(r, new LAppend(r, new LIter(i)));
		LStmt f = new LFor(input, i, body);
		List<LStmt> stmts = new ArrayList<LStmt>();
		stmts.add(as);
		stmts.add(f);
		stmts.add(new LReturn(r));
		List<LName> names = new ArrayList<LName>();
		List<LFunc> funcs = new ArrayList<LFunc>();
		LNode n = new LProg(names, funcs, new LStmts(stmts));
		CGenerator cc = new CGenerator();
		System.out.println(n.accept(cc));
	}
}
