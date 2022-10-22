package AST.Expr;

import AST.ASTVisitor;
import Parser.MxParser;
import Util.Type.varType;
import Util.position;

public class literalExprNode extends ExprNode {
    public int intVal;
    public boolean boolVal;
    public String stringVal;
    public boolean isNull = false;


    public literalExprNode(boolean boolval, position pos) {
        super(pos);
        this.boolVal = boolval;
        this.type = new varType(varType.BuiltinType.BOOL);
    }

    public literalExprNode(MxParser.LiteralContext ctx) {
        super(new position(ctx));
        if (ctx.IntLiteral() != null) {
            type = new varType(varType.BuiltinType.INT);
            intVal = Integer.parseInt(ctx.IntLiteral().toString());
        }
        if (ctx.StringLiteral() != null) {
            type = new varType(varType.BuiltinType.STRING);
            String str = ctx.StringLiteral().toString();
            stringVal = str.substring(1, str.length() - 1);
        }
        if (ctx.True() != null) {
            type = new varType(varType.BuiltinType.BOOL);
            boolVal = true;
        }
        if (ctx.False() != null) {
            type = new varType(varType.BuiltinType.BOOL);
            boolVal = false;
        }
        if (ctx.Null() != null) {
            type = new varType(varType.BuiltinType.NULL);
            isNull = true;
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
