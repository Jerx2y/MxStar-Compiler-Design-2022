package AST.Def;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Parser.MxParser;
import Util.Type.varType;
import Util.position;

public class singleVarDefNode extends DefNode {
    public MxParser.TypenameContext typename;
    public String identifier;
    public ExprNode initExpr;

    public singleVarDefNode(MxParser.TypenameContext ctx, String identifier, ExprNode initExpr, position pos) {
        super(pos);
        this.typename = ctx;
        this.identifier = identifier;
        this.initExpr = initExpr;
    }

    public singleVarDefNode(MxParser.TypenameContext ctx, String identifier, position pos) {
        super(pos);
        this.typename = ctx;
        this.identifier = identifier;
        this.initExpr = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
