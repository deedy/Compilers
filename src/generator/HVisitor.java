import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class HVisitor {

    List<HFunction> funHeaders = new ArrayList<HFunction>();
    HashMap<String, HInterface> classes = new HashMap<String, HInterface>();
    int curId = 5;

    HNode visit(CubexFunctionCall n) {
        List<HExpression> args = visitExpressions(n.exprList);

        return new HFunctionCall(n.name.name, args);
    }

    HNode visit(CubexVar n) {
        return new HVar(n.name.name);
    }

    HNode visit(CubexMethodCall n) {
        List<HExpression> args = visitExpressions(n.exprList);
        args.add(n.expr.accept(this));

        return new HFunctionCall(((CubexCType)n.expr.type).name + "_" + n.name, args);
    }

    HNode visit(CubexName n) {
        return null;
    }

    HNode visit(CubexAppend n) {
        return new HAppend(n.left.accept(this), n.right.accept(this));
    }

    HNode visit(CubexIterable n) {
        return null;
    }

    HNode visit(CubexBoolean n) {
        return new HBoolean(n.bool);
    }

    HNode visit(CubexInt n) {
        return new HInt(n.num);
    }

    HNode visit(CubexString n) {
        return new HString(n.str);
    }

    HFunction visit(CubexFunHeader n) {
        HFunction f = new HFunction(n.name.name, null);
        funHeaders.add(f);
        return f;
    }
    
    // HNode visit(CubexPType n) {
    //     return null;
    // }
    
    // HNode visit(CubexCType n) {
    //     return null;
    // }
    
    // HNode visit(CubexIType n) {
    //     return null;
    // }
    
    HStatement visit(CubexBlock n) {
        List<HStatement> stmts = visitStatements(n.stmts);

        return new HBlock(stmts);
    }
    
    HStatement visit(CubexAssign n) {
        return new HAssign(n.name.name, n.expr.accept(this));
    }
    
    HStatement visit(CubexConditional n) {
        return new HConditional(n.expr.accept(this), n.stmt1.accept(this), n.stmt2.accept(this));
    }
    
    HStatement visit(CubexWhileLoop n) {
        return new HWhileLoop(n.expr.accept(this), n.stmt.accept(this));
    }
    
    HStatement visit(CubexForLoop n) {
        return new HForLoop(n.name.name, n.expr.accept(this), n.stmt.accept(this));
    }
    
    HStatement visit(CubexReturn n) {
        return new HReturn(n.expr.accept(this));
    }
    
    HInterface visit(CubexInterface n) {
        HashMap<String,HFunction> funs = new HashMap<String,HFunction>();

        for (CubexFunHeader f : n.funList) {
            HFunction fu = f.accept(this);
            // we append the class name to the method name
            funs.put(fu.name, fu);
            fu.name = n.name.name + "_" + fu.name;
        }
        int id = curId;
        curId++;
        HInterface i = new HInterface(id, n.name.name, null, null, funs, n.type.getNames());
        classes.put(n.name.name, i);
        return i;
    }
    
    HInterface visit(CubexClass n) {
        HashMap<String, HFunction> funs = new HashMap<String, HFunction>();
        List<HStatement> stmts = visitStatements(n.stmts);
        List<HExpression> exprs = visitExpressions(n.exprs);

        for (CubexFunction f : n.funList) {
            HFunction fu = f.accept(this);
            // we append the class name to the method name
            funs.put(fu.name, fu);
            fu.name = n.name.name + "_" + fu.name;
        }
        int id = curId;
        curId++;
        HClass c = new HClass(id, n.name.name, exprs, stmts, funs, n.type.getNames());
        classes.put(n.name.name, c);
        return c;
    }
    
    HFunction visit(CubexFunction n) {
        return new HFunction(n.name.name, n.body.accept(this));
    }
    
    HProg visit(CubexClassProg n) {
        return new HClassProg(n.cls.accept(this), n.prog.accept(this));
    }
    
    HProg visit(CubexInterfaceProg n) {
        return new HClassProg(n.intf.accept(this), n.prog.accept(this));
    }

    HProg visit(CubexFuncsProg n) {
        List<HFunction> funs = new ArrayList<HFunction>();
        for (CubexFunction f : n.funcs) {
            funs.add(f.accept(this));
        }
        return new HFunProg(funs, n.prog.accept(this));
    }
    
    HProg visit(CubexStatementProg n) {
        List<HStatement> s = new ArrayList<HStatement>();
        s.add(n.stmt.accept(this));
        return new HStatementProg(s, n.prog.accept(this));
    }
    
    HProg visit(CubexStatementsProg n) {
        List<HStatement> stmts = visitStatements(n.stmts);
        
        return new HStatementProg(stmts, n.prog.accept(this));
    }

    List<HStatement> visitStatements(List<CubexStatement> l) {
        List<HStatement> stmts = new ArrayList<HStatement>();
        for (CubexStatement s : l) {
            stmts.add(s.accept(this));
        }
        return stmts;
    }

    List<HExpression> visitExpressions(List<CubexExpression> l) {
        List<HExpression> exprs = new ArrayList<HExpression>();
        for (CubexExpression e : l) {
            exprs.add(e.accept(this));
        }
        return exprs;
    }
}






