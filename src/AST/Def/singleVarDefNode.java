package AST.Def;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.Type.varType;
import Util.position;

public class singleVarDefNode extends DefNode {
    public varType varType;
    public String identifier;
    public ExprNode initExpr;

    public singleVarDefNode(varType varType, String identifier, ExprNode initExpr, position pos) {
        super(pos);
        this.varType = varType;
        this.identifier = identifier;
        this.initExpr = initExpr;
    }

    public singleVarDefNode(varType varType, String identifier, position pos) {
        super(pos);
        this.varType = varType;
        this.identifier = identifier;
        this.initExpr = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
