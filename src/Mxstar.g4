grammar Mxstar;

// Char Set
Add : '+';
Sub : '-';
Mul : '*';
Div : '/';
Mod : '%';

Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
Equal : '==';
NotEqual : '!=';

AndAnd : '&&';
OrOr : '||';
Not : '!';

LeftShift : '<<';
RightShift : '>>';
And : '&';
Or : '|';
Caret : '^';
Tilde : '~';

Assign : '=';

AddAdd : '++';
SubSub : '--';

Dot : '.';
LParen : '(';
RParen : ')';
LBrack : '[';
RBrack : ']';
LBrace : '{';
RBrace : '}';
Question : '?';
Colon : ':';
Semi : ';';
Comma : ',';

// Keyword
Void: 'void';
Bool: 'bool';
Int : 'int';
String: 'string';
New : 'new';
Class : 'class';
Null : 'null';
True : 'true';
False : 'false';
This : 'this';
If : 'if';
Else : 'else';
For : 'for';
While : 'while';
Break : 'break';
Continue : 'continue';
Return : 'return';
Arrow : '->';

// White Char
Whitespace
    :   [ \t]+
        -> skip
    ;
Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;

// Comment
LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;

// Identifier
Identifier : [a-zA-Z] [a-zA-Z_0-9]*; // TODO : Range

// Constant
BoolLiteral : False | True;
IntLiteral : [1-9] [0-9]* | '0' ; // TODO: Range
StringLiteral : '"' (ESC|.)*? '"';
fragment ESC : '\\"' | '\\\\';