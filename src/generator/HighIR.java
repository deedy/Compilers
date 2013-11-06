import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

abstract class HNode {
    public abstract LNode accept(HLVisitor v);
    public void convertFuns(Map<String, HFunction> map) {
        return;
    }
}

abstract class HExpression extends HNode {
    
}

abstract class HStatement extends HNode {

}

class HInterface extends HNode {
    static HashMap<String, HInterface> classes = new HashMap<String, HInterface>(); // a set of all the classes
    String name;
    HashMap<String, HFunction> funs;
    List<String> parents;
    int id;
    List<String> superInterfaces;

    public HInterface(int id, String name, HashMap<String,HFunction> funs, List<String> parents) {
        this.id = id;
        this.name = name;
        this.funs = funs;
        this.parents = parents;
    }

    List<String> getSuperInterfaces() {
        // no more super classes
        if (parents.size() == 1 && parents.contains("Thing")) {
            return new ArrayList<String>();
        }
        if (superInterfaces != null) {
            return superInterfaces;
        }
        superInterfaces = new ArrayList<String>();
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
        System.out.println(superInterfaces);
        return superInterfaces;
    }

    void implementSuperInterfaces(HashMap<String, HInterface> classes) {
        for (String s : getSuperInterfaces()) {
            HInterface i = classes.get(s);
            if (i != null) {
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
            s.append(f.getValue().toString() + "\n");
        }
        return s.toString();
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        for (Map.Entry<String,HFunction> f : funs.entrySet()) {
            f.getValue().convertFuns(map);
        }
    }
}

class HClass extends HInterface {

    List<String> fields;
    HExpression parent;
    CubexTypeContext tCont;
    List<HStatement> stmts;
    List<HExpression> exprs;

    public HClass(int id, String name, List<HExpression> exprs, 
        List<HStatement> stmts, HashMap<String,HFunction> funs, List<String> parents, 
        List<String> fields, HExpression parent, CubexTypeContext tCont) {
        super(id, name, funs, parents);
        this.stmts = stmts;
        this.exprs = exprs;
        this.fields = fields;
        this.parent = parent;
        this.tCont = tCont;
    }
    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        for (Map.Entry<String,HFunction> f : funs.entrySet()) {
            f.getValue().convertFuns(map);
        }
        for (HStatement s : stmts) {
            s.convertFuns(map);
        }
        for (HExpression e : exprs) {
            e.convertFuns(map);
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

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        expr.convertFuns(map);
        stmt1.convertFuns(map);
        stmt2.convertFuns(map);
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

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        expr.convertFuns(map);
        stmt.convertFuns(map);
    }
}

class HWhileLoop extends HStatement {

    HExpression expr;
    HStatement stmt;

    public HWhileLoop(HExpression expr, HStatement stmt) {
        this.expr = expr;
        this.stmt = stmt;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        expr.convertFuns(map);
        stmt.convertFuns(map);
    }
}

class HReturn extends HStatement {

    HExpression expr;

    public HReturn(HExpression expr) {
        this.expr = expr;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        expr.convertFuns(map);
    }
}

class HBlock extends HStatement {

    List<HStatement> stmts;

    public HBlock(List<HStatement> stmts) {
        this.stmts = stmts;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        for (HStatement s : stmts) {
            s.convertFuns(map);
        }
    }
}

class HAssign extends HStatement {

    HExpression expr;
    String name;

    public HAssign(String name, HExpression expr) {
        this.expr = expr;
        this.name = name;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        expr.convertFuns(map);
    }
}

class HFunction {
    String name;
    String declassedName;
    List<String> args;
    HStatement body;

    public HFunction(String name, HStatement body, List<String> args) {
        this.name = name;
        this.body = body;
        this.args = args;
    }

    void addDef(Integer id, HFunction f) {
        return;
    }

    public String toString() {
        return declassedName;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        body.convertFuns(map);
    }
}

class HUndefFunction extends HFunction {
    HashMap<Integer, HFunction> defs = new HashMap<Integer, HFunction>();

    public HUndefFunction(String name, List<String> args) {
        super(name, null, args);
    }

    void addDef(Integer id, HFunction f) {
        defs.put(id, f);
    }

    public String toString() {
        return declassedName + "\n" + defs.toString();
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}

class HFunctionCall extends HExpression {

    String name;
    List<HExpression> args;

    public HFunctionCall(String name, List<HExpression> args) {
        this.name = name;
        this.args = args;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        for (HExpression e : args) {
            e.convertFuns(map);
        }
        HFunction f = map.get(name);
        if (f != null) {
            name = f.name;
        }
    }
}

class HAppend extends HExpression {
    HExpression left;
    HExpression right;

    public HAppend(HExpression left, HExpression right) {
        this.left = left;
        this.right = right;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        left.convertFuns(map);
        right.convertFuns(map);
    }
}

class HIterable extends HExpression {
    List<HExpression> elems;

    public HIterable(List<HExpression> elems) {
        this.elems = elems;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }

    public void convertFuns(Map<String, HFunction> map) {
        for (HExpression e : elems) {
            e.convertFuns(map);
        }
    }
}

class HBoolean extends HExpression {

    String val;

    public HBoolean(String val) {
        this.val = val;
    } 

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}

class HInt extends HExpression {

    int val;

    public HInt(int val) {
        this.val = val;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}

class HString extends HExpression {

    String val;

    public HString(String val) {
        this.val = val;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}

class HVar extends HExpression {
    
    String var;

    public HVar(String var) {
        this.var = var;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}

abstract class HProg extends HNode {
    HProg prog;
}

class HStatementProg extends HProg {
    List<HStatement> stmts;

    public HStatementProg(List<HStatement> stmts, HProg prog) {
        this.stmts = stmts;
        this.prog = prog;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}

class HClassProg extends HProg {

    HInterface cls;

    public HClassProg(HInterface cls, HProg prog) {
        this.cls = cls;
        this.prog = prog;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}

class HFunProg extends HProg {

    List<HFunction> funs;

    public HFunProg(List<HFunction> funs, HProg prog) {
        this.funs = funs;
        this.prog = prog;
    }

    public LNode accept(HLVisitor v) {
        return v.visit(this);
    }
}