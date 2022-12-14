package AST.Stmt;

import AST.Expr.ExprNode;
import AST.ASTVisitor;
import Util.position;

public class exprStmtNode extends StmtNode{
    public ExprNode expr;

    public exprStmtNode(ExprNode expr, position pos) {
        super(pos);
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
