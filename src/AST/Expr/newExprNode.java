package AST.Expr;

import AST.ASTVisitor;
import Parser.MxParser;
import Util.Type;
import Util.position;

public class newExprNode extends ExprNode {
    public newExprNode(MxParser.TypenameContext ctx, position pos) {
        super(pos);
        this.type = new Type(ctx);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
