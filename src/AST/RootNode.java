package AST;

import AST.Def.*;
import Util.position;

import java.util.ArrayList;

public class RootNode extends ASTNode {
    public funcDefNode mainDef;
    public ArrayList<classDefNode> classDefs = new ArrayList<>();
    public ArrayList<funcDefNode> funcDefs = new ArrayList<>();
    public ArrayList<varDefNode> varDecs = new ArrayList<>();

    public RootNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
