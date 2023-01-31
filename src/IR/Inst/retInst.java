package IR.Inst;

import IR.Entity.Entity;
import IR.IRVisitor;

public class retInst extends Inst {

    Entity ret;

    public retInst(Entity ret) {
        this.ret = ret;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "ret " + ret;
    }
}
