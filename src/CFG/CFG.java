import java.util.*;
import org.pcollections.HashTreePSet;
import org.pcollections.MapPSet;

// some typedefs
class NodeList extends ArrayList<CFGNode> {
	public NodeList (Collection<CFGNode> coll) {
		super();
		for (CFGNode c : coll) {
			add(c);
		}
	}

	public NodeList (CFGNode n) {
		super();
		add(n);
	}

	public NodeList () {
		super();
	}
}
class StmtList extends ArrayList<LStmt> {}
class NodeListPair extends Pair<NodeList, NodeList> {
	public NodeListPair (NodeList l1, NodeList l2) {
		super(l1, l2);
	}
}

abstract class CFGNode {
	// the original statement
	LStmt stmt;
	// the set of variables live before statement
	MapPSet<LName> in = HashTreePSet.empty();
	// the set of variables used by successors
	MapPSet<LName> out = HashTreePSet.empty();
	// variables used before assignment
	MapPSet<LName> use = HashTreePSet.empty();
	// variables assigned in this statement
	MapPSet<LName> def = HashTreePSet.empty();

	// edges in the CFG
	NodeList prev = new NodeList();
	NodeList succ = new NodeList();
	// used to rebuild the IR
	NodeList children = new NodeList();


	// makes all internal connections
	// then exposes all entries and exits of the subgraph
	public abstract NodeListPair buildSubGraph();

	// build an IR Tree with garbage collection
	public abstract StmtList translate ();

	// runs the LVA algorithm one step
	// returns true is the sets have not changed
	public boolean update () {
		MapPSet<LName> inP = in;
		MapPSet<LName> outP = out;
		in = use.plusAll(out.minusAll(def));
		out = out.minusAll(out);
		for (CFGNode n : succ) {
			out = out.plusAll(n.in);
		}
		return (inP.equals(in) && outP.equals(out));
	}

	// the set of items that can be garbage collected
	MapPSet<LName> collectables() {
		// can only possibly collect live things
		MapPSet<LName> ret = in;
		// cannot garbge collect what is needed later
		ret = ret.minusAll(out);
		return ret;
	}
}

class CFGStmts extends CFGNode {
	LStmts stmt;

	public CFGStmts (LStmts s, CFG graph) {
		stmt = s;
		// add all substatements to the graph
		for (LStmt subStmt : stmt.stmts) {
			CFGNode c = subStmt.toCFGNode(graph);
			children.add(c);
		}
	}

	public NodeListPair buildSubGraph () {
		int len = children.size();
		NodeList entering = new NodeList();
		NodeList exiting = new NodeList();
		if (len > 0) {
			entering.add(children.get(0));
			exiting.add(children.get(len - 1));
			for (int i = 1; i < len; i++) {
				CFGNode a = children.get(i - 1);
				CFGNode b = children.get(i);
				a.succ.add(b);
				b.prev.add(a);
			}
		}
		return new NodeListPair(entering, exiting);
	}

	public StmtList translate () {
		return null;
	}
}

class CFGFor extends CFGNode {
	LFor stmt;
	CFGBranch br;

	public CFGFor (LFor s,  CFG graph) {
		stmt = s;
		CFGNode c = stmt.stmt.toCFGNode(graph);
		children.add(c);
		br = new CFGBranch(graph);
		br.use = stmt.iter.getNames();
		br.def = stmt.elem.getNames();
	}

	public NodeListPair buildSubGraph () {
		CFGNode c = children.get(0);
		NodeListPair pair = c.buildSubGraph();
		NodeList entering = new NodeList(br);
		NodeList exiting = new NodeList(pair.getRight());
		br.succ.addAll(entering);
		for (CFGNode i : entering) {
				i.prev.add(br);
			for (CFGNode j : exiting) {
				j.succ.add(i);
				i.prev.add(j);
			}
		}
		return new NodeListPair(entering, exiting);
	}


