parser grammar CubexParser;
options { tokenVocab = CubexLexer; }

string: STRING;
comment: COMMENT;
type: TYPE;
name: NAME;
bool: BOOL;
integer: INT;
ws: WS;
keyword: (INTERFACE | CLASS | EXTENDS | SUPER | FUN | WHILE | IF | ELSE | IN | RETURN) ;
symbol: (MODULO | PLUS | MINUS | LT | GT | LTE | GTE | EQ | NE | AND | OR | NEGATE | TIMES | DIVIDE | RANGEOP | PLUSPLUS | COLON | EQUAL | LPAREN | RPAREN | COMMA | SEMICOLON | LBRACE | RBRACE | DOT);
other: (keyword | symbol);
file : (string | comment | type | name | bool | integer | ws | other)*;
