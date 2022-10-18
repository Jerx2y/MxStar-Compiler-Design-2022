grammar Mx;

program: (varDeclaration | classDefinition | funcDefinition)* EOF;

// expressions

primaryExpression:
	literal
	| This
	| Identifier
	| LParen expression RParen
	| funcExpression
	| arrayExpression
	| lambdaExpression
	| newExpression;

literal: IntLiteral | StringLiteral | False | True | Null;

funcExpression: Identifier funcallParameter;

funcallParameter: LParen ( expression (Comma expression)* )? RParen;

arrayExpression: (newExpression | Identifier) (LBrack expression RBrack)+;

newExpression: New typename (LParen RParen)?;

lambdaExpression:
	LBrack And? RBrack funcParameter? Arrow compoundStatement funcallParameter;

memberExpression:
    primaryExpression ( Dot (Identifier | funcExpression | arrayExpression) )?;

selfExpression:
    memberExpression (AddAdd | SubSub)?;

unaryExpression:
	selfExpression | ( (AddAdd | SubSub | Not | Tilde | Sub | Add) unaryExpression );

multiplicativeExpression:
	unaryExpression ( (Mul | Div | Mod) multiplicativeExpression)?;

additiveExpression:
	multiplicativeExpression ( (Add | Sub) additiveExpression )?;

shiftExpression:
	additiveExpression ( (LShift | Rshift) shiftExpression )?;

relationalExpression:
	shiftExpression ( (Less | Greater | LessEqual | GreaterEqual) relationalExpression )?;

equalityExpression:
	relationalExpression ( (EqualEqual | NotEqual) equalityExpression )?;

andExpression: equalityExpression (And andExpression)?;

xorExpression: andExpression (Caret xorExpression)?;

orExpression: xorExpression (Or orExpression)?;

logicalAndExpression: orExpression (AndAnd logicalAndExpression)?;

logicalOrExpression: logicalAndExpression (OrOr logicalOrExpression)?;

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