package AST.Def;

import AST.ASTVisitor;
import AST.Stmt.StmtNode;
import Util.Type;
import Util.position;

import java.util.ArrayList;

public class classDefNode extends DefNode {
    public String identifier;
    public ArrayList<varDefNode> varDecs;
    public ArrayList<funcDefNode> funcDefs;
    public StmtNode constructor;

    public classDefNode(position pos, String identifier) {
        super(pos);
        this.identifier = identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
