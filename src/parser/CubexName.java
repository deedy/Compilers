public class CubexName extends CubexNode {
	String name;

	public String toString(){
		return name;
	}

	public boolean equals(Object obj) {
		if(obj instanceof CubexName) {
			CubexName n = (CubexName) obj;
			return name.equals(n.name);
		}
		else return false;
	}
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public HNode accept(HVisitor v) {
		return v.visit(this);
	}

	public String createHIR() {
        return null;
    }
}

// variable / function / method name
class CubexVName extends CubexName {
	public CubexVName(String n) {
		name = n;
	}
}

// class / interface / type
class CubexCName extends CubexName {
	public CubexCName(String n) {
		name = n;
	}
}

// type parameter names
class CubexPName extends CubexName {
	public CubexPName(String n) {
		name = n;
	}
}