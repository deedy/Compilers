import java.util.List;
import java.util.ArrayList;

public abstract class CubexExpression {

    /**
        Returns the type of this class expression or throws an error
    */
    public abstract CubexType getType(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st);
}


// every function of a type context

class CubexFunctionCall extends CubexExpression {
    CubexName name;
    List<CubexType> typeList;
    List<CubexExpression> exprList;

    public CubexType getType(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        
        return fc.get(this.name).scheme.type;
    }

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

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        return fc.get(this.name).scheme.type;
    }
}

class CubexMethodCall extends CubexExpression {
    CubexExpression expr;
    CubexVName name;
    List<CubexType> typeList;
    List<CubexExpression> exprList;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){   
        return fc.get(this.name).scheme.type;
    }

    public CubexMethodCall(CubexExpression e, CubexVName n, List<CubexType>  tl, List<CubexExpression> el) {
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
            name = new CubexVName("onwards");
            exprList.add(new CubexBoolean(b));
        } else {
            name = new CubexVName(s);
        }
    }

    // constructor for desugaring binary operators
    public CubexMethodCall(CubexExpression e, String s, CubexExpression f) {
        expr = e;
        boolean isRange = false;
        boolean isEq = false;
        boolean isNeg = false;
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
        } else if(s.equals("==")){
            isEq = true;
        } else if(s.equals("!=")){
            isEq = true;
            isNeg = true;
        }
        typeList = new ArrayList<CubexType>();
        exprList = new ArrayList<CubexExpression>();
        if(isRange){
            name = new CubexVName("through");
            exprList.add(f);
            exprList.add(new CubexBoolean(b1));
            exprList.add(new CubexBoolean(b2));
        } else if (isEq) {
            if (isNeg) {
                // create a new method call to the equals
                expr = new CubexMethodCall(e, "==", f);
                // make this method call a negate
                name = new CubexVName("negate");
            } else {
                name = new CubexVName("equals");
                exprList.add(f);
            }
        } else {
            name = new CubexVName(s);
            exprList.add(f);   
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

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        return new CubexCType("XXX");
    }

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

class CubexIterable extends CubexExpression {
    List<? extends CubexExpression> mElements;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        return new CubexCType("Iterable");
    }

    public CubexIterable(List<CubexExpression> elems) {
        mElements = elems;
    }

    public String toString() {
        String elems = ListPrinter.listToString(mElements, " , ");
        return String.format("[ %s]", ListPrinter.nullify(elems));
    }
}

class CubexBoolean extends CubexExpression {
    String bool;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        return new CubexCType("Boolean");
    }


    public CubexBoolean(String b) {
        bool = b;
    }

    public String toString() {
        return bool;
    }
}

class CubexInt extends CubexExpression {
    int num;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        return new CubexCType("Integer");
    }


    public CubexInt(int i) {
        num = i;
    }

    public String toString() {
        return String.format("%d", num);
    }
}

class CubexString extends CubexExpression {
    String str;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        return new CubexCType("String");
    }


    public CubexString(String s) {
        str = s;
    }

    public String toString() {
        return str;
    }
}