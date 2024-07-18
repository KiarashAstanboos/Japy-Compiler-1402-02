package gen;// Generated from C:/Users/Kia/Desktop/Compiler_final/grammar/japy.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link japyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface japyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link japyParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(japyParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(japyParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#entryClassDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntryClassDeclaration(japyParser.EntryClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(japyParser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#access_modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess_modifier(japyParser.Access_modifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(japyParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#closedStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClosedStatement(japyParser.ClosedStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#closedConditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClosedConditional(japyParser.ClosedConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#openConditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenConditional(japyParser.OpenConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#openStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenStatement(japyParser.OpenStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(japyParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementVarDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementVarDef(japyParser.StatementVarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBlock(japyParser.StatementBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementContinue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementContinue(japyParser.StatementContinueContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementBreak}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBreak(japyParser.StatementBreakContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementReturn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementReturn(japyParser.StatementReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementClosedLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementClosedLoop(japyParser.StatementClosedLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementOpenLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementOpenLoop(japyParser.StatementOpenLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementWrite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementWrite(japyParser.StatementWriteContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementAssignment(japyParser.StatementAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementInc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementInc(japyParser.StatementIncContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#statementDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementDec(japyParser.StatementDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(japyParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOr(japyParser.ExpressionOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionOrTemp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOrTemp(japyParser.ExpressionOrTempContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAnd(japyParser.ExpressionAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionAndTemp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAndTemp(japyParser.ExpressionAndTempContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionEq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionEq(japyParser.ExpressionEqContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionEqTemp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionEqTemp(japyParser.ExpressionEqTempContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionCmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionCmp(japyParser.ExpressionCmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionCmpTemp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionCmpTemp(japyParser.ExpressionCmpTempContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionAdd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAdd(japyParser.ExpressionAddContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionAddTemp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAddTemp(japyParser.ExpressionAddTempContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionMultMod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionMultMod(japyParser.ExpressionMultModContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionMultModTemp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionMultModTemp(japyParser.ExpressionMultModTempContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionUnary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionUnary(japyParser.ExpressionUnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionMethods}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionMethods(japyParser.ExpressionMethodsContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionMethodsTemp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionMethodsTemp(japyParser.ExpressionMethodsTempContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#expressionOther}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOther(japyParser.ExpressionOtherContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#japyType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJapyType(japyParser.JapyTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link japyParser#singleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleType(japyParser.SingleTypeContext ctx);
}