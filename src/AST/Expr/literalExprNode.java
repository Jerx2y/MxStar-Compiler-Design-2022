package AST.Expr;

import AST.ASTVisitor;
import Parser.MxParser;
import Util.Type.classType;
import Util.position;

public class literalExprNode extends ExprNode {
    public int intVal;
    public boolean boolVal;
    public String stringVal;


    public literalExprNode(boolean boolval, position pos) {
        super(pos);
        this.boolVal = boolval;
        this.type = new classType("bool");
    }

    public literalExprNode(MxParser.LiteralContext ctx) {
        super(new position(ctx));
        if (ctx.IntLiteral() != null) {
            type = new classType("int");
            intVal = Integer.parseInt(ctx.IntLiteral().toString());
        }
        if (ctx.StringLiteral() != null) {
            type = new classType("string");
            String str = ctx.StringLiteral().toString();
            stringVal = str.substring(1, str.length() - 1);
        }
        if (ctx.True() != null) {
            type = new classType("bool");
            boolVal = true;
        }
        if (ctx.False() != null) {
            type = new classType("bool");
            boolVal = false;
        }
        if (ctx.Null() != null)
            type = new classType("null");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
