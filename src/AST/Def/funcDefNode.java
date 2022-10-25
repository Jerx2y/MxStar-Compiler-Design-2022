package AST.Def;

import AST.ASTVisitor;
import AST.Stmt.StmtNode;
import Parser.MxParser;
import Util.Type.varType;
import Util.position;

import java.util.ArrayList;

public class funcDefNode extends DefNode {
    public MxParser.TypenameContext retype;
    public String identifier;
    public varDefNode parameter;

    public ArrayList<StmtNode> stmts;

    public funcDefNode(MxParser.TypenameContext ctx, String identifier, varDefNode parameter, position pos) {
        super(pos);
        this.retype = ctx;
        this.identifier = identifier;
        this.parameter = parameter;
        stmts = new ArrayList<StmtNode>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
