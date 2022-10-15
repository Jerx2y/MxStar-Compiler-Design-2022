package AST.Expr;

import AST.ASTVisitor;
import Util.Type;
import Util.position;

public class eqExprNode extends ExprNode {
    public ExprNode lhs, rhs;
    public enum cmpOpType { EQ, NEQ }
    public cmpOpType opCode;

    public eqExprNode(ExprNode lhs, ExprNode rhs, cmpOpType opCode, Type boolType, position pos) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
        this.opCode = opCode;
        this.type = Type.BuiltinType.BOOL;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
