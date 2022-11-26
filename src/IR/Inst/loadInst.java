package IR.Inst;

import IR.Entity.Entity;
import IR.IRType.IRType;
import IR.IRVisitor;

public class loadInst extends Inst {
    Entity rd, rs;

    public loadInst(Entity rd, Entity rs) {
        this.rd = rd;
        this.rs = rs;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return rd.getValue() + " = load " + rd.type + ", " + rs;
    }
}
