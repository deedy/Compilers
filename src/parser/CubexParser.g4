parser grammar CubexParser;
options { tokenVocab = CubexLexer; }

// TODO : finish and translate to accept full language

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

// comma separated expressions inside parens
exprs : LPAREN expr? (COMMA expr)* RPAREN;

// TODO
expr : PLUSPLUS;

statement : LBRACE statement* RBRACE
	| vname ASSIGN expr SEMICOLON
	| IF LPAREN expr RPAREN statement ELSE statement
	| WHILE LPAREN expr RPAREN statement
	| FOR LPAREN vname IN expr RPAREN statement
	| RETURN expr SEMICOLON;

fundef : FUN vname typescheme;

interfacedef : 
	INTERFACE cname LANGLE kcont RANGLE 
	EXTENDS type LBRACE (fundef SEMICOLON)* RBRACE;

classdef : 
	CLASS cname LANGLE kcont RANGLE LPAREN tcont RPAREN
	EXTENDS type LBRACE statement* SUPER exprs SEMICOLON
	(fundef statement)* RBRACE;

prog : statement
	| statement+ prog
	| (fundef statement)+ prog
	| interfacedef prog
	| classdef prog;