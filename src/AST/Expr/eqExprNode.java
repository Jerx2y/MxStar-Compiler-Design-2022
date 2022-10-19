package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class eqExprNode extends ExprNode {
    public ExprNode lhs, rhs;
    public boolean iseq;

    public eqExprNode(ExprNode lhs, ExprNode rhs, boolean iseq, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.iseq = iseq;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
