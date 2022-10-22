package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class memberExprNode extends ExprNode {
    public ExprNode caller;
    public String member;

    public memberExprNode(ExprNode caller, String member, position pos) {
        super(pos);
        this.caller = caller;
        this.member = member;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
