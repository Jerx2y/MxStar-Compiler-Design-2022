package AST.Stmt;

import AST.ASTVisitor;
import Util.position;

import java.util.ArrayList;

public class compoundStmtNode extends StmtNode {
    public ArrayList<StmtNode> stmts;

    public compoundStmtNode(position pos) {
        super(pos);
        this.stmts = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}