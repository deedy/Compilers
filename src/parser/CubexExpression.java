import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.antlr.v4.runtime.*;


public abstract class CubexExpression extends CubexNode implements HVisitor{

    /**
        Returns the type of this class expression or throws an error
    */
    public abstract CubexType getType(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st);

    public abstract HNode visit(CubexNode c);
}

// every function of a type context

class CubexFunctionCall extends CubexExpression {
    CubexName name;
    List<CubexType> typeList;
    List<CubexExpression> exprList;

    public CubexType getType(CubexClassContext cc, 
        CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        // check if name in function context
        CubexFunHeader f = fc.get(name);
        if(f == null) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT IN FUNCTION CONTEXT", 
                    name)
                );
        }
        // get the de-generic'd type scheme
        CubexTypeScheme swapped = CubexTC.replaceGenerics(f.scheme.kCont, typeList, f.scheme);
        // show that each expression is a subtype
        List<CubexType> expected = swapped.tCont.types;
        for(int i = 0; i < exprList.size(); i++) {
            // get the expression
            CubexExpression expr = exprList.get(i);
            // get its type
            CubexType eType = expr.getType(cc, kc, fc, st);
            // get the type from the scheme
            CubexType exp = expected.get(i);
            // show that it is a subtype
            if(!CubexTC.subType(cc, kc, eType, exp)){
                // not a subtype, error
                // System.out.println(swapped);
                throw new CubexTC.TypeCheckException(
                    String.format("%s IS NOT A SUBTYPE OF %s IN FUNCTION CALL %s", 
                        eType.toString(), exp.toString(), toString())
                    );
            }
        }
        //check the validity of the return type
        if(CubexTC.isValid(cc, kc, swapped.type)) return swapped.type;
        throw new CubexTC.TypeCheckException(
            String.format("%s IS NOT A VALID TYPE IN FUNCTION CALL %s", 
                swapped.type, toString())
            );

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

    public HNode visit(CubexNode c) {
        return null;
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
        // simply return the type from the symbol table
        if(st.contains(name)) return st.get(name);
        throw new CubexTC.TypeCheckException(
            String.format("%s IS NOT IN SYMBOL TABLE", name.toString())
        );
    }

    public HNode visit(CubexNode c) {
        return null;
    }
}

class CubexMethodCall extends CubexExpression {
    CubexExpression expr;
    CubexVName name;
    List<CubexType> typeList;
    List<CubexExpression> exprList;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        // get the type of the base
        CubexType base = expr.getType(cc, kc, fc, st);
        // get the typescheme of the method
        CubexTypeScheme s = CubexTC.method(cc, kc, base, name);
        if(s == null) {
            throw new CubexTC.TypeCheckException(
                String.format("METHOD CALL %s IS NOT VALID", 
                    this.toString())
                );
        }
        // swap out generics
        CubexTypeScheme swapped = CubexTC.replaceGenerics(s.kCont, typeList, s);
        // show that each expression is a subtype
        List<CubexType> expected = swapped.tCont.types;
        for(int i = 0; i < exprList.size(); i++) {
            // get the expression
            CubexExpression expr = exprList.get(i);
            // get its type
            CubexType eType = expr.getType(cc, kc, fc, st);
            // get the type from the scheme
            CubexType exp = expected.get(i);
            // show that it is a subtype
            if(!CubexTC.subType(cc, kc, eType, exp)){
                throw new CubexTC.TypeCheckException(
                    String.format("%s IS NOT A SUBTYPE OF %s IN METHOD CALL %s", 
                        eType.toString(), exp.toString(), toString())
                    );
            }
        }
        //check the validity of the return type
        if(CubexTC.isValid(cc, kc, swapped.type)) return swapped.type;
        throw new CubexTC.TypeCheckException(
            String.format("%s IS NOT A VALID TYPE IN METHOD CALL %s", 
                swapped.type, toString())
            );
    }

    public CubexMethodCall(CubexExpression e, CubexVName n, List<CubexType>  tl, List<CubexExpression> el) {
        expr = e;
        name = n;
        typeList = tl;
        exprList = el;
    }

    // constructor for desugaring unary prefixes and suffixes
    public CubexMethodCall(CubexExpression e, Token t) {
        String s = desugarUnaryOp.get(CubexLexer.ruleNames[t.getType()-1]);
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
    private static final Map<String, String> desugarUnaryOp = new HashMap<String, String>(){
        {
            put("STRICTONWARDSUNARYOP", "...");
            put("OPENONWARDSUNARYOP", "<..");
            put("MINUS", "negative");
            put("NEGATE", "negate");
        }
    };

    private static final Map<String, String> desugarBinOp = new HashMap<String, String>(){
        {
            put("TIMES", "times");
            put("PLUS", "plus");
            put("DIVIDE", "divide");
            put("MODULO", "modulo");
            put("MINUS", "minus");
            put("EQ", "==");
            put("NE", "!=");
            put("AND", "and");
            put("OR", "or");
            put("STRICTSTRICTBINOP", "..");
            put("OPENSTRICTBINOP", "<.");
            put("STRICTOPENBINOP", ".<");
            put("OPENOPENBINOP", "<<");
            put("RANGLE", ">");
            put("LANGLE", "<");
            put("LTE", "<=");
            put("GTE", ">=");
        }
    };

    public CubexMethodCall(CubexExpression e, Token t, CubexExpression f) {
        this(e, desugarBinOp.get(CubexLexer.ruleNames[t.getType()-1]), f);
    }

    // constructor for desugaring binary operators
    private CubexMethodCall(CubexExpression e, String s, CubexExpression f) {
        expr = e;
        boolean isRange = false;
        boolean isEq = false;
        boolean isNeg = false;
        String b1 = "", b2 = "";
        // Binary Range Operators
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
            // NEGATE and EQ 
            if (isNeg) {
                // create a new method call to the equals
                expr = new CubexMethodCall(e, "==", f);
                // make this method call a negate
                name = new CubexVName("negate");
            } else {
                name = new CubexVName("equals");
                exprList.add(f);
            }
        } else if (isInequalityOperator(s)) {
            // INEQUALITY OPERATOR
            String strictness = "true";
            name = new CubexVName("lessThan");
            if (s.equals("<=") || s.equals(">=")) {
                strictness = "false" ;
            }
            if (s.equals("<") || s.equals("<=")) {
                expr = e;
                exprList.add(f);
            } else {
                expr = f;
                exprList.add(e);
            }
            exprList.add(new CubexBoolean(strictness));
        } else {
            name = new CubexVName(s);
            exprList.add(f);   
        }
    }

    private boolean isInequalityOperator(String s) {
        return s.equals("<=") || s.equals(">=") || s.equals("<") || s.equals(">");
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

    public HNode visit(CubexNode c) {
        return null;
    }
}

