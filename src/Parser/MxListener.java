// Generated from D:/STUDY/COM/MxStar-Compiler-Design-2022/src/Parser\Mx.g4 by ANTLR 4.10.1
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(MxParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(MxParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(MxParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(MxParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(MxParser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(MxParser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#memberExpression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpression(MxParser.MemberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#memberExpression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpression(MxParser.MemberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(MxParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(MxParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcExpression}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpression(MxParser.FuncExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcExpression}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpression(MxParser.FuncExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcallParameter}.
	 * @param ctx the parse tree
	 */
	void enterFuncallParameter(MxParser.FuncallParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcallParameter}.
	 * @param ctx the parse tree
	 */
	void exitFuncallParameter(MxParser.FuncallParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#selfExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelfExpression(MxParser.SelfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#selfExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelfExpression(MxParser.SelfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(MxParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(MxParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(MxParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(MxParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(MxParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(MxParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeOperator(MxParser.MultiplicativeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeOperator(MxParser.MultiplicativeOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(MxParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(MxParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveOperator(MxParser.AdditiveOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveOperator(MxParser.AdditiveOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(MxParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(MxParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void enterShiftOperator(MxParser.ShiftOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void exitShiftOperator(MxParser.ShiftOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(MxParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(MxParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(MxParser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(MxParser.RelationalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(MxParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(MxParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqualityOperator(MxParser.EqualityOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqualityOperator(MxParser.EqualityOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MxParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MxParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void enterXorExpression(MxParser.XorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#xorExpression}.
	 * @param ctx the parse tree
	 */
	void exitXorExpression(MxParser.XorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(MxParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#orExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(MxParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(MxParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(MxParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(MxParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(MxParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(MxParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(MxParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MxParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MxParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(MxParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(MxParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(MxParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(MxParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(MxParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(MxParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(MxParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(MxParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void enterForInitStatement(MxParser.ForInitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void exitForInitStatement(MxParser.ForInitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(MxParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(MxParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MxParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MxParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#declarationElement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationElement(MxParser.DeclarationElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#declarationElement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationElement(MxParser.DeclarationElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefinition(MxParser.FuncDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefinition(MxParser.FuncDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcParameter}.
	 * @param ctx the parse tree
	 */
	void enterFuncParameter(MxParser.FuncParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcParameter}.
	 * @param ctx the parse tree
	 */
	void exitFuncParameter(MxParser.FuncParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFuncParameterList(MxParser.FuncParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFuncParameterList(MxParser.FuncParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(MxParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(MxParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classConstruction}.
	 * @param ctx the parse tree
	 */
	void enterClassConstruction(MxParser.ClassConstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classConstruction}.
	 * @param ctx the parse tree
	 */
	void exitClassConstruction(MxParser.ClassConstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#typename}.
	 * @param ctx the parse tree
	 */
	void enterTypename(MxParser.TypenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#typename}.
	 * @param ctx the parse tree
	 */
	void exitTypename(MxParser.TypenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 */
	void enterBracket(MxParser.BracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 */
	void exitBracket(MxParser.BracketContext ctx);
}