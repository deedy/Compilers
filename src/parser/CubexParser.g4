parser grammar CubexParser;
options { tokenVocab = CubexLexer; }

vname returns [CubexVName cn]
	: NAME { $cn =  new CubexVName($NAME.text); };

cname returns [CubexCName cn]
	: CLASSNAME { $cn =  new CubexCName($CLASSNAME.text); };

pname returns [CubexPName cn]
	: TYPEPARAM { $cn =  new CubexPName($TYPEPARAM.text); };

kcont : pname? (COMMA pname)*;

tcont : (vname COLON CLASSNAME)? (COMMA vname COLON CLASSNAME)*;

type : vname 
	| cname LANGLE type? (COMMA type)* RANGLE
	| type AND type
	| THING | NOTHING;

typescheme : LANGLE kcont RANGLE LPAREN tcont RPAREN COLON type;

// TODO
expr : PLUSPLUS;

statement : LBRACE statement* RBRACE
	| vname ASSIGN expr ';'
	| IF LPAREN expr RPAREN statement ELSE statement
	| WHILE LPAREN expr RPAREN statement
	| FOR LPAREN vname IN expr RPAREN statement
	| RETURN expr ';'; 