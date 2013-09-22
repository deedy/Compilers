import java.util.List;

public class CubexClass {
    CubexCName name;
    List<CubexPName> kCont;
    CubexTypeContext tCont; 
    CubexType type;
    List<CubexStatement> stmt;
    List<CubexExpression> expr;
    
    public CubexClass(CubexCName c, List<CubexPName> k, CubexTypeContext t, 
        CubexType ty, List<CubexStatement> s, List<CubexExpression> e) {

        name = c;
        kCont = k;
        tCont = t;
        type = ty;
        stmt = s;
        expr = e;
    }
}