package gen;// Generated from C:/Users/Kia/Desktop/Compiler_final/grammar/japy.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link japyParser}.
 */
public interface japyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link japyParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(japyParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(japyParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(japyParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(japyParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#entryClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEntryClassDeclaration(japyParser.EntryClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#entryClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEntryClassDeclaration(japyParser.EntryClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(japyParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(japyParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#access_modifier}.
	 * @param ctx the parse tree
	 */
	void enterAccess_modifier(japyParser.Access_modifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#access_modifier}.
	 * @param ctx the parse tree
	 */
	void exitAccess_modifier(japyParser.Access_modifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(japyParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(japyParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#closedStatement}.
	 * @param ctx the parse tree
	 */
	void enterClosedStatement(japyParser.ClosedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#closedStatement}.
	 * @param ctx the parse tree
	 */
	void exitClosedStatement(japyParser.ClosedStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#closedConditional}.
	 * @param ctx the parse tree
	 */
	void enterClosedConditional(japyParser.ClosedConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#closedConditional}.
	 * @param ctx the parse tree
	 */
	void exitClosedConditional(japyParser.ClosedConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#openConditional}.
	 * @param ctx the parse tree
	 */
	void enterOpenConditional(japyParser.OpenConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#openConditional}.
	 * @param ctx the parse tree
	 */
	void exitOpenConditional(japyParser.OpenConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#openStatement}.
	 * @param ctx the parse tree
	 */
	void enterOpenStatement(japyParser.OpenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#openStatement}.
	 * @param ctx the parse tree
	 */
	void exitOpenStatement(japyParser.OpenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(japyParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(japyParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementVarDef}.
	 * @param ctx the parse tree
	 */
	void enterStatementVarDef(japyParser.StatementVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementVarDef}.
	 * @param ctx the parse tree
	 */
	void exitStatementVarDef(japyParser.StatementVarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementBlock}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlock(japyParser.StatementBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementBlock}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlock(japyParser.StatementBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementContinue}.
	 * @param ctx the parse tree
	 */
	void enterStatementContinue(japyParser.StatementContinueContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementContinue}.
	 * @param ctx the parse tree
	 */
	void exitStatementContinue(japyParser.StatementContinueContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementBreak}.
	 * @param ctx the parse tree
	 */
	void enterStatementBreak(japyParser.StatementBreakContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementBreak}.
	 * @param ctx the parse tree
	 */
	void exitStatementBreak(japyParser.StatementBreakContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementReturn}.
	 * @param ctx the parse tree
	 */
	void enterStatementReturn(japyParser.StatementReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementReturn}.
	 * @param ctx the parse tree
	 */
	void exitStatementReturn(japyParser.StatementReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementClosedLoop}.
	 * @param ctx the parse tree
	 */
	void enterStatementClosedLoop(japyParser.StatementClosedLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementClosedLoop}.
	 * @param ctx the parse tree
	 */
	void exitStatementClosedLoop(japyParser.StatementClosedLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementOpenLoop}.
	 * @param ctx the parse tree
	 */
	void enterStatementOpenLoop(japyParser.StatementOpenLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementOpenLoop}.
	 * @param ctx the parse tree
	 */
	void exitStatementOpenLoop(japyParser.StatementOpenLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementWrite}.
	 * @param ctx the parse tree
	 */
	void enterStatementWrite(japyParser.StatementWriteContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementWrite}.
	 * @param ctx the parse tree
	 */
	void exitStatementWrite(japyParser.StatementWriteContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementAssignment}.
	 * @param ctx the parse tree
	 */
	void enterStatementAssignment(japyParser.StatementAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementAssignment}.
	 * @param ctx the parse tree
	 */
	void exitStatementAssignment(japyParser.StatementAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementInc}.
	 * @param ctx the parse tree
	 */
	void enterStatementInc(japyParser.StatementIncContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementInc}.
	 * @param ctx the parse tree
	 */
	void exitStatementInc(japyParser.StatementIncContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#statementDec}.
	 * @param ctx the parse tree
	 */
	void enterStatementDec(japyParser.StatementDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#statementDec}.
	 * @param ctx the parse tree
	 */
	void exitStatementDec(japyParser.StatementDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(japyParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(japyParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionOr}.
	 * @param ctx the parse tree
	 */
	void enterExpressionOr(japyParser.ExpressionOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionOr}.
	 * @param ctx the parse tree
	 */
	void exitExpressionOr(japyParser.ExpressionOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionOrTemp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionOrTemp(japyParser.ExpressionOrTempContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionOrTemp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionOrTemp(japyParser.ExpressionOrTempContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionAnd}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAnd(japyParser.ExpressionAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionAnd}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAnd(japyParser.ExpressionAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionAndTemp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAndTemp(japyParser.ExpressionAndTempContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionAndTemp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAndTemp(japyParser.ExpressionAndTempContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionEq}.
	 * @param ctx the parse tree
	 */
	void enterExpressionEq(japyParser.ExpressionEqContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionEq}.
	 * @param ctx the parse tree
	 */
	void exitExpressionEq(japyParser.ExpressionEqContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionEqTemp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionEqTemp(japyParser.ExpressionEqTempContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionEqTemp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionEqTemp(japyParser.ExpressionEqTempContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionCmp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionCmp(japyParser.ExpressionCmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionCmp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionCmp(japyParser.ExpressionCmpContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionCmpTemp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionCmpTemp(japyParser.ExpressionCmpTempContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionCmpTemp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionCmpTemp(japyParser.ExpressionCmpTempContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionAdd}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAdd(japyParser.ExpressionAddContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionAdd}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAdd(japyParser.ExpressionAddContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionAddTemp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAddTemp(japyParser.ExpressionAddTempContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionAddTemp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAddTemp(japyParser.ExpressionAddTempContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionMultMod}.
	 * @param ctx the parse tree
	 */
	void enterExpressionMultMod(japyParser.ExpressionMultModContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionMultMod}.
	 * @param ctx the parse tree
	 */
	void exitExpressionMultMod(japyParser.ExpressionMultModContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionMultModTemp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionMultModTemp(japyParser.ExpressionMultModTempContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionMultModTemp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionMultModTemp(japyParser.ExpressionMultModTempContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionUnary}.
	 * @param ctx the parse tree
	 */
	void enterExpressionUnary(japyParser.ExpressionUnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionUnary}.
	 * @param ctx the parse tree
	 */
	void exitExpressionUnary(japyParser.ExpressionUnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionMethods}.
	 * @param ctx the parse tree
	 */
	void enterExpressionMethods(japyParser.ExpressionMethodsContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionMethods}.
	 * @param ctx the parse tree
	 */
	void exitExpressionMethods(japyParser.ExpressionMethodsContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionMethodsTemp}.
	 * @param ctx the parse tree
	 */
	void enterExpressionMethodsTemp(japyParser.ExpressionMethodsTempContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionMethodsTemp}.
	 * @param ctx the parse tree
	 */
	void exitExpressionMethodsTemp(japyParser.ExpressionMethodsTempContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#expressionOther}.
	 * @param ctx the parse tree
	 */
	void enterExpressionOther(japyParser.ExpressionOtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#expressionOther}.
	 * @param ctx the parse tree
	 */
	void exitExpressionOther(japyParser.ExpressionOtherContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#japyType}.
	 * @param ctx the parse tree
	 */
	void enterJapyType(japyParser.JapyTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#japyType}.
	 * @param ctx the parse tree
	 */
	void exitJapyType(japyParser.JapyTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link japyParser#singleType}.
	 * @param ctx the parse tree
	 */
	void enterSingleType(japyParser.SingleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link japyParser#singleType}.
	 * @param ctx the parse tree
	 */
	void exitSingleType(japyParser.SingleTypeContext ctx);
}