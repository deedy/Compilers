import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.tuple.ImmutablePair;

public abstract class CubexObject {
    CubexCName name;
    List<CubexPName> kCont;
    CubexType type;
    List<? extends CubexFunHeader> funList;

    public abstract ImmutablePair<CubexClassContext, CubexFunctionContext> typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st);
}


class CubexInterface extends CubexObject {

    public ImmutablePair<CubexClassContext, CubexFunctionContext> typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
        // make a new kind context
        CubexKindContext theta = new CubexKindContext(kCont);
        // check construction of extended type
        CubexType con = CubexTC.constructable(cc, theta, type);
        if(!con.equals(new Thing())) {
            throw new CubexTC.TypeCheckException(
                String.format("%s DOES NOT CONSTRUCT TRUE IN INTERFACE", 
                    type.toString(), name.toString())
                );
        }

        // make a new class context
        CubexClassContext cc2 = cc.set(name, this);

        // get all the methods the parent must implement
        Collection<CubexVName> parentMethods = CubexTC.allMethods(cc, theta, type);

        // check every fun header for validity and match with parent
        for(CubexFunHeader header : funList) {
            CubexTypeScheme s = header.scheme;
            // check if valid
            if(!CubexTC.isValid(cc2, theta, s)) {
                throw new CubexTC.TypeCheckException(
                    String.format("%s IS AN INVALID TYPESCHEME IN INTERFACE %s", 
                        s.toString(), name.toString())
                    );
            }

            // check if parent has this function
            for(CubexVName n2 : parentMethods) {
                if(n2.equals(header.name)) {
                    CubexTypeScheme s2 = CubexTC.method(cc, theta, type, n2);
                    // signatures must be exactly the same
                    if(!s.equals(s2)) {
                        throw new CubexTC.TypeCheckException(
                            String.format("FUNCTION %s WITH SCHEME %s IN INTERFACE %s DOES NOT MATCH %s IN PARENT TYPE", 
                                header.name.toString(), s.toString(), name.toString(), s2.toString())
                            );
                    }
                }
            }
        }

        // everything passes
        return new ImmutablePair<CubexClassContext, CubexFunctionContext>(new CubexClassContext().set(name, this), new CubexFunctionContext());

    }

    public CubexInterface(CubexCName c, List<CubexPName> k, CubexType t, List<CubexFunHeader> f) {
        name = c;
        kCont = k;
        type = t;
        funList = f;
    }

    protected CubexInterface() {}

    public String toString() {
        String n = name.toString();
        String k = ListPrinter.listToString(kCont, " , ");
        String t = type.toString();
        String f = ListPrinter.listToString(funList, " ");
        return String.format("interface %s < %s> extends %s { %s}", 
                            n, ListPrinter.nullify(k),
                            t,
                            ListPrinter.nullify(f));
    }
}


class CubexClass extends CubexObject {

    CubexTypeContext tCont; 
    List<CubexStatement> stmt;
    List<CubexExpression> expr;

    public ImmutablePair<CubexClassContext, CubexFunctionContext> typeCheck(CubexClassContext cc, CubexFunctionContext kc, SymbolTable st) {
        return null;
    }
    
    public CubexClass(CubexCName c, List<CubexPName> k, CubexTypeContext t, 
        CubexType ty, List<CubexStatement> s, List<CubexExpression> e, List<CubexFunction> f) {
        name = c;
        kCont = k;
        type = ty;
        funList = f;
        tCont = t;
        stmt = s;
        expr = e;
    }

    protected CubexClass() {

    }

    public String toString() {
        String n = name.toString();
        String k = ListPrinter.listToString(kCont, " , ");
        String t = tCont.toString();
        String ty = type.toString();
        String s = ListPrinter.listToString(stmt, " ");
        String e = ListPrinter.listToString(expr, " , ");
        String f = ListPrinter.listToString(funList, " ");
        return String.format("class %s < %s> ( %s) extends %s { %ssuper ( %s) ; %s}",
                            n, 
                            ListPrinter.nullify(k), 
                            ListPrinter.nullify(t),
                            ty, 
                            ListPrinter.nullify(s),
                            ListPrinter.nullify(e),
                            ListPrinter.nullify(f));
    }
}


