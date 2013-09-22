parser grammar CubexParser;
options { tokenVocab = CubexLexer; }

// TODO : finish and translate to accept full language

vname returns [CubexName cn]
	: NAME { $cn =  new CubexVName($NAME.text); };

cname returns [CubexName cn]
	: CLASSNAME { $cn =  new CubexCName($CLASSNAME.text); };

pname returns [CubexName cn]
	: TYPEPARAM { $cn =  new CubexPName($TYPEPARAM.text); };

vcname: cname | vname;

kcont : pname? (COMMA pname)*;

tcont : (vname COLON CLASSNAME)? (COMMA vname COLON CLASSNAME)*;

type : vname 
	| cname LANGLE type? (COMMA type)* RANGLE
	| type AND type
	| THING | NOTHING;

types returns [List<CubexTypen> cu] 
    : { $xi = new ArrayList<CubexType>(); }
                                        (t=type { $cu.add($t.cu); }
                                         (COMMA t=type { $cu.add($t.cu); })*
                                        )?; 

typescheme : LANGLE kcont RANGLE LPAREN tcont RPAREN COLON type;

expr returns [CubexExpression cu]
    : vname { $cu = $vname.cu; }
    | l=expr PLUSPLUS r=expr { $cu = new CubexAppend(l, r); }
    | n=vcname LANGLE t=types RANGLE LPAREN e=exprs RPAREN 
    	{ $cu = new CubexFunctionCall(n, t, e); }
    | e=expr DOT n=vname LANGLE t=types RANGLE LPAREN e=exprs RPAREN 
    	{ $cu = new CubexMethodCall(e, n, t, e); } 
    | LSQUARE list=exprs RSQUARE { $cu = new CubexArray($list.cu)}
    | TRUE { $cu = new CubexBoolean(true); }
    | FALSE { $cu = new CubexBoolean(false); }
    | INTEGER { $cu = new CubexInteger($INTEGER.int); }
    | STRING { $cu = new CubexString($STRING.text); }

exprs returns [List<CubexExpression> cu] 
    : { $cu = new ArrayList<CubexExpression>(); }
                                        (e=expr { $cu.add($e.cu); }
                                         (COMMA e=expr { $cu.add($e.cu); })*
                                        )?;

statement returns [CubexStatement cu]
	: LBRACE s=statements RBRACE
		{ $cu = new CubexBlock(s); }
	| vname ASSIGN expr SEMICOLON
	| IF LPAREN e=expr RPAREN s1=statement ELSE s2=statement
		{ $cu = new CubexConditional(e, s1, s2); }
	| WHILE LPAREN e=expr RPAREN s=statement
		{ $cu = new CubexWhileLoop(e, s); }
	| FOR LPAREN n=vname IN e=expr RPAREN s=statement
		{ $cu = new CubexForLoop(n, e, s); }
	| RETURN e=expr SEMICOLON
		{ $cu = new CubexReturn(e); };

statements returns [List<CubexStatement> cu] 
    : { $cu = new ArrayList<CubexStatement>(); }
                                        (s=statement { $cu.add($s.cu); }
                                         (s=statement { $cu.add($s.cu); })*
                                        )?;

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