package IR.Inst;

import IR.Entity.Entity;
import IR.IRType.IRType;
import IR.IRType.voidIRType;
import IR.IRVisitor;

import java.util.ArrayList;

public class callInst extends Inst {

    Entity rd;
    IRType retType;
    String name;
    public ArrayList<Entity> para;

    public callInst(Entity rd, IRType retType, String name, ArrayList<Entity> para) {
        this.rd = rd;
        this.retType = retType;
        this.name = name;
        this.para = para;
    }

    public callInst(String name, ArrayList<Entity> para) {
        this.rd = null;
        this.retType = new voidIRType();
        this.name = name;
        this.para = para;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String res = "";
        if (rd != null)
            res += rd.getValue() + " = ";
        res += "call " + retType.toString() + " @" + name + "(";
        if (para.size() > 0)
            res += para.get(0);
        for (int i = 1, sz = para.size(); i < sz; ++i)
            res += ", " + para.get(i);
        res += ")";
        return res;
    }
}
