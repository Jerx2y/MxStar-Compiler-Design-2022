// Generated from D:/STUDY/COM/MxStar-Compiler-Design-2022/src/Parser\Mxstar.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxstarParser}.
 */
public interface MxstarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxstarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#mainFunction}.
	 * @param ctx the parse tree
	 */
	void enterMainFunction(MxstarParser.MainFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#mainFunction}.
	 * @param ctx the parse tree
	 */
	void exitMainFunction(MxstarParser.MainFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(MxstarParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(MxstarParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxstarParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxstarParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funCall}.
	 * @param ctx the parse tree
	 */
	void enterFunCall(MxstarParser.FunCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funCall}.
	 * @param ctx the parse tree
	 */
	void exitFunCall(MxstarParser.FunCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#arrayParameter}.
	 * @param ctx the parse tree
	 */
	void enterArrayParameter(MxstarParser.ArrayParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#arrayParameter}.
	 * @param ctx the parse tree
	 */
	void exitArrayParameter(MxstarParser.ArrayParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#newArray}.
	 * @param ctx the parse tree
	 */
	void enterNewArray(MxstarParser.NewArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#newArray}.
	 * @param ctx the parse tree
	 */
	void exitNewArray(MxstarParser.NewArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#newClass}.
	 * @param ctx the parse tree
	 */
	void enterNewClass(MxstarParser.NewClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#newClass}.
	 * @param ctx the parse tree
	 */
	void exitNewClass(MxstarParser.NewClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(MxstarParser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(MxstarParser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#lambdaIntroducer}.
	 * @param ctx the parse tree
	 */
	void enterLambdaIntroducer(MxstarParser.LambdaIntroducerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#lambdaIntroducer}.
	 * @param ctx the parse tree
	 */
	void exitLambdaIntroducer(MxstarParser.LambdaIntroducerContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#selfExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelfExpression(MxstarParser.SelfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#selfExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelfExpression(MxstarParser.SelfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(MxstarParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(MxstarParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(MxstarParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(MxstarParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(MxstarParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(MxstarParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(MxstarParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(MxstarParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(MxstarParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(MxstarParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(MxstarParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(MxstarParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MxstarParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MxstarParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(MxstarParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(MxstarParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(MxstarParser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(MxstarParser.InclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(MxstarParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(MxstarParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(MxstarParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(MxstarParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(MxstarParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(MxstarParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxstarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxstarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxstarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxstarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MxstarParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MxstarParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(MxstarParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(MxstarParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#statementSeq}.
	 * @param ctx the parse tree
	 */
	void enterStatementSeq(MxstarParser.StatementSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#statementSeq}.
	 * @param ctx the parse tree
	 */
	void exitStatementSeq(MxstarParser.StatementSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(MxstarParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(MxstarParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(MxstarParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(MxstarParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(MxstarParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(MxstarParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void enterForInitStatement(MxstarParser.ForInitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void exitForInitStatement(MxstarParser.ForInitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(MxstarParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MxstarParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#declarationElement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationElement(MxstarParser.DeclarationElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#declarationElement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationElement(MxstarParser.DeclarationElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefinition(MxstarParser.FuncDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefinition(MxstarParser.FuncDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcParameter}.
	 * @param ctx the parse tree
	 */
	void enterFuncParameter(MxstarParser.FuncParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcParameter}.
	 * @param ctx the parse tree
	 */
	void exitFuncParameter(MxstarParser.FuncParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#funcParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFuncParameterList(MxstarParser.FuncParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#funcParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFuncParameterList(MxstarParser.FuncParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(MxstarParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(MxstarParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#classConstruction}.
	 * @param ctx the parse tree
	 */
	void enterClassConstruction(MxstarParser.ClassConstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#classConstruction}.
	 * @param ctx the parse tree
	 */
	void exitClassConstruction(MxstarParser.ClassConstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxstarParser#typename}.
	 * @param ctx the parse tree
	 */
	void enterTypename(MxstarParser.TypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxstarParser#typename}.
	 * @param ctx the parse tree
	 */
	void exitTypename(MxstarParser.TypenameContext ctx);
}