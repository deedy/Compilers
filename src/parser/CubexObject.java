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