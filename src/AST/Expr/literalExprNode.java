package AST.Expr;

import AST.ASTVisitor;
import Parser.MxParser;
import Util.Type;
import Util.position;

public class literalExprNode extends ExprNode {
    int intVal;
    boolean boolVal;
    String stringVal;


    public literalExprNode(boolean boolval, position pos) {
        super(pos);
        this.boolVal = boolval;
        ;
    }

    public literalExprNode(MxParser.LiteralContext ctx, position pos) {
        super(pos);
        if (ctx.IntLiteral() != null) {
            type = new Type(Type.BuiltinType.INT);
            intVal = Integer.parseInt(ctx.IntLiteral().toString());
        }
        if (ctx.StringLiteral() != null) {
            type = new Type(Type.BuiltinType.STRING);
            String str = ctx.StringLiteral().toString();
            stringVal = str.substring(1, str.length() - 1);
        }
        if (ctx.True() != null) {
            type = new Type(Type.BuiltinType.BOOL);
            boolVal = true;
        }
        if (ctx.False() != null) {
            type = new Type(Type.BuiltinType.BOOL);
            boolVal = false;
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
