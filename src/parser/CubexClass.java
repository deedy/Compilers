import java.util.List;

public class CubexClass {
    CubexCName name;
    List<CubexPName> kCont;
    CubexTypeContext tCont; 
    CubexType type;
    List<CubexStatement> stmt;
    List<CubexExpression> expr;
    List<CubexFunction> funcs;
    
    public CubexClass(CubexCName c, List<CubexPName> k, CubexTypeContext t, 
        CubexType ty, List<CubexStatement> s, List<CubexExpression> e, List<CubexFunction> f) {

        name = c;
        kCont = k;
        tCont = t;
        type = ty;
        stmt = s;
        expr = e;
        funcs = f;
    }

    public String toString() {
        String n = name.toString();
        String k = ListPrinter.listToString(kCont, " , ");
        String t = tCont.toString();
        String ty = type.toString();
        String s = ListPrinter.listToString(stmt, " ");
        String e = ListPrinter.listToString(expr, " , ");
        String f = ListPrinter.listToString(funcs, " ");
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