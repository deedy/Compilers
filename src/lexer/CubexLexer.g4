lexer grammar CubexLexer;

INTERFACE : 'interface';
CLASS : 'class';
EXTENDS : 'extends';
SUPER : 'super';
FUN : 'fun';
WHILE : 'while';
IF : 'if';
ELSE : 'else';
IN : 'in';
RETURN : 'return';
FOR: 'for';
THING : 'Thing';
NOTHING : 'Nothing';

MINUS : '-';
NEGATE : '!';
TIMES : '*';
DIVIDE : '/';
MODULO : '%';
PLUS : '+';
RANGEOPBINARY : '..' | '<.' | '.<' | '<<';
RANGEOPUNARY : '...' | '<..';
PLUSPLUS : '++';
LANGLE : '<';
RANGLE : '>';
LTE : '<=';
GTE : '>=';
EQ : '==';
NE : '!=';
AND : '&';
OR: '|';
ASSIGN: ':=';
LPAREN : '(';
RPAREN : ')';
COMMA : ',';
COLON : ':';
EQUAL : '=';
SEMICOLON : ';';
LBRACE : '{';
RBRACE : '}';
DOT : '.';
LSQUARE : '[';
RSQUARE : ']';

BOOL : 'true' | 'false'; 
TYPEPARAM : [A-Z];
CLASSNAME : [A-Z][a-zA-Z0-9_]+; 
NAME : [a-z][a-zA-Z0-9_]*;
WS : [ \t\n\r]+ -> skip;
INT : ([1-9][0-9]*|[0]);
// Need to add support for ''
COMMENT : (SINGLELINECOMMENT | MULTILINECOMMENT) -> skip;
STRING : '"' ( ~[\"\n\r] )* '"';

SINGLELINECOMMENT : '#' ~[\r\n]* -> skip;
MULTILINECOMMENT : '`' (MULTILINECOMMENT|  ~[`\'] )* '\'';

ERRORCHAR : .;

