parser grammar CubexParser;
options { tokenVocab = CubexLexer; }

// TODO : finish and translate to accept full language

vname returns [CubexName cn]
	: NAME { $cn =  new CubexVName($NAME.text); };

cname returns [CubexName cn]
	: CLASSNAME { $cn =  new CubexCName($CLASSNAME.text); };

pname returns [CubexName cn]
	: TYPEPARAM { $cn =  new CubexPName($TYPEPARAM.text); };
