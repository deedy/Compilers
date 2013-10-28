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
    static HashMap<String, HClass> classes = new HashMap<String, HClass>(); // a set of all the classes
    String name;
    String superclass; // the name of the superclass
    HashMap<String, HFunction> functions = new HashMap<String, HFunction>();
    CubexTypeContext typeContext;
    List<HStatement> stmts;
    List<HExpression> exprs;

    public HClass(String name, String superclass, List<HFunction> funs, 
        List<String> paramNames, CubexTypeContext typeContext, List<HStatement> stmts, List<HExpression> exprs) {
        this(name, superclass, funs, paramNames);
        this.typeContext = typeContext;
        this.stmts = stmts;
        this.exprs = exprs;
    }

    public HClass(String name, String superclass, List<HFunction> funs, 
        List<String> paramNames) {
        this.name = name;
        this.superclass = superclass;
        HClass.classes.put(name, this);
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