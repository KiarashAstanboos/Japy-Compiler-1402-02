grammar japy;

program:
 (c1 = classDeclaration)*
 mainclass = entryClassDeclaration
 (c3 = classDeclaration)* EOF
	;

classDeclaration:
	access_modifier? 'class' className= ID ('inherits' classParent= ID)? 'begin'
	(fieldDeclaration | methodDeclaration)*
    'end'
	;

entryClassDeclaration:
	('MAIN') classDeclaration;


fieldDeclaration:
	access_modifier? 'field' fieldType = japyType fieldName = ID (',' ii = ID)*
	;

access_modifier:
	'public'| 'private' | 'protected'
	;

methodDeclaration:
    methodAccessModifier = access_modifier? 'func' methodName = ID
    '(' ( param1 = ID ':' typeP1 = japyType (',' param2 = ID ':' typeP2 = japyType)*)?')'
    'returns' t = japyType
    'begin' (s = statement)* 'end'
    ;

closedStatement :
    s1 = statementBlock
    | conditionalStat=closedConditional
    | s3 = statementClosedLoop
    | s4 = statementWrite
    | s5 = statementAssignment
    | s6 = statementReturn
    | s7 = statementVarDef
    | s8 = statementContinue
    | s9 = statementBreak
    | incStat=statementInc
    | decStat=statementDec
;

closedConditional :
    'if' '(' ifExp=expression ')' ifStat=closedStatement
    ('elif' '(' elifExp=expression ')' elifStat=closedStatement)*
    'else' elseStmt=closedStatement
;

openConditional:
    'if' '(' ifExp=expression ')' ifStat=statement
    | (
    'if' '(' ifExp=expression ')' secondIfStat=closedStatement
    ('elif' '(' elifExp=expression ')' elifStat=closedStatement )*
    'elif' '(' lastElifExp=expression ')' lastElifStmt=statement
    )
    | (
    'if' '(' ifExp=expression ')' thirdIfStat=closedStatement
    ('elif' '(' elifExp=expression ')' elifStat=closedStatement)*
    'else' elseStmt=openStatement
    )
;

openStatement :
    s1 = statementOpenLoop
    | conditionalStat=openConditional
    ;


statement:
	s1 = closedStatement
	| s2 = openStatement
	;

statementVarDef:
	    'var' i1 = ID '=' e1 = expression (',' i2 = ID '=' e2 = expression)*
	    ;

statementBlock:
	'begin' (s = statement )* 'end';

statementContinue:
	myContinue='continue';

statementBreak:
	myBreak='break'
	;

statementReturn:
	myReturn='return'
		e = expression
	;

statementClosedLoop:
	'while' '(' e = expression ')' s = closedStatement;

statementOpenLoop:
    'while' '(' e = expression ')' s = openStatement
;

statementWrite:
	printLine='sout' '(' e = expression ')'
	;

statementAssignment:
	left=expression assignOp='=' right = expression
;

statementInc:
    lvalExpr=expression incOp='++'
;


statementDec:
    lvalExpr=expression decOp='--'
;
expression:
	e = expressionOr ;

expressionOr:
	a = expressionAnd ot = expressionOrTemp
	;

expressionOrTemp:
	orOp='||' a = expressionAnd
	ot = expressionOrTemp
	|;

expressionAnd:
	e = expressionEq at = expressionAndTemp;

expressionAndTemp:
	andOp='&&' e = expressionEq
	at = expressionAndTemp
	|;

expressionEq:
	c = expressionCmp et = expressionEqTemp
	;

expressionEqTemp :
	( eqOp='==' | neqOp='<>')
	c=expressionCmp
	et=expressionEqTemp
	|;

expressionCmp:
	a = expressionAdd ct = expressionCmpTemp;

expressionCmpTemp:
	( ltOp='<' | gtOp='>')
	a = expressionAdd
	ct = expressionCmpTemp
	|;

expressionAdd:
	m = expressionMultMod at = expressionAddTemp
	;

expressionAddTemp:
	(addOp='+' | subOp='-')
	m = expressionMultMod
	at = expressionAddTemp
	|;

expressionMultMod:
	u = expressionUnary mt = expressionMultModTemp
    ;

expressionMultModTemp :
	(mulOp='*' | divOp='/' | modOp='%')
	u = expressionUnary
	mt = expressionMultModTemp
	|;

expressionUnary:
	(notOp='!' | negOp='-')
	u = expressionUnary
	| m = expressionMethods ;

expressionMethods:
	o = expressionOther mt = expressionMethodsTemp
;

expressionMethodsTemp:
	(dotOp='.' i = ID '(' ( e1 = expression  (',' e2 = expression )*)? ')'
	| dotOp='.' ii = ID
    | leftBrace='[' e3 = expression ']'
    ) mt = expressionMethodsTemp
	|;

expressionOther:
	n = CONST_NUM

	| s = CONST_STR

	| newModifier='new' st = singleType leftBrace='[' size = expression ']'

	| newModifier='new' i = ID '(' ')'

	| selfModifier='this'

	| trueModifier='true'

	| falseModifier='false'

	| i1 = ID

	| i2 = ID leftBrace='[' e = expression ']'

	| leftPara='(' ee = expression ')'

	| i3 = ID
    '(' ( e3 = expression (',' e4 = expression)*)? ')'
    ;

japyType:
	st = singleType ('[' ']')?
	;

singleType: (
		'double'
		| 'bool'
		| 'string'
		| i = ID
	);

CONST_NUM: [1-9][0-9]* | [0] | [1-9][0-9]* '.'  [0-9]+;

CONST_STR: '"' ~('\r' | '\n' | '"')* '"';

NL: '\r'? '\n' -> skip;

ID: [a-zA-Z_][a-zA-Z0-9_]*;

COMMENT: '##' (~[\r\n])* -> skip;

MULTILINE_COMMENT: '###' (.)*? '###' -> skip;

WS: [ \t] -> skip;