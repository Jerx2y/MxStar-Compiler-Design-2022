package IR;

import IR.Entity.Entity;
import IR.Entity.label;
import IR.Inst.Inst;
import IR.Inst.allocaInst;
import IR.Inst.brInst;
import IR.Inst.retInst;

import java.util.ArrayList;

public class IRBlock {
    public Entity name;
    public ArrayList<Inst> instList;
    public Inst terminator;
    IRFunction inFunc;
    public boolean br = false;

    public IRBlock(IRFunction inFunc) {
        this.name = new label(inFunc.getRegId());
        this.inFunc = inFunc;
        this.terminator = null;
        instList = new ArrayList<>();
    }

    public boolean addInst(Inst i) {
        if (terminator != null || br) return false;
        if (i instanceof brInst || i instanceof retInst)
            terminator = i;
        else instList.add(i);
        return true;
    }

    public void addTerminatorInst(Inst i) {
        assert (br);
        assert (terminator == null);
        terminator = i;
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}