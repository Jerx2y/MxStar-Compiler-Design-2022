package FrontEnd;

import AST.*;
import AST.Def.*;
import AST.Expr.ExprNode;
import AST.Expr.literalExprNode;
import AST.Stmt.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Type;
import Util.error.syntaxError;
import Util.scope.*;
import Util.position;
import org.antlr.v4.runtime.ParserRuleContext;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {

    private globalScope gScope;
    public ASTBuilder(globalScope gScope) {
        this.gScope = gScope;
    }

    // Type;

    @Override public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        RootNode root = new RootNode(new position(ctx));

        ctx.classDefinition().forEach(cd -> root.classDefs.add((classDefNode) visit(cd)));
        ctx.funcDefinition().forEach(fd -> root.funcDefs.add((funcDefNode) visit(fd)));
        ctx.varDeclaration().forEach(vd -> root.varDecs.add((varDefNode) visit(vd)));

        return root;
    }

    // Definition

    @Override public ASTNode visitClassDefinition(MxParser.ClassDefinitionContext ctx) {
        classDefNode node = new classDefNode(new position(ctx), ctx.Identifier().toString());
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
        return new funcDefNode(functype, ctx.Identifier().toString(), (varDefNode) visit(ctx.funcParameter()), (StmtNode) visit(ctx.compoundStatement()), new position(ctx));
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
            return visit(ctx.varDeclaration());
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
    }
}
