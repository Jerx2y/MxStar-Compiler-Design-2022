package AST.Expr;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class funcExprNode extends ExprNode {
    public ExprNode caller;
    public ArrayList<ExprNode> exprs;

    public funcExprNode(ExprNode caller, position pos) {
        super(pos);
        this.caller = caller;
        exprs = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
