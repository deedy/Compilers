import org.antlr.v4.runtime.*;
import java.util.List;
import java.util.ArrayList;

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
        CubexType commonType = new Nothing();
        for (CubexComprehensionable elem : comprehensionableList) {
            try {
                System.out.println("ELEM: "+elem);
                switch (elem.getComprehenshionableType()) {
                    case EXPR:
                        commonType = CubexTC.join(cc, kc, commonType,
                            ((CubexExpression)elem).getType(cc, kc, fc, st));
                        break;
                    case FOR:
                        commonType = CubexTC.join(cc, kc, commonType,
                            elem.getType(cc, kc, fc, st));
                        break;
                    case IF:
                        commonType = CubexTC.join(cc, kc, commonType,
                            elem.getType(cc, kc, fc, st));
                        break;
                    default:
                        break;
                }
            } catch (CubexTC.UnexpectedTypeHierarchyException e) {
            throw new CubexTC.TypeCheckException(
                String.format("ERROR GETTING JOIN TYPE OF %s", toString())
                );
            }
        }
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
        String lst = ListPrinter.listToString(comprehensionableList, " , ");
        return String.format("[ %s ]", ListPrinter.nullify(lst));
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

    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){

        System.out.println("FOR: "+expr);

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
        // System.out.println(foundIter);
        SymbolTable tmp = st.set(name, foundIter.params.get(0));

        return cmpr.getType(cc, kc, fc, tmp);
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

    public CubexType getType(CubexClassContext cc,
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        CubexType exprType = expr.getType(cc, kc, fc, st);
        boolean isBoolean = CubexTC.subType(cc, kc, exprType, new CubexCType("Boolean"));
        if (!isBoolean) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT A BOOLEAN", exprType.toString()));
        }
        return cmpr.getType(cc, kc, fc, st);
    }

}
