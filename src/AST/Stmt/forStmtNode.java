package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.position;

public class forStmtNode extends StmtNode {
    ExprNode condition, step;
    StmtNode initial;
    StmtNode stmt;

    public forStmtNode(StmtNode initial, ExprNode condition, ExprNode step, StmtNode stmt, position pos) {
        super(pos);
        this.initial = initial;
        this.condition = condition;
        this.step = step;
        this.stmt = stmt;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
