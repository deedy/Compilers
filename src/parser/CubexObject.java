import java.util.List;
import java.util.ArrayList;

public abstract class CubexObject {
    CubexCName name;
    List<CubexPName> kCont;
    CubexType type;
    List<? extends CubexFunHeader> funList;
}


class CubexInterface extends CubexObject {

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
//         kCont.add(new CubexPName("E"));
//         tCont = new CubexTypeContext();
//         type = CubexType.getThing();
//         stmt = new ArrayList<CubexStatement>();
//         expr = new ArrayList<CubexExpression>();
//         funList = new ArrayList<CubexFunction>();
//     }

//     boolean negate(){

//     } 

//     boolean and (boolean that){

//     }

//     boolean or (boolean that){

//     }

//     ArrayList<boolean> through (boolean upper, boolean includeLower, boolean includeUpper){

//     }

//     ArrayList<boolean> onwards (boolean inclusive){

//     }

//     boolean lessThan (boolean that, boolean strict){

//     }

//     boolean equals (boolean that){

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

//     } 

//     int times (int factor){

//     } 

//     ArrayList<Integer> divide (int divisor){

//     } 

//     ArrayList<Integer> modulo (int modulus){

//     }

//     int plus (int summand){

//     } 

//     int minus (int subtrahend){

//     }

//     ArrayList through (int upper, boolean includeLower,boolean includeUpper){

//     }

//     ArrayList onwards (boolean inclusive){

//     }

//     boolean lessThan (int that, boolean strict){

//     }

//     boolean equals (int that){

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

//     }

//     boolean equals (char that){

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

//  } 

// }
