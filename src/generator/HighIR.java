import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

    // Get all the functions of this class
    public void getFunctions(Map<String, HFunction> funs) {

        for (Map.Entry<String, HFunction> e : functions.entrySet()) {
            // Don't add the function if it's already in the map
            if (funs.get(e.getKey()) != null) {
                funs.put(e.getKey(), e.getValue());
            }
        }

        if (superclass != null) {
            HClass classObj = HClass.classes.get(superclass);
            classObj.getFunctions(funs);
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

    List<HStatement> stmts;

    public HBlock(List<HStatement> stmts) {
        this.stmts = stmts;
    }
}

class HAssign extends HStatement {

    HExpression expr;
    String name;

    public HAssign(HExpression expr, String name) {
        this.expr = expr;
        this.name = name;
    }
}



class HFunction {
    String name;
    HStatement body;

    public HFunction(String name, HStatement body) {
        this.name = name;
        this.body = body;
    }

}

class HFunctionCall extends HExpression {

    String name;
    List<HExpression> args;

    public HFunctionCall(String name, List<HExpression> args) {
        this.name = name;
        this.args = args;
    }
}

class HMethodCall extends HExpression {
    HExpression expr;
    String name;
    List<HExpression> args;

    public HMethodCall(HExpression expr, String name, List<HExpression> args) {
        this.name = name;
        this.args = args;
        this.expr = expr;
    }
}

class HAppend extends HExpression {
    HExpression left;
    HExpression right;

    public HAppend(HExpression left, HExpression right) {
        this.left = left;
        this.right = right;
    }
}

class HIterable extends HExpression {
    List<HExpression> elems;

    public HIterable(List<HExpression> elems) {
        this.elems = elems;
    }
}

class HBoolean extends HExpression {

    String val;

    public HBoolean(String val) {
        this.val = val;
    } 
}

class HInt extends HExpression {

    int val;

    public HInt(int val) {
        this.val = val;
    }
}

class HString extends HExpression {

    String val;

    public HString(String val) {
        this.val = val;
    }
}

class HVar extends HExpression {
    
    String var;

    public HVar(String var) {
        this.var = var;
    }
}

class HProg {
    HProg prog;
}

class HStatementProg extends HProg {
    List<HStatement> stmts;

    public HStatementProg(List<HStatement> stmts) {
        this.stmts = stmts;
    }
}

class HClassProg extends HProg {

    HClass cls;

    public HClassProg(HClass cls, HProg prog) {
        this.cls = cls;
        this.prog = prog;
    }
}

class HFunProg extends HProg {

    List<HFunction> funs;

    public HFunProg(List<HFunction> funs, HProg prog) {
        this.funs = funs;
        this.prog = prog;
    }
}