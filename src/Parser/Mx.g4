grammar Mx;

program: def* EOF;

def: (varDeclaration | classDefinition | funcDefinition);

// expressions

primaryExpression:
	literal
	| This
	| Identifier
	| LParen expression RParen
	| newExpression
	| funcExpression
	| lambdaExpression;

literal: IntLiteral | StringLiteral | False | True | Null;

funcExpression: Identifier funcallParameter;

funcallParameter: LParen ( expression (Comma expression)* )? RParen;

newExpression: New typename (LParen RParen)?;

lambdaExpression:
	LBrack And? RBrack funcParameter? Arrow compoundStatement funcallParameter;

arrayExpression:
    primaryExpression (LBrack expression RBrack)*;

memberExpression:
    arrayExpression (Dot arrayExpression)*;

selfExpression:
    memberExpression (AddAdd | SubSub)?;

unaryExpression:
	selfExpression | ( (AddAdd | SubSub | Not | Tilde | Sub | Add) unaryExpression );

multiplicativeExpression:
	unaryExpression (multiplicativeOperator unaryExpression)*;

multiplicativeOperator: Mul | Div | Mod;

additiveExpression:
	multiplicativeExpression (additiveOperator multiplicativeExpression)*;

additiveOperator: Add | Sub;

shiftExpression:
	additiveExpression (shiftOperator additiveExpression)*;

shiftOperator: LShift | Rshift;

relationalExpression:
	shiftExpression (relationalOperator shiftExpression)*;

relationalOperator: Less | Greater | LessEqual | GreaterEqual;

equalityExpression:
	relationalExpression (equalityOperator relationalExpression)*;

equalityOperator: EqualEqual | NotEqual;

andExpression: equalityExpression (And equalityExpression)*;

xorExpression: andExpression (Caret andExpression)*;

orExpression: xorExpression (Or xorExpression)*;

logicalAndExpression: orExpression (AndAnd orExpression)*;

logicalOrExpression: logicalAndExpression (OrOr logicalAndExpression)*;

assignmentExpression: logicalOrExpression (Assign logicalOrExpression)?;

expression: assignmentExpression;

// statements

statement:
    varDeclaration
    | expressionStatement
    | compoundStatement
    | selectionStatement
    | iterationStatement
    | jumpStatement;

expressionStatement: expression? Semi;

compoundStatement: LBrace statement* RBrace;

selectionStatement: If LParen condition RParen statement (Else statement)?;

condition: expression;

iterationStatement:
	(While LParen condition RParen statement)
	| (For LParen ( forInitStatement condition? Semi expression? ) RParen statement);

forInitStatement: expressionStatement | varDeclaration;

jumpStatement: ( Break | Continue | Return expression? ) Semi;

// declarations

varDeclaration: typename declarationElement (Comma declarationElement)* Semi;

declarationElement: Identifier (Assign expression)?;

funcDefinition: (typename | Void) Identifier funcParameter compoundStatement;

funcParameter: LParen funcParameterList? RParen;

funcParameterList: typename Identifier (Comma typename Identifier)*;

classDefinition : Class Identifier LBrace (varDeclaration | classConstruction | funcDefinition)* RBrace Semi;

classConstruction: Identifier LParen RParen compoundStatement;

typename: (Int | Bool | String | Identifier) (LBrack expression? RBrack)*;

// Lexer

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
EqualEqual : '==';
NotEqual : '!=';

AndAnd : '&&';
OrOr : '||';
Not : '!';

LShift : '<<';
Rshift : '>>';
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
Whitespace : [ \t]+ -> skip ;
Newline : ( '\r' '\n'? | '\n' ) -> skip;

// Comment
LineComment : '//' ~[\r\n]* -> skip;
BlockComment : '/*' .*? '*/' -> skip;

// Identifier
Identifier : [a-zA-Z] [a-zA-Z_0-9]*;

// Constant
IntLiteral : [1-9] [0-9]* | '0' ;                             // TODO: Range
StringLiteral : '"' (ESC|.)*? '"';                            // TODO: Range
fragment ESC : '\\"' | '\\\\' | '\\n';