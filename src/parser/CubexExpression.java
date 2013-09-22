import java.util.Collection;
import java.util.ArrayList;

public class CubexExpression {

}

class CubexFunctionCall extends CubexExpression {
    private CubexName name;
    private Collection<CubexType> typeList;
    private Collection<CubexExpression> exprList;
    public CubexFunctionCall(CubexName n, Collection<CubexType>  tl, Collection<CubexExpression> el) {
        name = n;
        typeList = tl;
        exprList = el;
    }
}

class CubexVar extends CubexExpression {
    CubexVName name;
    public CubexVar(CubexVName n) {
        name = n;
    }
}

class CubexMethodCall extends CubexExpression {
    private CubexExpression expr;
    private CubexName name;
    private Collection<CubexType> typeList;
    private Collection<CubexExpression> exprList;
    public CubexMethodCall(CubexExpression e, CubexName n, Collection<CubexType>  tl, Collection<CubexExpression> el) {
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
}

class CubexAppend extends CubexExpression {
    CubexExpression left;
    CubexExpression right;
    public CubexAppend(CubexExpression l, CubexExpression r) {
        left = l;
        right = r;
    }
}

class CubexArray extends CubexExpression {
    private Collection<? extends CubexExpression> mElements;
    public CubexArray(Collection<CubexExpression> elems) {
        mElements = elems;
    }
}

class CubexBoolean extends CubexExpression {
    private String bool;
    public CubexBoolean(String b) {
        bool = b;
    }
}

class CubexInt extends CubexExpression {
    private int num;
    public CubexInt(int i) {
        num = i;
    }
}

class CubexString extends CubexExpression {
    private String str;
    public CubexString(String s) {
        str = s;
    }
}