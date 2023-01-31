package IR.Inst;

import IR.Entity.Entity;
import IR.IRVisitor;

import java.util.ArrayList;

public class phiInst extends Inst {
    Entity rd;
    ArrayList<Entity> val, block;

    public phiInst(Entity rd) {
        this.rd = rd;
        val = new ArrayList<>();
        block = new ArrayList<>();
    }

    public void add(Entity var, Entity label) {
        val.add(var);
        block.add(label);
    }

    @Override
    public String toString() {
        String res = rd.getValue() + " = phi " + rd.type + " ";
        for (int i = 0; i < val.size(); ++i) {
            if (i > 0)res += ", ";
            res += "[ " + val.get(i).getValue() + ", %" + block.get(i).getValue() + " ]";
        }
        return res;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
