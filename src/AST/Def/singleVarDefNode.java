package AST.Def;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.Type;
import Util.position;

public class singleVarDefNode extends DefNode {
    Type type;
    String identifier;
    ExprNode initExpr;

    public singleVarDefNode(Type type, String identifier, ExprNode initExpr, position pos) {
        super(pos);
        this.type = type;
        this.identifier = identifier;
        this.initExpr = initExpr;
    }

    public singleVarDefNode(Type type, String identifier, position pos) {
        super(pos);
        this.type = type;
        this.identifier = identifier;
        this.initExpr = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
