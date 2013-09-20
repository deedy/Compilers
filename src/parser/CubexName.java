public abstract class CubexName {
	String name;
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