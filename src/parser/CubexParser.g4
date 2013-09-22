parser grammar CubexParser;
options { tokenVocab = CubexLexer; }

// TODO : finish and translate to accept full language

vname returns [CubexVName cu]
	: NAME { $cu =  new CubexVName($NAME.text); };

cname returns [CubexCName cu]
	: CLASSNAME { $cu =  new CubexCName($CLASSNAME.text); };

pname returns [CubexPName cu]
	: TYPEPARAM { $cu =  new CubexPName($TYPEPARAM.text); };

vcname returns [CubexName cu]
	: cname
	| vname;

kcont : pname? (COMMA pname)*;

tcont : (vname COLON CLASSNAME)? (COMMA vname COLON CLASSNAME)*;

type returns [CubexType cu]
	: p=pname { $cu = new CubexType($p.cu); }
	| c=cname LANGLE t=types RANGLE
		{ $cu = new CubexType($c.cu, $t.cu); }
	| t1=type AND t2=type { $cu = new CubexType($t1.cu, $t2.cu); }
	| THING { $cu = new CubexType(); }
	| NOTHING { $cu = new CubexType(); };

types returns [List<CubexType> cu] 
    : { $cu = new ArrayList<CubexType>(); }
                                        (t=type { $cu.add($t.cu); }
                                         (COMMA t=type { $cu.add($t.cu); })*
                                        )?; 

typescheme : LANGLE kcont RANGLE LPAREN tcont RPAREN COLON type;

expr returns [CubexExpression cu]
    : n=vname { $cu = new CubexVar($n.cu); }
    | l=expr PLUSPLUS r=expr { $cu = new CubexAppend($l.cu, $r.cu); }
    | c=vcname LANGLE t=types RANGLE LPAREN es=exprs RPAREN 
    	{ $cu = new CubexFunctionCall($c.cu, $t.cu, $es.cu); }
    | e=expr DOT n=vname LANGLE t=types RANGLE LPAREN es=exprs RPAREN 
    	{ $cu = new CubexMethodCall($e.cu, $n.cu, $t.cu, $es.cu); } 
    | LSQUARE list=exprs RSQUARE { $cu = new CubexArray($list.cu); }
    | BOOL { $cu = new CubexBoolean($BOOL.text); }
    | INT { $cu = new CubexInt($INT.int); }
    | STRING { $cu = new CubexString($STRING.text); };

exprs returns [List<CubexExpression> cu] 
    : { $cu = new ArrayList<CubexExpression>(); }
                                        (e=expr { $cu.add($e.cu); }
                                         (COMMA e=expr { $cu.add($e.cu); })*
                                        )?;

statement returns [CubexStatement cu]
	: LBRACE s=statements RBRACE
		{ $cu = new CubexBlock($s.cu); }
	| n=vname ASSIGN e1=expr SEMICOLON
		{ $cu = new CubexAssign($n.cu, $e1.cu); }
	| IF LPAREN e2=expr RPAREN s1=statement ELSE s2=statement
		{ $cu = new CubexConditional($e2.cu, $s1.cu, $s2.cu); }
	| WHILE LPAREN e3=expr RPAREN s3=statement
		{ $cu = new CubexWhileLoop($e3.cu, $s3.cu); }
	| FOR LPAREN n=vname IN e4=expr RPAREN s4=statement
		{ $cu = new CubexForLoop($n.cu, $e4.cu, $s4.cu); }
	| RETURN e5=expr SEMICOLON
		{ $cu = new CubexReturn($e5.cu); };

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