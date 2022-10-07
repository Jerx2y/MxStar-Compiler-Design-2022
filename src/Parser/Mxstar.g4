grammar Mxstar;

program: (varDeclaration | classDefinition | funcDefinition)* mainFunction (varDeclaration | classDefinition | funcDefinition)*  EOF;

mainFunction: Int 'main' LParen RParen compoundStatement;

// expressions

primaryExpression:
	literal
	| This
	| Null
	| Identifier ( funCall | arrayParameter )?
	| LParen expression RParen
	| lambdaExpression
	| newArray
	| newClass;

literal: IntLiteral | StringLiteral | False | True;

funCall: LParen ( expression (Comma expression)* )? RParen;

arrayParameter: (LBrack expression? RBrack)+;

newArray: New typename arrayParameter;

newClass: New Identifier (LParen RParen)?;

lambdaExpression:
	lambdaIntroducer funcParameter Arrow compoundStatement funCall;

lambdaIntroducer: LBrack And? RBrack;

selfExpression:
    primaryExpression | (primaryExpression ( AddAdd | SubSub | Dot selfExpression ) );

unaryExpression:
	selfExpression | ( (AddAdd | SubSub | Not | Tilde | Sub | Add) unaryExpression );

multiplicativeExpression:
	unaryExpression ( (Mul | Div | Mod) unaryExpression )*;

additiveExpression:
	multiplicativeExpression ( (Add | Sub) multiplicativeExpression )*;

shiftExpression:
	additiveExpression ( (LShift | Rshift) additiveExpression )*;

relationalExpression:
	shiftExpression ( (Less | Greater | LessEqual | GreaterEqual) shiftExpression )*;

equalityExpression:
	relationalExpression ( (EqualEqual | NotEqual) relationalExpression )*;

andExpression: equalityExpression (And equalityExpression)*;

exclusiveOrExpression: andExpression (Caret andExpression)*;

inclusiveOrExpression:
	exclusiveOrExpression (Or exclusiveOrExpression)*;

logicalAndExpression:
	inclusiveOrExpression (AndAnd inclusiveOrExpression)*;

logicalOrExpression:
	logicalAndExpression (OrOr logicalAndExpression)*;

assignmentExpression:
	logicalOrExpression (Assign logicalOrExpression)?;

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

compoundStatement: LBrace statementSeq? RBrace;

statementSeq: statement+;

selectionStatement: If LParen condition RParen statement (Else statement)?;

condition: expression;

iterationStatement:
	While LParen condition RParen statement
	| For LParen ( forInitStatement condition? Semi expression? ) RParen statement;

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

typename: (Int | Bool | String | Identifier) arrayParameter?;

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
Identifier : [a-zA-Z] [a-zA-Z_0-9]*;                          // TODO : Range

// Constant
IntLiteral : [1-9] [0-9]* | '0' ;                             // TODO: Range
StringLiteral : '"' (ESC|.)*? '"';                            // TODO: Range
fragment ESC : '\\"' | '\\\\';