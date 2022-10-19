package FrontEnd;

import AST.*;
import AST.Stmt.*;
import AST.Expr.*;
import AST.Def.*;
import Util.Scope.*;
import Util.Type;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    private globalScope gScope;
    private Type currentStruct = null;

    public SemanticChecker(globalScope gScope) {
        currentScope = this.gScope = gScope;
    }

    @Override
    public void visit(RootNode it) {
        ;
    }

    public void visit(DefNode it) {}
    public void visit(classDefNode it) {}
    public void visit(funcDefNode it) {}
    public void visit(singleVarDefNode it) {}
    public void visit(varDefNode it) {}

    public void visit(ExprNode it) {}
    public void visit(arrayExprNode it) {}
    public void visit(assignExprNode it) {}
    public void visit(binaryExprNode it) {}
    public void visit(cmpExprNode it) {}
    public void visit(eqExprNode it) {}
    public void visit(funcExprNode it) {}
    public void visit(lambdaExprNode it) {}
    public void visit(literalExprNode it) {}
    public void visit(memberExprNode it) {}
    public void visit(newExprNode it) {}
    public void visit(unaryExprNode it) {}
    public void visit(varExprNode it) {}

    public void visit(StmtNode it) {}
    public void visit(compoundStmtNode it) {}
    public void visit(defStmtNode it) {}
    public void visit(exprStmtNode it) {}
    public void visit(flowStmtNode it) {}
    public void visit(forStmtNode it) {}
    public void visit(ifStmtNode it) {}
    public void visit(returnStmtNode it) {}
    public void visit(whileStmtNode it) {}
}
