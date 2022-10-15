package AST.Stmt;

import AST.ASTVisitor;
import Util.position;

public class flowStmtNode extends StmtNode {
    boolean isbreak; // break or continue

    public flowStmtNode(boolean isbreak, position pos) {
        super(pos);
        this.isbreak = isbreak;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
