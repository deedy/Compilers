import org.antlr.v4.runtime.*;
import java.util.List;
import java.util.ArrayList;

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
        CubexType commonType = new Nothing();
        if (comp == null) {
            return commonType;
        }
        commonType = CubexTC.join(cc, kc, commonType,
                            comp.getType(cc, kc, fc, st));

        List<CubexType> params = new ArrayList<CubexType>();
        params.add(commonType);
        CubexType out = new CubexCType(new CubexCName("Iterable"), params);
        if(CubexTC.isValid(cc, kc, out)) {
            this.type = out;
            return this.type;
        }
        throw new CubexTC.TypeCheckException(
            String.format("TYPE %s IS NOT VALID IN %s", out.toString(), toString())
            );
    }

    public String toString() {
        if (comp != null) {
            return String.format("[ %s ]", comp.toString());
        }
        return "[ ]";
    }
}

interface CubexComprehensionable {
    public static enum ComprehensionableType {
        FOR, IF, EXPR
    }
    public ComprehensionableType getComprehenshionableType();
    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st);
}

class CubexExprComp implements CubexComprehensionable {
    CubexExpression expr;
    CubexComprehensionable comp;
    public CubexExprComp(CubexExpression e, CubexComprehensionable c) {
        expr = e;
        comp = c;
    }

    public ComprehensionableType getComprehenshionableType() {
        return ComprehensionableType.EXPR;
    }

    public String toString() {
        if (comp == null) {
            return String.format("%s", expr.toString());
        }
        String compStr = comp.toString();
        if (compStr.length() == 0) {
            return String.format("%s", expr.toString());
        }
        return String.format("%s, %s", expr.toString(), comp.toString());
    }

    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        if (comp == null) {
            return expr.getType(cc, kc, fc, st);
        }
        return CubexTC.join(cc, kc, expr.getType(cc, kc, fc, st),
            comp.getType(cc, kc, fc, st));
    }
}

class CubexForComp implements CubexComprehensionable {
    CubexVName name;
    CubexExpression expr;
    CubexComprehensionable comp;
    public CubexForComp(CubexVName n, CubexExpression e, CubexComprehensionable c) {
        name = n;
        expr = e;
        comp = c;
    }

    public String toString() {
        if (comp == null) {
            return "";
        }
        String compStr = comp.toString();
        if (compStr.length() == 0) {
            return "";
        }
        return String.format("for (%s in %s) %s", name.toString(),
            expr.toString(), compStr);
    }

    public ComprehensionableType getComprehenshionableType() {
        return ComprehensionableType.FOR;
    }

    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        if (comp == null) {
            return new Nothing();
        }
        CubexType exprType = expr.getType(cc, kc, fc, st);
        if (!exprType.isIterable(cc, kc)) {
            throw new CubexTC.TypeCheckException(
                String.format("%s OF TYPE %s IS NOT AN ITERABLE", expr.toString(), exprType));
        }
        ArrayList<CubexType> params = new ArrayList<CubexType>();
        params.add(new Nothing());
        CubexType testIter = new CubexCType(new CubexCName("Iterable"), params);
        // this cast should be safe
        CubexCType foundIter = (CubexCType) CubexTC.join(cc, kc, testIter, exprType);
        SymbolTable tmp = st.set(name, foundIter.params.get(0));
        return comp.getType(cc, kc, fc, tmp);
    }
}

class CubexIfComp implements CubexComprehensionable {
    CubexExpression cond;
    CubexComprehensionable comp;
    public CubexIfComp(CubexExpression e, CubexComprehensionable c) {
        cond = e;
        comp = c;
    }

    public String toString() {
        if (comp == null) {
            return "";
        }
        String compStr = comp.toString();
        if (compStr.length() == 0) {
            return "";
        }
        return String.format("if (%s) %s", cond.toString(), compStr);
    }

    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        if (comp == null) {
            return new Nothing();
        }
        CubexType exprType = cond.getType(cc, kc, fc, st);
        boolean isBoolean = CubexTC.subType(cc, kc, exprType, new CubexCType("Boolean"));
        if (!isBoolean) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT A BOOLEAN", exprType.toString()));
        }
        return comp.getType(cc, kc, fc, st);
    }

    public ComprehensionableType getComprehenshionableType() {
        return ComprehensionableType.IF;
    }
}
