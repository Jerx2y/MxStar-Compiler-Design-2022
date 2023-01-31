package IR.Inst;

import IR.Entity.Entity;
import IR.IRBlock;
import IR.IRType.IRType;
import IR.IRVisitor;

import java.lang.annotation.Target;

public class bitcastInst extends Inst {

    Entity rd, rs;
    IRType targetType;

    public bitcastInst(Entity rd, Entity rs) {
        this.rd = rd;
        this.rs = rs;
        targetType = rd.type;
    }

    @Override
    public String toString() {
        return rd.getValue() + " = bitcast " + rs + " to " + targetType;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

}
