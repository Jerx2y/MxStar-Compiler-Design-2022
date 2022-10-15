package AST.Def;

import AST.ASTVisitor;
import Util.Type;
import Util.position;

public class funcDefNode extends DefNode {
    Type type;
    String identifier;

    varDefNode(Type type, String identifier, position pos) {
        super(pos);
        this.type = type;
        this.identifier = identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
