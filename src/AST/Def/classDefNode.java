package AST.Def;

import AST.ASTVisitor;
import Util.Type;
import Util.position;

import java.util.ArrayList;

public class classDefNode extends DefNode {
    String identifier;
    ArrayList<varDefNode> varDefs;
    ArrayList<funcDefNode> funcDefs;

    classDefNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
