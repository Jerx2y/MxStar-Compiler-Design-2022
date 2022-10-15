package AST.Stmt;

import AST.Def.DefNode;
import AST.ASTVisitor;
import Util.position;

public class defStmtNode extends StmtNode{
    public DefNode def;

    public defStmtNode(DefNode def, position pos) {
        super(pos);
        this.def = def;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
