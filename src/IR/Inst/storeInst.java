package IR.Inst;

import IR.Entity.Entity;
import IR.IRType.IRType;
import IR.IRVisitor;

public class storeInst extends Inst {
    Entity rd, rs;

    public storeInst(Entity rd, Entity rs) {
        this.rd = rd;
        this.rs = rs;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "store " + rd + ", " + rs;
    }
}
