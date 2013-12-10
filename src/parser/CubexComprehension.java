import org.antlr.v4.runtime.*;
import java.util.List;

public class CubexComprehension extends CubexExpression {
    List<CubexComprehensionable> comprehensionableList;
    public CubexComprehension(List<CubexComprehensionable> cublist) {
        comprehensionableList = cublist;
    }

    public HExpression createHIR() {
        // TODO
        return null;
    }

    public HExpression accept(HVisitor v) {
        // TODO
        return null;
    }

    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        // TODO
        return this.type;
    }

    public String toString() {
        String lst = ListPrinter.listToString(comprehensionableList, " , ");
        return String.format("[ %s ]", ListPrinter.nullify(lst));
    }
}

interface CubexComprehensionable {
    public static enum ComprehensionableType {
        FOR, IF, EXPR
    }
    public ComprehensionableType getComprehenshionableType();
}

class CubexForComprehensionable implements CubexComprehensionable {
    CubexVName name;
    CubexExpression expr;
    CubexComprehensionable cmpr;
    public CubexForComprehensionable(CubexVName n, CubexExpression e, CubexComprehensionable c) {
        name = n;
        expr = e;
        cmpr = c;
    }

    public ComprehensionableType getComprehenshionableType() {
        return ComprehensionableType.FOR;
    }

    public String toString() {
        String n = name.toString();
        String e = expr.toString();
        String c = cmpr.toString();
        return String.format("for ( %s in %s ) %s", n, e, c);
    }
}

class CubexIfComprehensionable implements CubexComprehensionable {
    CubexComprehensionable cmpr;
    CubexExpression expr;
    public CubexIfComprehensionable(CubexExpression expr,
            CubexComprehensionable cmpr) {
        this.cmpr = cmpr;
        this.expr = expr;
    }

    public ComprehensionableType getComprehenshionableType() {
        return ComprehensionableType.IF;
    }

    public String toString() {
        String e = expr.toString();
        String c = cmpr.toString();
        return String.format("if ( %s ) %s", e, c);
    }
}
