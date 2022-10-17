package AST.Def;

import AST.ASTVisitor;
import AST.Stmt.StmtNode;
import Util.Type;
import Util.position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class funcDefNode extends DefNode {
    Type type;
    String identifier;
    varDefNode parameter;
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
