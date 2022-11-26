package IR.Inst;

import IR.Entity.Entity;
import IR.IRVisitor;
import IR.IRType.IRType;

public class allocaInst extends Inst {

    public IRType type;

    public Entity rd;

    public allocaInst(Entity rd, IRType type) {
        this.type = type;
        this.rd = rd;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return rd.getValue() + " = alloca " + type;
    }
}