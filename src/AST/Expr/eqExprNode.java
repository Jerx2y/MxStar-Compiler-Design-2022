package AST.Expr;

import AST.ASTVisitor;
import Util.position;

public class eqExprNode extends ExprNode {

    public enum eqOpType {
        EQ, NE
    }

    public ExprNode lhs, rhs;
    public eqOpType opt;

    public eqExprNode(ExprNode lhs, ExprNode rhs, boolean isEQ, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        opt = isEQ ? eqOpType.EQ : eqOpType.NE;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
