import java.util.*;
import org.pcollections.HashTreePSet;
import org.pcollections.MapPSet;

// some typedefs
class NodeList extends ArrayList<CFGNode> {}
class StmtList extends ArrayList<LStmt> {}

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

	// construct the CFG from the semi-converted IR tree
	public abstract void setEdges(NodeList incoming, NodeList outgoing);

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

	public void setEdges (NodeList incoming, NodeList outgoing) {
		int len = children.size();
		if (len > 0) {
			children.get(0).prev.addAll(incoming);
			children.get(len - 1).succ.addAll(outgoing);
			for (int i = 1; i < len; i++) {
				CFGNode a = children.get(i - 1);
				CFGNode b = children.get(i);
				a.succ.add(b);
				b.prev.add(a);
			}
		}
	}

	public StmtList translate() {
		return null;
	}
}

class CFGFor extends CFGNode {
	LFor stmt;

	public CFGFor (LFor s,  CFG graph) {
		stmt = s;
	}

	public void setEdges (NodeList incoming, NodeList outgoing) {
	}

	public StmtList translate() {
		return null;
	}
}

class CFGWhile extends CFGNode {
	LWhile stmt;

	public CFGWhile (LWhile s,  CFG graph) {
		stmt = s;
	}

	public void setEdges (NodeList incoming, NodeList outgoing) {
	}

	public StmtList translate() {
		return null;
	}
}

class CFGCond extends CFGNode {
	LCond stmt;

	public CFGCond (LCond s, CFG graph) {
		stmt = s;
	}

	public void setEdges (NodeList incoming, NodeList outgoing) {
	}

	public StmtList translate() {
		return null;
	}
}


class CFGAssign extends CFGNode {
	LAssign stmt;

	public CFGAssign (LAssign s, CFG graph) {
		stmt = s;
	}

	public void setEdges (NodeList incoming, NodeList outgoing) {
	}

	public StmtList translate() {
		return null;
	}
}

class CFGReturn extends CFGNode {
	LReturn stmt;

	public CFGReturn (LReturn s, CFG graph) {
		stmt = s;
	}

	public void setEdges (NodeList incoming, NodeList outgoing) {
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