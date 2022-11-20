package IR.Inst;

import IR.IRVisitor;
import IR.IRType.IRType;

public class alloca extends Inst {

    public IRType type;



    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return null;
    }
}