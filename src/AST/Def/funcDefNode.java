package AST.Def;

import AST.ASTVisitor;
import AST.Stmt.StmtNode;
import Util.Type;
import Util.position;

public class funcDefNode extends DefNode {
    public Type type;
    public String identifier;
    public varDefNode parameter;
    StmtNode stmts;

    public funcDefNode(Type type, String identifier, varDefNode parameter, StmtNode stmts, position pos) {
        super(pos);
        this.type = type;
        this.identifier = identifier;
        this.parameter = parameter;
        this.stmts = stmts;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
