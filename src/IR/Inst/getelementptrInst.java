package IR.Inst;

import IR.Entity.Entity;
import IR.IRType.IRType;
import IR.IRVisitor;

import java.util.ArrayList;

public class getelementptrInst extends Inst {
    public Entity rd, rs;
    public IRType type;
    public ArrayList<Entity> idx;

    public getelementptrInst(Entity rd, Entity rs, IRType type) {
        this.rd = rd;
        this.rs = rs;
        this.type = type;
        this.idx = new ArrayList<>();
    }

    public getelementptrInst(Entity rd, Entity rs, IRType type, ArrayList<Entity> idx) {
        this.rd = rd;
        this.rs = rs;
        this.type = type;
        this.idx = idx;
    }

    public getelementptrInst(Entity rd, Entity rs, IRType type, Entity var1) {
        this.rd = rd;
        this.rs = rs;
        this.type = type;
        this.idx = new ArrayList<>();
        this.idx.add(var1);
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String res = rd.getValue() + " = getelementptr inbounds " + type + ", " + rs;
        for (Entity entity : idx)
            res += ", " + entity;
        return res;
    }
}
