package AST.Expr;

import AST.ASTNode;
import Util.Type.varType;
import Util.position;

public abstract class ExprNode extends ASTNode {
    public varType type;

    public ExprNode(position pos) {
        super(pos);
    }

    public boolean isAssignable() {
        return false;
    }
}