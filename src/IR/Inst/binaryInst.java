package IR.Inst;

import IR.IRVisitor;

public class binaryInst extends Inst {

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return null;
    }
}
