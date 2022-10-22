package AST.Def;

import AST.ASTVisitor;
import AST.Stmt.StmtNode;
import Util.Type.varType;
import Util.position;

import java.util.ArrayList;

public class funcDefNode extends DefNode {
    public varType varType;
    public String identifier;
    public varDefNode parameter;

    public ArrayList<StmtNode> stmts;

    public funcDefNode(varType varType, String identifier, varDefNode parameter, position pos) {
        super(pos);
        this.varType = varType;
        this.identifier = identifier;
        this.parameter = parameter;
        stmts = new ArrayList<StmtNode>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
