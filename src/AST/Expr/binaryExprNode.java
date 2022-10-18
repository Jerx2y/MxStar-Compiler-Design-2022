package AST.Expr;

import AST.ASTVisitor;
import Util.Type;
import Util.position;

public class binaryExprNode extends ExprNode {
    public ExprNode lhs, rhs;
    public enum binaryOpType {
        ADD, SUB, MUL, DIV, MOD,
        DOUBLE_AND, DOUBLE_OR,
        DOUBLE_L, DOUBLE_R, AND, OR, XOR
    }
    public binaryOpType opCode;

    public binaryExprNode(ExprNode lhs, ExprNode rhs, binaryOpType opCode, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.opCode = opCode;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
