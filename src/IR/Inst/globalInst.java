package IR.Inst;

import IR.Entity.Entity;
import IR.IRVisitor;

public class globalInst extends Inst {

    public enum defineType {
        GLOBAL, CONSTANT
    }

    Entity rd, init;
    defineType dType;

    public globalInst(Entity rd, Entity init, defineType dType) {
        this.rd = rd;
        this.init = init;
        this.dType = dType;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return rd.getValue() + " = " + dType.name().toLowerCase() + " " + init;
    }
}