	public StmtList translate() {
		return null;
	}
}

class CFGWhile extends CFGNode {
	LWhile stmt;
	CFGBranch br;

	public CFGWhile (LWhile s,  CFG graph) {
		stmt = s;
		CFGNode c = stmt.stmt.toCFGNode(graph);
		children.add(c);
		br = new CFGBranch(graph);
		br.use = stmt.cond.getNames();
	}

	public NodeListPair buildSubGraph () {
		CFGNode c = children.get(0);
		NodeListPair pair = c.buildSubGraph();
		NodeList entering = new NodeList(br);
		NodeList exiting = new NodeList(pair.getRight());
		br.succ.addAll(entering);
		for (CFGNode i : entering) {
				i.prev.add(br);
			for (CFGNode j : exiting) {
				j.succ.add(i);
				i.prev.add(j);
			}
		}
		return new NodeListPair(entering, exiting);
	}

	public StmtList translate() {
		return null;
	}
}

class CFGCond extends CFGNode {
	LCond stmt;
	CFGBranch br;

	public CFGCond (LCond s, CFG graph) {
		stmt = s;
		br = new CFGBranch(graph);
		br.use = stmt.cond.getNames();
		CFGNode c1 = stmt.ifBlock.toCFGNode(graph);
		CFGNode c2 = stmt.elseBlock.toCFGNode(graph);
		children.add(c1);
		children.add(c2);
	}

	public NodeListPair buildSubGraph () {
		CFGNode c1 = children.get(0);
		CFGNode c2 = children.get(1);

		NodeListPair pair1 = c1.buildSubGraph();
		NodeListPair pair2 = c2.buildSubGraph();

		NodeList in1 = pair1.getLeft();
		NodeList in2 = pair2.getLeft();
		NodeList out1 = pair1.getRight();
		NodeList out2 = pair2.getRight();

		for (CFGNode i : in1) {
			i.prev.add(br);
			br.succ.add(i);
		}

		for (CFGNode i : in2) {
			i.prev.add(br);
			br.succ.add(i);
		}

		NodeList entering = new NodeList(br);
		NodeList exiting = new NodeList(out1);
		exiting.addAll(out2);


		return new NodeListPair(entering, exiting);
	}

	public StmtList translate() {
		return null;
	}
}

class CFGAssign extends CFGNode {
	LAssign stmt;

	public CFGAssign (LAssign s, CFG graph) {
		stmt = s;
		graph.nodes.add(this);
		use = stmt.val.getNames();
		def = stmt.var.getNames();
	}

	public NodeListPair buildSubGraph () {
		NodeList ret = new NodeList(this);
		return new NodeListPair(ret, ret);
	}

	public StmtList translate() {
		return null;
	}
}

class CFGReturn extends CFGNode {
	LReturn stmt;

	public CFGReturn (LReturn s, CFG graph) {
		stmt = s;
		graph.nodes.add(this);
		use = stmt.ret.getNames();
	}

	public NodeListPair buildSubGraph () {
		NodeList ret = new NodeList(this);
		return new NodeListPair(ret, new NodeList());
	}

	public StmtList translate() {
		return null;
	}
}

class CFGBranch extends CFGNode {

	public CFGBranch (CFG graph) {
		graph.nodes.add(this);
	}

	public NodeListPair buildSubGraph () {
		NodeList ret = new NodeList(this);
		return new NodeListPair(ret, ret);
	}

	public StmtList translate() {
		return null;
	}
}

class CFG {
	// the root of the statement tree
	CFGNode root;
	// basic blocks (does not include ifs, whiles, etc)
	NodeList nodes;


	public CFG (LStmt s, MapPSet<LName> initial) {

	}

	// solve the data flow equations in place
	public void solveLiveness() {
		boolean fold = false;
		while (!fold) {
			fold = true;
			for (CFGNode node : nodes) {
				fold = fold && node.update();
			}
		}
	}
}