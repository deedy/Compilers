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

    // constructor for desugaring unary prefixes and suffixes
    public CubexMethodCall(CubexExpression e, String s) {
        expr = e;
        boolean isRange = false;
        String b = "";
        if(s.equals("...")) {
            b = "true";
            isRange = true;
        } else if (s.equals("<..")) {
            b = "false";
            isRange = true;
        } 
        typeList = new ArrayList<CubexType>();
        exprList = new ArrayList<CubexExpression>();
        if(isRange) {
            name = new CubexVName("onward");
            exprList.add(new CubexBoolean(b));
        } else {
            name = new CubexVName(s);
        }
    }

    // constructor for desugaring binary operators
    public CubexMethodCall(CubexExpression e, String s, CubexExpression f) {
        expr = e;
        boolean isRange = false;
        String b1 = "", b2 = "";
        if(s.equals("..")) {
            b1 = "true";
            b2 = "true";
            isRange = true;
        } else if (s.equals("<.")) {
            b1 = "false";
            b2 = "true";
            isRange = true;
        } else if (s.equals(".<")) {
            b1 = "true";
            b2 = "false";
            isRange = true;
        } else if (s.equals("<<")) {
            b1 = "false";
            b2 = "false";
            isRange = true;
        } 
        typeList = new ArrayList<CubexType>();
        exprList = new ArrayList<CubexExpression>();
        exprList.add(f);
        if(isRange){
            exprList.add(new CubexBoolean(b1));
            exprList.add(new CubexBoolean(b2));
            name = new CubexVName("through");
        } else {
            name = new CubexVName(s);   
        }
    }

    // constructor for ineqaulity operators
    public CubexMethodCall(CubexExpression e, String n, CubexExpression f, String strict) {
        expr = e;
        name = new CubexVName(n);
        typeList = new ArrayList<CubexType>();
        exprList = new ArrayList<CubexExpression>();
        exprList.add(f);
        exprList.add(new CubexBoolean(strict));
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