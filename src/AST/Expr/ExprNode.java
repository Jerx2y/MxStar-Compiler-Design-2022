package AST.Expr;

import AST.ASTNode;
import IR.Entity.Entity;
import Util.Type.Type;
import Util.position;

public abstract class ExprNode extends ASTNode {
    public Type type;

    public Entity entity;

    public ExprNode(position pos) {
        super(pos);
    }

    public boolean isAssignable() {
        return false;
    }
}