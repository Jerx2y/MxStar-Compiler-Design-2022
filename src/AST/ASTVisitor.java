package AST;

import AST.Def.*;
import AST.Expr.*;
import AST.Stmt.*;

public interface ASTVisitor {
    void visit(RootNode it);

    void visit(DefNode it);
    void visit(classDefNode it);
    void visit(funcDefNode it);
    void visit(singleVarDefNode it);
    void visit(varDefNode it);

    void visit(ExprNode it);
    void visit(assignExprNode it);
    void visit(binaryExprNode it);
    void visit(cmpExprNode it);
    void visit(eqExprNode it);
    void visit(funcExprNode it);
    void visit(literalExprNode it);
    void visit(varExprNode it);

    void visit(StmtNode it);
    void visit(compoundStmtNode it);
    void visit(defStmtNode it);
    void visit(exprStmtNode it);
    void visit(flowStmtNode it);
    void visit(forStmtNode it);
    void visit(ifStmtNode it);
    void visit(returnStmtNode it);
    void visit(whileStmtNode it);
}
