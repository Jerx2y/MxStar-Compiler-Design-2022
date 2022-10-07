// Generated from D:/STUDY/COM/MxStar-Compiler-Design-2022/src/Parser\Mxstar.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxstarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxstarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#mainFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainFunction(MxstarParser.MainFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(MxstarParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MxstarParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCall(MxstarParser.FunCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#arrayParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayParameter(MxstarParser.ArrayParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#newArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArray(MxstarParser.NewArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#newClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewClass(MxstarParser.NewClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#lambdaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpression(MxstarParser.LambdaExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#lambdaIntroducer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaIntroducer(MxstarParser.LambdaIntroducerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#selfExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfExpression(MxstarParser.SelfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(MxstarParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(MxstarParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(MxstarParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(MxstarParser.ShiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(MxstarParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(MxstarParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(MxstarParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusiveOrExpression(MxstarParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusiveOrExpression(MxstarParser.InclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(MxstarParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(MxstarParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(MxstarParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MxstarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MxstarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(MxstarParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(MxstarParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#statementSeq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementSeq(MxstarParser.StatementSeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(MxstarParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(MxstarParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(MxstarParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#forInitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitStatement(MxstarParser.ForInitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#declarationElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationElement(MxstarParser.DeclarationElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funcDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefinition(MxstarParser.FuncDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funcParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParameter(MxstarParser.FuncParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#funcParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParameterList(MxstarParser.FuncParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#classDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefinition(MxstarParser.ClassDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#classConstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassConstruction(MxstarParser.ClassConstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxstarParser#typename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypename(MxstarParser.TypenameContext ctx);
}