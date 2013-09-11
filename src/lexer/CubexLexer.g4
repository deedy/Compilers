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

MODULO : '%';
PLUS : '+';
MINUS : '-';
LT : '<';
GT : '>';
LTE : '<=';
GTE : '>=';
EQ : '==';
NE : '!=';
AND : '&';
OR: '|';
NEGATE : '!';
TIMES : '*';
DIVIDE : '/';
RANGEOP : '..' | '<.' | '.<' | '<<' | '...' | '<..';
PLUSPLUS : '++';
COLON : ':';
EQUAL : '=';
LPAREN : '(';
RPAREN : ')';
COMMA : ',';
SEMICOLON : ';';
LBRACE : '{';
RBRACE : '}';
DOT : '.';
LSQUARE : '[';
RSQUARE : ']';

TYPE : [A-Z][a-zA-Z0-9_]*; 
NAME : [a-z][a-zA-Z0-9_]*;
WS : [ \t\n\r]+ -> skip;
INT : ([1-9][0-9]*|[0]);
BOOL : 'true' | 'false'; 
// Need to add support for ''
COMMENT : (SINGLELINECOMMENT | MULTILINECOMMENT) -> skip;
STRING : '"' ( ~'"' )* '"';

SINGLELINECOMMENT : [#].*?[\n\r] -> skip;
MULTILINECOMMENT : '`' (MULTILINECOMMENT|  ~[`\'] )* '\'';




