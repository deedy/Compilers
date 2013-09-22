import java.util.List;

public class CubexInterface {
    CubexCName name;
    List<CubexPName> kCont;
    CubexType type;
    List<CubexFunHeader> funList;
    public CubexInterface(CubexCName c, List<CubexPName> k, CubexType t, List<CubexFunHeader> f) {
        name = c;
        kCont = k;
        type = t;
        funList = f;
    }

    public String toString() {
        String n = name.toString();
        String k = ListPrinter.listToString(kCont, " , ");
        String t = type.toString();
        String f = ListPrinter.listToString(funList, " ; ");
        return String.format("interface %s < %s> extends %s { %s}", 
                            n, ListPrinter.nullify(k),
                            ListPrinter.nullify(t),
                            ListPrinter.nullify(f));
    }
}