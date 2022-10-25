package FrontEnd;

import AST.*;
import AST.Def.*;
import AST.Expr.*;
import AST.Stmt.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Type.varType;
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
        funcDefNode node = new funcDefNode(ctx.typename(), ctx.Identifier().getText(), (varDefNode) visit(ctx.funcParameter()), new position(ctx));
        compoundStmtNode stmt = (compoundStmtNode) visit(ctx.compoundStatement());
        node.stmts = stmt.stmts;
        return node;
    }


    @Override public ASTNode visitFuncParameter(MxParser.FuncParameterContext ctx) {
        if (ctx.funcParameterList().isEmpty()) return null;
        varDefNode node = new varDefNode(new position(ctx));
        for (int i = 0, size = ctx.funcParameterList().typename().size(); i < size; ++i) {
            node.singleVarDefs.add(new singleVarDefNode(ctx.funcParameterList().typename(i), ctx.funcParameterList().Identifier(i).getText(), new position(ctx.funcParameterList())));
        }
        return node;
    }

    @Override public ASTNode visitVarDeclaration(MxParser.VarDeclarationContext ctx) {
        varDefNode node = new varDefNode(new position(ctx));
        ctx.declarationElement().forEach(de -> node.singleVarDefs.add(new singleVarDefNode(ctx.typename(), de.Identifier().getText(), (ExprNode) visit(de.expression()), new position(de))));
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
            return new returnStmtNode(ctx.expression() != null ? (ExprNode) visit(ctx.expression()) : null, new position(ctx));
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
        ExprNode node = (ExprNode) visit(ctx.logicalAndExpression(0));
        for (int i = 1, sz = ctx.logicalAndExpression().size(); i < sz; ++i)
            node = new binaryExprNode(node, (ExprNode) visit(ctx.logicalAndExpression(i)), binaryExprNode.binaryOpType.DOUBLE_OR, new position(ctx));
        return node;
    }

    @Override public ASTNode visitLogicalAndExpression(MxParser.LogicalAndExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.orExpression(0));
        for (int i = 1, sz = ctx.orExpression().size(); i < sz; ++i)
            node = new binaryExprNode(node, (ExprNode) visit(ctx.orExpression(i)), binaryExprNode.binaryOpType.DOUBLE_AND, new position(ctx));
        return node;
    }

    @Override public ASTNode visitOrExpression(MxParser.OrExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.xorExpression(0));
        for (int i = 1, sz = ctx.xorExpression().size(); i < sz; ++i)
            node = new binaryExprNode(node, (ExprNode) visit(ctx.xorExpression(i)), binaryExprNode.binaryOpType.OR, new position(ctx));
        return node;
    }

    @Override public ASTNode visitXorExpression(MxParser.XorExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.andExpression(0));
        for (int i = 1, sz = ctx.andExpression().size(); i < sz; ++i)
            node = new binaryExprNode(node, (ExprNode) visit(ctx.andExpression(i)), binaryExprNode.binaryOpType.XOR, new position(ctx));
        return node;
    }

    @Override public ASTNode visitAndExpression(MxParser.AndExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.equalityExpression(0));
        for (int i = 1, sz = ctx.equalityExpression().size(); i < sz; ++i)
            node = new binaryExprNode(node, (ExprNode) visit(ctx.equalityExpression(i)), binaryExprNode.binaryOpType.AND, new position(ctx));
        return node;
    }

    @Override public ASTNode visitEqualityExpression(MxParser.EqualityExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.relationalExpression(0));
        for (int i = 1, sz = ctx.relationalExpression().size(); i < sz; ++i)
            node = new eqExprNode(node, (ExprNode) visit(ctx.relationalExpression(i)), ctx.equalityOperator(i - 1).EqualEqual() != null, new position(ctx));
        return node;
    }

    @Override public ASTNode visitRelationalExpression(MxParser.RelationalExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.shiftExpression(0));
        for (int i = 1, sz = ctx.shiftExpression().size(); i < sz; ++i) {
            cmpExprNode.cmpOpType optype;
            if (ctx.relationalOperator(i - 1).Less() != null)
                optype = cmpExprNode.cmpOpType.LE;
            else if (ctx.relationalOperator(i - 1).LessEqual() != null)
                optype = cmpExprNode.cmpOpType.LEQ;
            else if (ctx.relationalOperator(i - 1).Greater() != null)
                optype = cmpExprNode.cmpOpType.GR;
            else optype = cmpExprNode.cmpOpType.GEQ;
            node = new cmpExprNode(node, (ExprNode) visit(ctx.shiftExpression(i)), optype, new position(ctx));
        }
        return node;
    }

    @Override public ASTNode visitShiftExpression(MxParser.ShiftExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.additiveExpression(0));
        for (int i = 1, sz = ctx.additiveExpression().size(); i < sz; ++i)
            node = new binaryExprNode(node, (ExprNode) visit(ctx.additiveExpression(i)), ctx.shiftOperator(i - 1).LShift() != null ? binaryExprNode.binaryOpType.DOUBLE_L : binaryExprNode.binaryOpType.DOUBLE_R, new position(ctx));
        return node;
    }

    @Override public ASTNode visitAdditiveExpression(MxParser.AdditiveExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.multiplicativeExpression(0));
        for (int i = 1, sz = ctx.multiplicativeExpression().size(); i < sz; ++i)
            node = new binaryExprNode(node, (ExprNode) visit(ctx.multiplicativeExpression(i)), ctx.additiveOperator(i - 1).Add() != null ? binaryExprNode.binaryOpType.ADD : binaryExprNode.binaryOpType.SUB, new position(ctx));
        return node;
    }

    @Override public ASTNode visitMultiplicativeExpression(MxParser.MultiplicativeExpressionContext ctx) {
        ExprNode node = (ExprNode) visit(ctx.newExpression(0));
        for (int i = 1, sz = ctx.newExpression().size(); i < sz; ++i) {
            binaryExprNode.binaryOpType type;
            if (ctx.multiplicativeOperator(i - 1).Mul() != null)
                type = binaryExprNode.binaryOpType.MUL;
            else if (ctx.multiplicativeOperator(i - 1).Div() != null)
                type = binaryExprNode.binaryOpType.DIV;
            else type = binaryExprNode.binaryOpType.MOD;
            node = new binaryExprNode(node, (ExprNode) visit(ctx.newExpression(i)), type, new position(ctx));
        }
        return node;
    }

    @Override public ASTNode visitNewExpression(MxParser.NewExpressionContext ctx) {
        if (ctx.unaryExpression() != null)
            return visit(ctx.unaryExpression());
        newExprNode node = new newExprNode(ctx.typename(), new position(ctx));
        for (MxParser.BracketContext bracket : ctx.typename().bracket()) {
            if (bracket.expression() != null)
                node.dimExpr.add((ExprNode) visit(bracket.expression()));
            else node.dimExpr.add(null);
        }
        return node;
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
            return new unaryExprNode((ExprNode) visit(ctx.primaryExpression()), unaryExprNode.unaryOpType.R_DOUBLE_ADD, new position(ctx));
        else if (ctx.SubSub() != null)
            return new unaryExprNode((ExprNode) visit(ctx.primaryExpression()), unaryExprNode.unaryOpType.R_DOUBLE_SUB, new position(ctx));
        else return visit(ctx.primaryExpression());
    }

    @Override public ASTNode visitLambdaExpression(MxParser.LambdaExpressionContext ctx) {
        varDefNode parameter = (varDefNode) visit(ctx.funcParameter());
        StmtNode stmts = (StmtNode) visit(ctx.compoundStatement());
        lambdaExprNode node = new lambdaExprNode(ctx.And() != null, parameter, stmts, new position(ctx));
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
            return new varExprNode("this", new position(ctx));
        } else if (ctx.Identifier() != null)
            return new varExprNode(ctx.Identifier().getText(), new position(ctx));
        else if (ctx.LParen() != null)
            return visit(ctx.expression());
        else if (ctx.Dot() != null)
            return new memberExprNode((ExprNode) visit(ctx.primaryExpression()), ctx.Identifier().getText(), new position(ctx));
        else if (ctx.LBrack() != null)
            return new arrayExprNode((ExprNode) visit(ctx.primaryExpression()), (ExprNode) visit(ctx.expression()), new position(ctx));
        else if (ctx.funcallParameter() != null) {
            funcExprNode node = new funcExprNode((ExprNode) visit(ctx.primaryExpression()), new position(ctx));
            ctx.funcallParameter().expression().forEach(ex -> node.exprs.add((ExprNode) visit(ex)));
            return node;
        } else if (ctx.lambdaExpression() != null)
            return visit(ctx.lambdaExpression());
        return null;
    }

}
