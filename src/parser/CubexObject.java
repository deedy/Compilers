import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public abstract class CubexObject extends CubexNode  {
    CubexCName name;
    List<CubexPName> kCont;
    CubexType type;
    List<? extends CubexFunHeader> funList;

    public abstract Pair<CubexClassContext, CubexFunctionContext> typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st);

    public abstract HNode accept(HVisitor v);
}


class CubexInterface extends CubexObject {

    public Pair<CubexClassContext, CubexFunctionContext> typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
        // make a new kind context
        CubexKindContext theta = new CubexKindContext(kCont);
        // check construction of extended type
        CubexType con = CubexTC.constructable(cc, theta, type);
        if(!con.equals(new Thing())) {
            throw new CubexTC.TypeCheckException(
                String.format("%s DOES NOT CONSTRUCT TRUE IN INTERFACE %s", 
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
        return new Pair<CubexClassContext, CubexFunctionContext>(new CubexClassContext().set(name, this), new CubexFunctionContext());

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

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }

    public HClass createHIR() {
        return null;
    }
}


class CubexClass extends CubexObject {
    List<CubexFunction> funList;
    CubexTypeContext tCont; 
    List<CubexStatement> stmts;
    List<CubexExpression> exprs;

    public Pair<CubexClassContext, CubexFunctionContext> typeCheck(CubexClassContext cc, CubexFunctionContext fc, SymbolTable st) {
        // make a new kind context
        CubexKindContext theta = new CubexKindContext(kCont);
        // check construction of extended type
        CubexType con = CubexTC.constructable(cc, theta, type);

        if(con == null) {
            throw new CubexTC.TypeCheckException(
                String.format("%s IS NOT CONSTRUCTABLE IN CLASS %s", 
                    type.toString(), name.toString())
                );
        }

        // make a new class context
        CubexClassContext ccp = new CubexClassContext().set(name, this);
        CubexClassContext cc2 = cc.set(name, this);

        // make a new function context
        ArrayList<CubexType> typeParams = new ArrayList<CubexType>();
        for(CubexPName p : kCont) {
            typeParams.add(new CubexPType(p));
        }
        CubexTypeScheme scheme = new CubexTypeScheme(kCont, tCont, new CubexCType(name, typeParams));
        CubexVName funname = new CubexVName(name.name);
        CubexFunHeader head = new CubexFunHeader(funname, scheme);
        CubexFunctionContext fcp = new CubexFunctionContext().set(funname, head);
        CubexFunctionContext fc2 = fc.set(funname, head);

        // check type context
        if(!CubexTC.isValid(cc2, theta, tCont)) {
            throw new CubexTC.TypeCheckException(
                String.format("CLASS %s HAS INVALID TYPE CONTEXT %s", 
                    name.toString(), tCont.toString())
                );
        }

        // type check the list of statements
        SymbolTable currOutgoing = new SymbolTable(tCont);
        for(CubexStatement s : stmts) {
            Triple<SymbolTable, Boolean, CubexType> imm = s.typeCheck(cc2, theta, fc2, st, currOutgoing);
            if(imm.getMiddle().booleanValue()) {
                throw new CubexTC.TypeCheckException(
                    String.format("RETURNING STATEMENT %s FOUND IN CLASS %s", 
                        s.toString(), name.toString())
                    );
            }
            currOutgoing = imm.getLeft();
        }
        SymbolTable merged = st.merge(currOutgoing);

        // type check the super call
        // if super is thing, check that expression list is empty
        if(con.equals(new Thing())) {
            if(exprs.size() > 0) {
                throw new CubexTC.TypeCheckException(
                    String.format("INVALID CALL TO SUPER IN CLASS %s", 
                        name.toString())
                    );
            }
        } else {
            // else, check that parent(exprs) is a valid function call
            CubexCType supertype = (CubexCType) con;
            CubexFunctionCall superCall = new CubexFunctionCall(supertype.name, supertype.params, exprs);
            CubexType superRet = superCall.getType(cc2, theta, fc2, merged);
            if(!CubexTC.subType(cc2, theta, superRet, con)) {
                throw new CubexTC.TypeCheckException(
                    String.format("CALL TO SUPER IN CLASS %s RETURNS %s BUT EXPECTED %s", 
                        name.toString(), superRet.toString(), con.toString())
                    );   
            }
        }

        // put all functions in the current context
        CubexFunctionContext fcpp = fc2;
        for(CubexFunction f : funList) {
            CubexVName funName = f.name;
            fcpp = fcpp.set(funName, f);
        }

        // type check each function
        for(CubexFunction f : funList) {
            // check the typescheme
            if(!CubexTC.isValid(cc2, theta, f.scheme)){
                throw new CubexTC.TypeCheckException(
                    String.format("FUNCTION %s IN CLASS %s HAS INVALID TYPESCHEME %s", 
                        f.name.toString(), name.toString(), f.scheme.toString())
                    );   
            }

            // break up the typescheme
            CubexKindContext theta_i = new CubexKindContext(f.scheme.kCont);
            SymbolTable st_i = new SymbolTable(f.scheme.tCont);
            CubexType retType = f.scheme.type;

            // check each statement
            CubexKindContext theta_m = theta.merge(theta_i);
            Triple<SymbolTable, Boolean, CubexType> ret = f.body.typeCheck(cc2, theta_m, fcpp, merged, st_i);
            // check that it returns
            if(!ret.getMiddle().booleanValue()){
                throw new CubexTC.TypeCheckException(
                    String.format("FUNCTION %s OF CLASS %s HAS NON-RETURNING BODY", 
                        f.name.toString(), name.toString())
                    ); 
            }

            // check that it returns a subtype of its declared type
            if(!CubexTC.subType(cc2, theta_m, ret.getRight(), retType)){
                throw new CubexTC.TypeCheckException(
                    String.format("FUNCTION %s OF CLASS %s RETURNS %s EXPECTED %s", 
                        f.name.toString(), name.toString(), ret.getRight().toString(), retType.toString())
                    ); 
            }
        }

        // check every method of the supertype
        // System.out.println(CubexTC.allMethods(cc, theta, type));
        for(CubexVName fun : CubexTC.allMethods(cc, theta, type)) {
            // check that the class has this method
            boolean found = false;
            CubexTypeScheme s2 = CubexTC.method(cc, theta, type, fun);
            for(CubexFunHeader f : funList) {
                if(f.name.equals(fun)) {
                    found = true;
                    // check that their typeschemes are an exact match
                    CubexTypeScheme s1 = f.scheme;
                    if(s1 == null || !s2.equals(s1)) {
                        throw new CubexTC.TypeCheckException(
                            String.format("FUNCTION %s WITH SCHEME %s IN CLASS %s DOES NOT MATCH %s IN PARENT TYPE", 
                                f.name.toString(), s1.toString(), name.toString(), s2.toString())
                            );
                    }
                }
            }
            if(!found){
                throw new CubexTC.TypeCheckException(
                    String.format("CLASS %s DOES NOT PRESENT METHOD %s FOUND IN PARENT TYPE", 
                        name.toString(), fun.toString())
                    );
            }
        }

        // all tests pass
        return new Pair<CubexClassContext, CubexFunctionContext>(ccp, fcp);
    }
    
    public CubexClass(CubexCName c, List<CubexPName> k, CubexTypeContext t, 
        CubexType ty, List<CubexStatement> s, List<CubexExpression> e, List<CubexFunction> f) {
        name = c;
        kCont = k;
        type = ty;
        funList = f;
        tCont = t;
        stmts = s;
        exprs = e;
    }

    public String toString() {
        String n = name.toString();
        String k = ListPrinter.listToString(kCont, " , ");
        String t = tCont.toString();
        String ty = type.toString();
        String s = ListPrinter.listToString(stmts, " ");
        String e = ListPrinter.listToString(exprs, " , ");
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

    public HNode accept(HVisitor v) {
        return v.visit(this);
    }

    public HClass createHIR() {
        return null;
    }
}