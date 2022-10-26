package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class arrayExprNode extends ExprNode {
    public ExprNode caller, index;

    public arrayExprNode(ExprNode caller, ExprNode index, position pos) {
        super(pos);
        this.caller = caller;
        this.index = index;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isAssignable() {
        return true;
    }
}