class CubexAppend extends CubexExpression {
    CubexExpression left;
    CubexExpression right;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st){
        // get the types of left and right
        CubexType lt = left.getType(cc, kc, fc, st);
        CubexType rt = right.getType(cc, kc, fc, st);

        // check that they are iterables
        if(!lt.isIterable(cc, kc) || !rt.isIterable(cc,kc)) {
            throw new CubexTC.TypeCheckException(
                String.format("%s OR %s ARE NOT ITERABLE", lt.toString(), rt.toString())
                );
        }
        CubexType out = new Nothing();
        CubexType commonType = new Nothing();
        try {
            commonType = CubexTC.join(cc, kc, lt, rt);
            
        } catch(CubexTC.UnexpectedTypeHierarchyException e){
            throw new CubexTC.TypeCheckException(
                String.format("ERROR GETTING JOIN TYPE OF %s", toString())
                );
        }
        // check for validity
        // System.out.printf("%s : %s ++ %s : %s -> %s\n", left, lt, right, rt, commonType);
        if(CubexTC.isValid(cc, kc, commonType)) return out;
        throw new CubexTC.TypeCheckException(
            String.format("TYPE %s IS NOT VALID IN %s", out.toString(), toString())
            );
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

    public HNode visit(CubexNode c) {
        return null;
    }
}

class CubexIterable extends CubexExpression {
    List<? extends CubexExpression> mElements;

    public CubexType getType(CubexClassContext cc, 
    CubexKindContext kc, CubexFunctionContext fc, SymbolTable st) {
        CubexType commonType = new Nothing();
        for (CubexExpression elem : mElements) {
            try {
                commonType = CubexTC.join(cc, kc, commonType, elem.getType(cc, kc, fc, st));
            } catch (CubexTC.UnexpectedTypeHierarchyException e) {
            throw new CubexTC.TypeCheckException(
                String.format("ERROR GETTING JOIN TYPE OF %s", toString())
                );
            }
        }
        List<CubexType> params = new ArrayList<CubexType>();
        params.add(commonType);
        CubexType out = new CubexCType(new CubexCName("Iterable"), params);
        if(CubexTC.isValid(cc, kc, out)) return out;
        throw new CubexTC.TypeCheckException(
            String.format("TYPE %s IS NOT VALID IN %s", out.toString(), toString())
            );
    }

    public CubexIterable(List<CubexExpression> elems) {
        mElements = elems;
    }

    public String toString() {
        String elems = ListPrinter.listToString(mElements, " , ");
        return String.format("[ %s]", ListPrinter.nullify(elems));
    }

    public HNode visit(CubexNode c) {
        return null;
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

    public HNode visit(CubexNode c) {
        return null;
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

    public HNode visit(CubexNode c) {
        return null;
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

    public HNode visit(CubexNode c) {
        return null;
    }
}

