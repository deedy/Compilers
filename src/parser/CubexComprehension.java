import org.antlr.v4.runtime.*;
import java.util.List;

public class CubexComprehension extends CubexExpression {
    CubexComprehensionable comp;
    public CubexComprehension(CubexComprehensionable c) {
        comp = c;
    }

    public HExpression createHIR() {
        return null;
    }

    public HExpression accept(HVisitor v) {
        return v.visit(this);
    }

    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        // TODO
        return this.type;
    }

    public String toString() {
        return String.format("[ %s ]", comp.toString());
    }
}

abstract class CubexComprehensionable {

}

class CubexExprComp extends CubexComprehensionable {
    CubexExpression expr;
    CubexComprehensionable comp;
    public CubexExprComp(CubexExpression e, CubexComprehensionable c) {
        expr = e;
        comp = c;
    }

    public String toString() {
        if (comp != null) {
            return String.format("%s, %s", expr.toString(), comp.toString());
        } else {
            return String.format("%s", expr.toString());
        }

    }
}

class CubexForComp extends CubexComprehensionable {
    CubexVName name;
    CubexExpression expr;
    CubexComprehensionable comp;
    public CubexForComp(CubexVName n, CubexExpression e, CubexComprehensionable c) {
        name = n;
        expr = e;
        comp = c;
    }

    public String toString() {
        return String.format("for (%s in %s) %s", name.toString(), expr.toString(), comp.toString());
    }
}

class CubexIfComp extends CubexComprehensionable {
    CubexExpression cond;
    CubexComprehensionable comp;
    public CubexIfComp(CubexExpression e, CubexComprehensionable c) {
        cond = e;
        comp = c;
    }

    public String toString() {
        return String.format("if (%s) %s", cond.toString(), comp.toString());
    }
}