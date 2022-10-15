package AST.Expr;

import AST.*;
import Util.position;

public class assignExprNode extends ExprNode {
    public ExprNode lhs, rhs;

    public assignExprNode(position pos, ExprNode lhs, ExprNode rhs) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}