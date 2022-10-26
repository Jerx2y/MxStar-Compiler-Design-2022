package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class varExprNode extends ExprNode {
    public String identifier;

    public varExprNode(String identifier, position pos) {
        super(pos);
        this.identifier = identifier;
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
