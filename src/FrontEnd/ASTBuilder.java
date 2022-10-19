package FrontEnd;

import AST.*;
import AST.Def.*;
import AST.Expr.*;
import AST.Stmt.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Type;
import Util.error.syntaxError;
import Util.Scope.*;
import Util.position;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {

    private globalScope gScope;
    public ASTBuilder(globalScope gScope) {
        this.gScope = gScope;
    }

    // Type;

    @Override public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx));

        ctx.def().forEach(def -> root.defs.add((DefNode) visit(def)));
        
        return root;
    }

    // Definition

    @Override public ASTNode visitClassDefinition(MxParser.ClassDefinitionContext ctx) {
        classDefNode node = new classDefNode(new position(ctx), ctx.Identifier().getText());
        ctx.funcDefinition().forEach(fd -> node.funcDefs.add((funcDefNode) visit(fd)));
        ctx.varDeclaration().forEach(vd -> node.varDecs.add((varDefNode) visit(vd)));
        if (ctx.classConstruction().size() > 1)
            throw new syntaxError("Construction of class > 1", new position(ctx));
        ctx.classConstruction().forEach(cc -> node.constructor = (StmtNode) visit(cc));
        return node;
    }

    @Override public ASTNode visitFuncDefinition(MxParser.FuncDefinitionContext ctx) {
        Type functype;
        if (ctx.Void() != null)
            functype = new Type(Type.BuiltinType.VOID);
        else functype = new Type(ctx.typename());
        return new funcDefNode(functype, ctx.Identifier().getText(), (varDefNode) visit(ctx.funcParameter()), (StmtNode) visit(ctx.compoundStatement()), new position(ctx));
    }


    @Override public ASTNode visitFuncParameter(MxParser.FuncParameterContext ctx) {
        if (ctx.funcParameterList().isEmpty()) return null;
        varDefNode node = new varDefNode(new position(ctx));
        for (int i = 0, size = ctx.funcParameterList().typename().size(); i < size; ++i) {
            node.singleVarDefs.add(new singleVarDefNode(new Type(ctx.funcParameterList().typename(i)), ctx.funcParameterList().Identifier(i).getText(), new position(ctx.funcParameterList())));
        }
        return node;
    }

    @Override public ASTNode visitVarDeclaration(MxParser.VarDeclarationContext ctx) {
        varDefNode node = new varDefNode(new position(ctx));
        ctx.declarationElement().forEach(de -> node.singleVarDefs.add(new singleVarDefNode(new Type(ctx.typename()), de.Identifier().getText(), (ExprNode) visit(de.expression()), new position(de))));
        return node;
    }

    // Statement

    @Override public ASTNode visitStatement(MxParser.StatementContext ctx) {
        if (!ctx.varDeclaration().isEmpty())
            return new defStmtNode((DefNode) visit(ctx.varDeclaration()), new position(ctx));
        else if (!ctx.expressionStatement().isEmpty())
            return visit(ctx.expressionStatement());
        else if (!ctx.compoundStatement().isEmpty())
            return visit(ctx.compoundStatement());
        else if (!ctx.selectionStatement().isEmpty())
            return visit(ctx.selectionStatement());
        else if (!ctx.iterationStatement().isEmpty())
            return visit(ctx.iterationStatement());
        else if (!ctx.jumpStatement().isEmpty())
            return visit(ctx.jumpStatement());
        else return null;
    }

    @Override public ASTNode visitCompoundStatement(MxParser.CompoundStatementContext ctx) {
        if (ctx.statement().isEmpty()) return null;
        compoundStmtNode node = new compoundStmtNode(new position(ctx));
        ctx.statement().forEach(stmt -> node.stmts.add((StmtNode) visit(stmt)));
        return node;
    }

    @Override public ASTNode visitIterationStatement(MxParser.IterationStatementContext ctx) {
        if (ctx.For() != null) {
            StmtNode initial = (StmtNode) visit(ctx.forInitStatement());
            ExprNode step = (ExprNode) visit(ctx.expression());
            ExprNode condition;
            if (ctx.condition().isEmpty())
                condition = new literalExprNode(true, new position(ctx.condition()));
            else condition = (ExprNode) visit(ctx.condition());
            return new forStmtNode(initial, condition, step, (StmtNode) visit(ctx.statement()), new position(ctx));
        } else return new whileStmtNode((ExprNode) visit(ctx.condition()), (StmtNode) visit(ctx.statement()), new position(ctx));
    }

    @Override public ASTNode visitForInitStatement(MxParser.ForInitStatementContext ctx) {
        if (!ctx.expressionStatement().isEmpty())
            return visit(ctx.expressionStatement());
        else return visit(ctx.varDeclaration());
    }

    @Override public ASTNode visitSelectionStatement(MxParser.SelectionStatementContext ctx) {
        ExprNode condition = (ExprNode) visit(ctx.condition());
        StmtNode thenstmt = (StmtNode) visit(ctx.statement(0)), elsestmt = null;
        if (ctx.Else() != null)
            elsestmt = (StmtNode) visit(ctx.statement(1));
        return new ifStmtNode(condition, thenstmt, elsestmt, new position(ctx));
    }

    @Override public ASTNode visitJumpStatement(MxParser.JumpStatementContext ctx) {
        if (ctx.Return() != null)
            return new returnStmtNode((ExprNode) visit(ctx.expression()), new position(ctx));
        else return new flowStmtNode(ctx.Break() != null, new position(ctx));
    }

    @Override public ASTNode visitExpressionStatement(MxParser.ExpressionStatementContext ctx) {
        if (!ctx.expression().isEmpty())
            return new exprStmtNode(null, new position(ctx));
        else return new exprStmtNode((ExprNode) visit(ctx.expression()), new position(ctx));
    }

    // Expression

    @Override public ASTNode visitExpression(MxParser.ExpressionContext ctx) {
        return visit(ctx.assignmentExpression());
    }

    @Override public ASTNode visitAssignmentExpression(MxParser.AssignmentExpressionContext ctx) {
        if (ctx.Assign() == null)
            return visit(ctx.logicalOrExpression(0));
        return new assignExprNode(new position(ctx), (ExprNode) visit(ctx.logicalOrExpression(0)), (ExprNode) visit(ctx.logicalOrExpression(1)));
    }

    @Override public ASTNode visitLogicalOrExpression(MxParser.LogicalOrExpressionContext ctx) {
        if (ctx.OrOr() == null)
            return visit(ctx.logicalAndExpression());
        return new binaryExprNode((ExprNode) visit(ctx.logicalAndExpression()), (ExprNode) visit(ctx.logicalOrExpression()), binaryExprNode.binaryOpType.DOUBLE_OR, new position(ctx));
    }

    @Override public ASTNode visitLogicalAndExpression(MxParser.LogicalAndExpressionContext ctx) {
        if (ctx.AndAnd() == null)
            return visit(ctx.orExpression());
        return new binaryExprNode((ExprNode) visit(ctx.orExpression()), (ExprNode) visit(ctx.logicalAndExpression()), binaryExprNode.binaryOpType.DOUBLE_AND, new position(ctx));
    }

    @Override public ASTNode visitOrExpression(MxParser.OrExpressionContext ctx) {
        if (ctx.Or() == null)
            return visit(ctx.xorExpression());
        return new binaryExprNode((ExprNode) visit(ctx.xorExpression()), (ExprNode) visit(ctx.orExpression()), binaryExprNode.binaryOpType.OR, new position(ctx));
    }

    @Override public ASTNode visitXorExpression(MxParser.XorExpressionContext ctx) {
        if (ctx.Caret() == null)
            return visit(ctx.andExpression());
        return new binaryExprNode((ExprNode) visit(ctx.andExpression()), (ExprNode) visit(ctx.xorExpression()), binaryExprNode.binaryOpType.XOR, new position(ctx));
    }

    @Override public ASTNode visitAndExpression(MxParser.AndExpressionContext ctx) {
        if (ctx.And() == null)
            return visit(ctx.equalityExpression());
        return new binaryExprNode((ExprNode) visit(ctx.equalityExpression()), (ExprNode) visit(ctx.andExpression()), binaryExprNode.binaryOpType.AND, new position(ctx));
    }

    @Override public ASTNode visitEqualityExpression(MxParser.EqualityExpressionContext ctx) {
        if (ctx.EqualEqual() == null && ctx.NotEqual() == null)
            return visit(ctx.relationalExpression());
        return new eqExprNode((ExprNode) visit(ctx.relationalExpression()), (ExprNode) visit(ctx.equalityExpression()), ctx.EqualEqual() != null, new position(ctx));
    }

    @Override public ASTNode visitRelationalExpression(MxParser.RelationalExpressionContext ctx) {
        if (ctx.Less() != null)
            return new cmpExprNode((ExprNode) visit(ctx.shiftExpression()), (ExprNode) visit(ctx.relationalExpression()), cmpExprNode.cmpOpType.LE, new position(ctx));
        else if (ctx.LessEqual() != null)
            return new cmpExprNode((ExprNode) visit(ctx.shiftExpression()), (ExprNode) visit(ctx.relationalExpression()), cmpExprNode.cmpOpType.LEQ, new position(ctx));
        else if (ctx.Greater() != null)
            return new cmpExprNode((ExprNode) visit(ctx.shiftExpression()), (ExprNode) visit(ctx.relationalExpression()), cmpExprNode.cmpOpType.GR, new position(ctx));
        else if (ctx.GreaterEqual() != null)
            return new cmpExprNode((ExprNode) visit(ctx.shiftExpression()), (ExprNode) visit(ctx.relationalExpression()), cmpExprNode.cmpOpType.GEQ, new position(ctx));
        else return visit(ctx.shiftExpression());
    }

    @Override public ASTNode visitShiftExpression(MxParser.ShiftExpressionContext ctx) {
        if (ctx.LShift() != null)
            return new binaryExprNode((ExprNode) visit(ctx.additiveExpression()), (ExprNode) visit(ctx.shiftExpression()), binaryExprNode.binaryOpType.DOUBLE_L, new position(ctx));
        else if (ctx.Rshift() != null)
            return new binaryExprNode((ExprNode) visit(ctx.additiveExpression()), (ExprNode) visit(ctx.shiftExpression()), binaryExprNode.binaryOpType.DOUBLE_R, new position(ctx));
        else return visit(ctx.additiveExpression());
    }

    @Override public ASTNode visitAdditiveExpression(MxParser.AdditiveExpressionContext ctx) {
        if (ctx.Add() != null)
            return new binaryExprNode((ExprNode) visit(ctx.multiplicativeExpression()), (ExprNode) visit(ctx.additiveExpression()), binaryExprNode.binaryOpType.ADD, new position(ctx));
        else if (ctx.Sub() != null)
            return new binaryExprNode((ExprNode) visit(ctx.multiplicativeExpression()), (ExprNode) visit(ctx.additiveExpression()), binaryExprNode.binaryOpType.SUB, new position(ctx));
        else return visit(ctx.multiplicativeExpression());
    }

    @Override public ASTNode visitMultiplicativeExpression(MxParser.MultiplicativeExpressionContext ctx) {
        if (ctx.Mul() != null)
            return new binaryExprNode((ExprNode) visit(ctx.unaryExpression()), (ExprNode) visit(ctx.multiplicativeExpression()), binaryExprNode.binaryOpType.MUL, new position(ctx));
        else if (ctx.Div() != null)
            return new binaryExprNode((ExprNode) visit(ctx.unaryExpression()), (ExprNode) visit(ctx.multiplicativeExpression()), binaryExprNode.binaryOpType.DIV, new position(ctx));
        else if (ctx.Mod() != null)
            return new binaryExprNode((ExprNode) visit(ctx.unaryExpression()), (ExprNode) visit(ctx.multiplicativeExpression()), binaryExprNode.binaryOpType.MOD, new position(ctx));
        else return visit(ctx.multiplicativeExpression());
    }

    @Override public ASTNode visitUnaryExpression(MxParser.UnaryExpressionContext ctx) {
        if (ctx.AddAdd() != null)
            return new unaryExprNode((ExprNode) visit(ctx.unaryExpression()), unaryExprNode.unaryOpType.DOUBLE_ADD, new position(ctx));
        else if (ctx.SubSub() != null)
            return new unaryExprNode((ExprNode) visit(ctx.unaryExpression()), unaryExprNode.unaryOpType.DOUBLE_SUB, new position(ctx));
        else if (ctx.Not() != null)
            return new unaryExprNode((ExprNode) visit(ctx.unaryExpression()), unaryExprNode.unaryOpType.NOT, new position(ctx));
        else if (ctx.Tilde() != null)
            return new unaryExprNode((ExprNode) visit(ctx.unaryExpression()), unaryExprNode.unaryOpType.TILDE, new position(ctx));
        else if (ctx.Add() != null)
            return new unaryExprNode((ExprNode) visit(ctx.unaryExpression()), unaryExprNode.unaryOpType.ADD, new position(ctx));
        else if (ctx.Sub() != null)
            return new unaryExprNode((ExprNode) visit(ctx.unaryExpression()), unaryExprNode.unaryOpType.SUB, new position(ctx));
        else return visit(ctx.selfExpression());
    }

    @Override public ASTNode visitSelfExpression(MxParser.SelfExpressionContext ctx) {
        if (ctx.AddAdd() != null)
            return new unaryExprNode((ExprNode) visit(ctx.memberExpression()), unaryExprNode.unaryOpType.R_DOUBLE_ADD, new position(ctx));
        else if (ctx.SubSub() != null)
            return new unaryExprNode((ExprNode) visit(ctx.memberExpression()), unaryExprNode.unaryOpType.R_DOUBLE_SUB, new position(ctx));
        else return visit(ctx.memberExpression());
    }

    @Override public ASTNode visitMemberExpression(MxParser.MemberExpressionContext ctx) {
        if (ctx.Identifier() != null)
            return new memberExprNode((ExprNode) visit(ctx.primaryExpression()), new varExprNode(ctx.Identifier().getText(), new position(ctx.Identifier())), new position(ctx));
        else if (ctx.funcExpression() != null)
            return new memberExprNode((ExprNode) visit(ctx.primaryExpression()), (ExprNode) visit(ctx.funcExpression()), new position(ctx));
        else if (ctx.arrayExpression() != null)
            return new memberExprNode((ExprNode) visit(ctx.primaryExpression()), (ExprNode) visit(ctx.arrayExpression()), new position(ctx));
        else return visit(ctx.primaryExpression());
    }

    @Override public ASTNode visitLambdaExpression(MxParser.LambdaExpressionContext ctx) {
        varDefNode parameter = (varDefNode) visit(ctx.funcParameter());
        StmtNode stmts = (StmtNode) visit(ctx.compoundStatement());
        lambdaExprNode node = new lambdaExprNode(ctx.And() != null, parameter, stmts, new position(ctx));
        ctx.funcallParameter().expression().forEach(ex -> node.exprs.add((ExprNode) visit(ex)));
        return node;
    }

    @Override public ASTNode visitNewExpression(MxParser.NewExpressionContext ctx) {
        return new newExprNode(ctx.typename(), new position(ctx));
    }

    @Override public ASTNode visitArrayExpression(MxParser.ArrayExpressionContext ctx) {
        ExprNode caller;
        if (ctx.newExpression() != null)
            caller = (ExprNode) visit(ctx.newExpression());
        else caller = new varExprNode(ctx.Identifier().getText(), new position(ctx.Identifier()));
        arrayExprNode node = new arrayExprNode(caller, (ExprNode) visit(ctx.expression(0)), new position(ctx.expression(0)));
        for (int i = 1, sz = ctx.expression().size(); i < sz; ++i)
            node = new arrayExprNode(node, (ExprNode) visit(ctx.expression(i)), new position(ctx.expression(i)));
        return node;
    }

    @Override public ASTNode visitFuncExpression(MxParser.FuncExpressionContext ctx) {
        funcExprNode node = new funcExprNode(ctx.Identifier().getText(), new position(ctx));
        ctx.funcallParameter().expression().forEach(ex -> node.exprs.add((ExprNode) visit(ex)));
        return node;
    }

    @Override public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        return new literalExprNode(ctx);
    }

    @Override public ASTNode visitPrimaryExpression(MxParser.PrimaryExpressionContext ctx) {
        if (ctx.literal() != null)
            return visit(ctx.literal());
        else if (ctx.This() != null) {
            // TODO literal?
        } else if (ctx.Identifier() != null)
            return new varExprNode(ctx.Identifier().getText(), new position(ctx));
        else if (ctx.expression() != null)
            return visit(ctx.expression());
        else if (ctx.funcExpression() != null)
            return visit(ctx.funcExpression());
        else if (ctx.arrayExpression() != null)
            return visit(ctx.arrayExpression());
        else if (ctx.lambdaExpression() != null)
            return visit(ctx.lambdaExpression());
        else if (ctx.newExpression() != null)
            return visit(ctx.newExpression());
        return null;
    }

}
