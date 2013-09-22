import java.util.List;
import java.util.ArrayList;

public class CubexExpression {

}

class CubexFunctionCall extends CubexExpression {
    private CubexName name;
    private List<CubexType> typeList;
    private List<CubexExpression> exprList;
    public CubexFunctionCall(CubexName n, List<CubexType> tl, List<CubexExpression> el) {
        name = n;
        typeList = tl;
        exprList = el;
    }

    public String toString() {
        String n = name.toString();
        String tl = ListPrinter.listToString(typeList, " , ");
        String el = ListPrinter.listToString(exprList, " , ");
        return String.format("%s < %s> ( %s)", n, ListPrinter.nullify(tl), ListPrinter.nullify(el));
    }
}

class CubexVar extends CubexExpression {
    CubexVName name;
    public CubexVar(CubexVName n) {
        name = n;
    }

    public String toString() {
        return name.toString();
    }
}

class CubexMethodCall extends CubexExpression {
    private CubexExpression expr;
    private CubexName name;
    private List<CubexType> typeList;
    private List<CubexExpression> exprList;
    public CubexMethodCall(CubexExpression e, CubexName n, List<CubexType>  tl, List<CubexExpression> el) {
        expr = e;
        name = n;
        typeList = tl;
        exprList = el;
    }

    // constructor for desugaring binary operators
    public CubexMethodCall(CubexExpression e, String s, CubexExpression f) {
        expr = e;
        name = new CubexVName(s);
        typeList = new ArrayList<CubexType>();
        exprList = new ArrayList<CubexExpression>();
        exprList.add(f);
    }

    public String toString() {
        String e = expr.toString();
        String n = name.toString();
        String tl = ListPrinter.listToString(typeList, " , ");
        String el = ListPrinter.listToString(exprList, " , ");
        return String.format("%s . %s < %s> ( %s)", 
                            e, n,
                            ListPrinter.nullify(tl),
                            ListPrinter.nullify(el));
    }
}

class CubexAppend extends CubexExpression {
    CubexExpression left;
    CubexExpression right;
    public CubexAppend(CubexExpression l, CubexExpression r) {
        left = l;
        right = r;
    }

    public String toString() {
        String l = left.toString();
        String r = right.toString();
        return String.format("%s ++ %s", l, r);
    }
}

class CubexArray extends CubexExpression {
    private List<? extends CubexExpression> mElements;
    public CubexArray(List<CubexExpression> elems) {
        mElements = elems;
    }

    public String toString() {
        String elems = ListPrinter.listToString(mElements, " , ");
        return String.format("[ %s]", ListPrinter.nullify(elems));
    }
}

class CubexBoolean extends CubexExpression {
    private String bool;
    public CubexBoolean(String b) {
        bool = b;
    }

    public String toString() {
        return bool;
    }
}

class CubexInt extends CubexExpression {
    private int num;
    public CubexInt(int i) {
        num = i;
    }

    public String toString() {
        return String.format("%d", num);
    }
}

class CubexString extends CubexExpression {
    private String str;
    public CubexString(String s) {
        str = s;
    }

    public String toString() {
        return str;
    }
}