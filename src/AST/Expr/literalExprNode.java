package AST.Expr;

import AST.ASTVisitor;
import Parser.MxParser;
import Util.Type;
import Util.position;

public class literalExprNode extends ExprNode {
    public int intVal;
    public boolean boolVal;
    public String stringVal;
    public boolean isNull = false;


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
            type = new Type(Type.BuiltinType.NULL);
            isNull = true;
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