class CubexIterableClass extends CubexClass {
    public CubexIterableClass() {
        super();
        name = new CubexCName("Iterable");
        kCont = new ArrayList<CubexPName>();
        kCont.add(new CubexPName("E"));
        tCont = new CubexTypeContext();
        type = CubexType.getThing();
        stmt = new ArrayList<CubexStatement>();
        expr = new ArrayList<CubexExpression>();
        funList = new ArrayList<CubexFunction>();
    }

}

// class CubexBooleanClass extends CubexClass {
//     public CubexBooleanClass(){
//         super();
//         name = new CubexCName("Boolean");
//         kCont = new ArrayList<CubexPName>();
//         tCont = new CubexTypeContext();
//         type = CubexType.getThing();
//         stmt = new ArrayList<CubexStatement>();
//         expr = new ArrayList<CubexExpression>();
//         funList = new ArrayList<CubexFunction>();
    
//         CubexFunction negate = new CubexFunction();
//         negate.name = "negate";
// RESUME
//         funList.append(CubexFunction("negate", tCont, negate));
//     }

//     boolean negate(){
//         return true;
//     } 

//     boolean and(boolean that){
//         return true;
//     }

//     boolean or(boolean that){
//         return true;
//     }

//     ArrayList<boolean> through(boolean upper, boolean includeLower, boolean includeUpper){
//         return new ArrayList();
//     }

//     ArrayList<boolean> onwards(boolean inclusive){
//         return new ArrayList();
//     }

//     boolean lessThan(boolean that, boolean strict){
//         return true;
//     }

//     boolean equals(boolean that){
//         return true;
//     }

// }


// class CubexIntegerClass extends CubexClass {

//     public CubexIntegerClass(){
//         super();
//         name = new CubexCName("Integer");
//         kCont = new ArrayList<CubexPName>();
//         kCont.add(new CubexPName("E"));
//         tCont = new CubexTypeContext();
//         type = CubexType.getThing();
//         stmt = new ArrayList<CubexStatement>();
//         expr = new ArrayList<CubexExpression>();
//         funList = new ArrayList<CubexFunction>();
//     }

//     int negative (){
//         return 0;
//     } 

//     int times (int factor){
//         return 0;
//     } 

//     ArrayList<Integer> divide (int divisor){
//         return new ArrayList();
//     } 

//     ArrayList<Integer> modulo (int modulus){
//         return new ArrayList();
//     }

//     int plus (int summand){
//         return 0;
//     } 

//     int minus (int subtrahend){
//         return 0;
//     }

//     ArrayList through (int upper, boolean includeLower,boolean includeUpper){
//         return new ArrayList();
//     }

//     ArrayList onwards (boolean inclusive){
//         return new ArrayList();
//     }

//     boolean lessThan (int that, boolean strict){
//         return true;
//     }

//     boolean equals (int that){
//         return true;
//     }

// }


// class CubexCharacterClass extends CubexClass {

//     public CubexCharacterClass(){
//         super();
//         name = new CubexCName("Character");
//         kCont = new ArrayList<CubexPName>();
//         kCont.add(new CubexPName("E"));
//         tCont = new CubexTypeContext();
//         type = CubexType.getThing();
//         stmt = new ArrayList<CubexStatement>();
//         expr = new ArrayList<CubexExpression>();
//         funList = new ArrayList<CubexFunction>();
//     }


//     int unicode(){
//         return 0;
//     }

//     boolean equals (char that){
//         return true;
//     }

// }


// class CubexStringClass extends ArrayList<Character> {

//         public CubexStringClass(){
//         super();
//         name = new CubexCName("String");
//         kCont = new ArrayList<CubexPName>();
//         kCont.add(new CubexPName("E"));
//         tCont = new CubexTypeContext();
//         type = CubexType.getThing();
//         stmt = new ArrayList<CubexStatement>();
//         expr = new ArrayList<CubexExpression>();
//         funList = new ArrayList<CubexFunction>();
//     }

//  boolean equals(String that){
//     return true;
//  } 

// }
