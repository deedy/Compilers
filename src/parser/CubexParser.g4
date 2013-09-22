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

kcont returns [List<CubexPName> cu] 
    : { $cu = new ArrayList<CubexPName>(); }
                                        (p=pname { $cu.add($p.cu); }
                                         (COMMA p=pname { $cu.add($p.cu); })*
                                        )?; 
tcont returns [CubexTypeContext cu]
    : { $cu = new CubexTypeContext(); }
                                        (v=vname COLON t=type { $cu.add($v.cu, $t.cu); }
                                         (COMMA v=vname COLON t=type { $cu.add($v.cu, $t.cu); })*
                                        )?; 

type returns [CubexType cu]
	: p=pname { $cu = new CubexPType($p.cu); }
	| c=cname LANGLE t=types RANGLE
		{ $cu = new CubexCType($c.cu, $t.cu); }
	| t1=type AND t2=type { $cu = new CubexIType($t1.cu, $t2.cu); }
	| THING { $cu = CubexType.getThing(); }
	| NOTHING { $cu = CubexType.getNothing(); };

types returns [List<CubexType> cu] 
    : { $cu = new ArrayList<CubexType>(); }
                                        (t=type { $cu.add($t.cu); }
                                         (COMMA t=type { $cu.add($t.cu); })*
                                        )?; 

typescheme returns [CubexTypeScheme cu]
	: LANGLE k=kcont RANGLE LPAREN tc=tcont RPAREN COLON t=type
		{ $cu = new CubexTypeScheme($k.cu, $tc.cu, $t.cu); };

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
    | STRING { $cu = new CubexString($STRING.text); }
    // binary operators
    | e1=expr PLUS e2=expr {
    	$cu = new CubexMethodCall($e1.cu, "plus", $e2.cu);
    };

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

funheader returns [CubexFunHeader cu]
	: FUN v=vname t=typescheme
		{ $cu = new CubexFunHeader($v.cu, $t.cu); };

funheaders returns [List<CubexFunHeader> cu] 
    : { $cu = new ArrayList<CubexFunHeader>(); }
                                        (c=funheader { $cu.add($c.cu); }
                                         (SEMICOLON c=funheader { $cu.add($c.cu); })*
                                        )?;

fundef returns [CubexFunction cu]
	: FUN v=vname t=typescheme s=statement
		{ $cu = new CubexFunction($v.cu, $t.cu, $s.cu); };

funsdef returns [List<CubexFunction> cu] 
    : { $cu = new ArrayList<CubexFunction>(); }
                                        (c=fundef { $cu.add($c.cu); }
                                         (SEMICOLON c=fundef { $cu.add($c.cu); })*
                                        )?;

interfacedef returns [CubexInterface cu]
	: INTERFACE c=cname LANGLE k=kcont RANGLE 
	EXTENDS t=type LBRACE f=funheaders RBRACE
		{ $cu = new CubexInterface($c.cu, $k.cu, $t.cu, $f.cu); };

classdef returns [CubexClass cu]: 
	CLASS c=cname LANGLE k=kcont RANGLE LPAREN t=tcont RPAREN
	EXTENDS ty=type LBRACE s=statements SUPER e=exprs SEMICOLON
	funsdef RBRACE
		{ $cu = new CubexClass($c.cu, $k.cu, $t.cu, $ty.cu, $s.cu, $e.cu); };

progs returns [List<CubexProg> cu]
	: { $cu=new ArrayList<CubexProg>(); }
	(s=statement { 
		$cu.add(new CubexStatementProg($s.cu)); 
	}                                  |
	// match more than one statement 
	s1=statement s2=statements { 
		$s2.cu.add(0, $s1.cu);
		$cu.add(new CubexStatementsProg($s2.cu)); 
	}                                  |
	// match more than one function
	f1=fundef f2=funsdef {
		$f2.cu.add(0, $f1.cu);
		$cu.add(new CubexFuncsProg($f2.cu)); 
	}                                  |
	i=interfacedef { 
		$cu.add(new CubexInterfaceProg($i.cu)); 
	}                                  | 
	c=classdef { 
		$cu.add(new CubexClassProg($c.cu)); 
	}                                   )* 
	// can only logically end with a statement
	s=statement { 
		$cu.add(new CubexStatementProg($s.cu)); 
	};

prog returns [CubexProgs cu]
	: p=progs {$cu = new CubexProgs($p.cu);};