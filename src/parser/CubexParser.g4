parser grammar CubexParser;
options { tokenVocab = CubexLexer; }



vname returns [CubexVName cn]
	: NAME { $cn =  new CubexVName($NAME.text); };