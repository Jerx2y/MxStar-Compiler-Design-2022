package AST.Expr;

import AST.ASTVisitor;
import Parser.MxParser;
import Util.Type;
import Util.position;

public class literalExprNode extends ExprNode {
    int intVal;
    boolean boolVal;
    String stringVal;
    boolean isNull = false;


    public literalExprNode(boolean boolval, position pos) {
        super(pos);
        this.boolVal = boolval;
        this.type = new Type(Type.BuiltinType.BOOL);
    }

    public literalExprNode(MxParser.LiteralContext ctx) {
        super(new position(ctx));
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
        if (ctx.Null() != null) {
            isNull = true;
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
