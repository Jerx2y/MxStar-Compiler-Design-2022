package AST.Expr;

import AST.ASTVisitor;
import Util.Type;
import Util.position;

public class unaryExprNode extends ExprNode {
    public ExprNode expr;
    public enum unaryOpType {
        DOUBLE_ADD, DOUBLE_SUB, NOT, TILDE, ADD, SUB,
        R_DOUBLE_ADD, R_DOUBLE_SUB
    }
    public unaryOpType opCode;

    public unaryExprNode(ExprNode expr, unaryOpType opCode, position pos) {
        super(pos);
        this.expr = expr;
        this.opCode = opCode;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
