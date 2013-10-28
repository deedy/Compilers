import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

abstract class HNode {
    
}

abstract class HExpression extends HNode {
    
}

abstract class HStatement extends HNode {

}

class HClass extends HNode {
    static HashSet<HClass> classes; // a set of all the classes
    String superclass; // the name of the superclass
    HashMap<String, HFunction> functions;

    public HClass(String superclass, List<HFunction> funs) {
        this.superclass = superclass;
        HClass.classes.add(this);
        for (HFunction f : funs) {
            functions.put(f.name, f);
        }
    }
}

class HConditional extends HStatement {

    HExpression expr;
    HStatement stmt1;
    HStatement stmt2;

    public HConditional(HExpression expr, HStatement stmt1, HStatement stmt2) {
        this.expr = expr;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }
}

class HForLoop extends HStatement {

    HExpression expr;
    HStatement stmt;

    public HForLoop(HExpression expr, HStatement stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }
}

class HWhileLoop extends HStatement {

    HExpression expr;
    HStatement stmt;

    public HWhileLoop(HExpression expr, HStatement stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }
}

class HReturn extends HStatement {

    HExpression expr;

    public HReturn(HExpression expr) {
        this.expr = expr;
    }
}

/// NO CUBEX... T.T
class HBlock extends HStatement {

    List<CubexStatement> stmts;

    public HBlock(List<CubexStatement> stmts) {
        this.stmts = stmts;
    }
}

class HAssign extends HStatement {

    HExpression expr;
    CubexVName name;

    public HAssign(CubexExpression expr, CubexVName name) {
        this.expr = expr;
        this.name = name;
    }
}



class HFunction extends HExpression {
    String name;
    CubexReturn body;

    public HFunction(String name, CubexReturn body) {
        this.name = name;
        this.body = body;
    }

}