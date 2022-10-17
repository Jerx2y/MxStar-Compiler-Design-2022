package AST.Def;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import Util.Type;
import Util.position;

import java.util.ArrayList;

public class varDefNode extends DefNode {
    public ArrayList<singleVarDefNode> singleVarDefs;

    public varDefNode(position pos) {
        super(pos);
        singleVarDefs = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
