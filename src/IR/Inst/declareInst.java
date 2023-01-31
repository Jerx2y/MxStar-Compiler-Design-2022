package IR.Inst;

import IR.IRFunction;
import IR.IRType.IRType;
import IR.IRVisitor;

import java.util.ArrayList;

public class declareInst extends Inst {
    public String name;
    public IRType retType;
    public ArrayList<IRType> para;

    public declareInst(IRFunction f) {
        name = f.identifier;
        retType = f.retType;
        para = new ArrayList<>();
        f.paraEntity.forEach(x -> para.add(x.type));
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String ret = "declare " + retType + " @" + name + "(";
        for (int i = 0; i < para.size(); ++i){
            if (i > 0) ret += ", ";
            ret += para.get(i);
        }
        ret += ")";
        return ret;
    }
}
