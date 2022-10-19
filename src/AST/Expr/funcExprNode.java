package AST.Expr;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class funcExprNode extends ExprNode {
    public String caller;
    public ArrayList<ExprNode> exprs;

    public funcExprNode(String caller, position pos) {
        super(pos);
        this.caller = caller;
        exprs = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
