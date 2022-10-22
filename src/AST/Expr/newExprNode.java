package AST.Expr;

import AST.ASTVisitor;
import Parser.MxParser;
import Util.Type.varType;
import Util.position;

import java.util.ArrayList;

public class newExprNode extends ExprNode {
    public ArrayList<ExprNode> dimExpr;

    public newExprNode(MxParser.TypenameContext ctx, position pos) {
        super(pos);
        this.type = new varType(ctx);
        dimExpr = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
