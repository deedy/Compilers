import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;

abstract class HNode {
    
}

abstract class HExpression extends HNode {
    
}

abstract class HStatement extends HNode {

}

class HInterface extends HNode {
    static HashMap<String, HInterface> classes = new HashMap<String, HInterface>(); // a set of all the classes
    String name;
    //String superclass; // the name of the superclass
    //HashMap<String, HFunction> functions = new HashMap<String, HFunction>();
    //CubexTypeContext typeContext;
    List<HStatement> stmts;
    List<HExpression> exprs;
    HashMap<String, HFunction> funs;
    List<String> parents;
    int id;
    List<String> superInterfaces;

    public HInterface(int id, String name, List<HExpression> exprs, 
        List<HStatement> stmts, HashMap<String,HFunction> funs, List<String> parents) {
        this.id = id;
        this.name = name;
        this.exprs = exprs;
        this.stmts = stmts;
        this.funs = funs;
        this.parents = parents;
    }

    List<String> getSuperInterfaces() {
        // no more super classes
        if (parents.size() == 1 && parents.contains("Thing")) {
            return null;
        }
        if (superInterfaces != null) {
            return superInterfaces;
        }
        List<String> superInterfaces = new ArrayList<String>();
        for (String s : parents) {
            HInterface i = classes.get(s);
            if (!(i instanceof HClass)) {
                superInterfaces.add(s);
            }
            HInterface superInterface = classes.get(s);
            if (superInterface != null) {
                superInterfaces.addAll(superInterface.getSuperInterfaces());
            }
        }
        this.superInterfaces = superInterfaces;
        return superInterfaces;
    }

    void implementSuperInterfaces(HashMap<String, HInterface> classes) {
        for (String s : getSuperInterfaces()) {
            HInterface i = classes.get(s);
            if (i != null) {
                // implement all the unimplemented interface functions
                for (Map.Entry<String,HFunction> f : funs.entrySet()) {
                    i.addImplementation(id, f.getValue());
                }
            }
        }
    }

    void addImplementation(Integer id, HFunction f) {
        HFunction fun = funs.get(f.name);
        if (fun != null) {
            fun.addDef(id, f);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, HFunction> f : funs.entrySet()) {
            s.append(f.toString());
        }
        return s.toString();
    }
}

class HClass extends HInterface {

    List<String> fields;

    public HClass(int id, String name, List<HExpression> exprs, 
        List<HStatement> stmts, HashMap<String,HFunction> funs, List<String> parents, List<String> fields) {
        super(id, name, exprs, stmts, funs, parents);
        this.fields = fields;
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
    String name;
    HExpression expr;
    HStatement stmt;

    public HForLoop(String name, HExpression expr, HStatement stmt) {
        this.name = name;
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

    public HAssign(String name, HExpression expr) {
        this.expr = expr;
        this.name = name;
    }
}



class HFunction {
    String name;
    String declassedName;
    HStatement body;

    public HFunction(String name, HStatement body) {
        this.name = name;
        this.body = body;
    }

    void addDef(Integer id, HFunction f) {
        return;
    }

    public String toString() {
        return name;
    }

}

class HUndefFunction extends HFunction {
    String name;
    HashMap<Integer, HFunction> defs = new HashMap<Integer, HFunction>();

    public HUndefFunction(String name) {
        super(name, null);
        this.name = name;
    }

    void addDef(Integer id, HFunction f) {
        defs.put(id, f);
    }

    public String toString() {
        return name;
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

// class HMethodCall extends HExpression {
//     HExpression expr;
//     String name;
//     List<HExpression> args;

//     public HMethodCall(HExpression expr, String name, List<HExpression> args) {
//         this.name = name;
//         this.args = args;
//         this.expr = expr;
//     }
// }

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

class HProg extends HNode {
    HProg prog;
}

class HStatementProg extends HProg {
    List<HStatement> stmts;

    public HStatementProg(List<HStatement> stmts, HProg prog) {
        this.stmts = stmts;
        this.prog = prog;
    }
}

class HClassProg extends HProg {

    HInterface cls;

    public HClassProg(HInterface cls, HProg prog) {
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