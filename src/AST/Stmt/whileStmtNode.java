package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.position;

public class whileStmtNode extends StmtNode {
    public ExprNode condition;
    public StmtNode stmt;

    public whileStmtNode(ExprNode condition, StmtNode stmt, position pos) {
        super(pos);
        this.condition = condition;
        this.stmt = stmt;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
