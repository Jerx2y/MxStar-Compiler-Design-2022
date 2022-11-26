package IR.Inst;

import IR.Entity.Entity;
import IR.Entity.constant;
import IR.Entity.global;
import IR.IRType.IRType;
import IR.IRType.iIRType;
import IR.IRVisitor;

public class brInst extends Inst {

    Entity cond, d1, d2;

    public brInst(Entity d1) {
        cond = new constant(new iIRType(1), true);
        this.d1 = d1;
        d2 = null;
    }

    public brInst(Entity cond, Entity d1, Entity d2) {
        this.cond = cond;
        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (d2 == null)
            return "br " + d1;
        else return "br " + cond + ", " + d1 + ", " + d2;
    }
}
