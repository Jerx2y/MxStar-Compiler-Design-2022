package IR;

import IR.Entity.Entity;
import IR.Inst.Inst;
import IR.Inst.allocaInst;
import IR.Inst.brInst;
import IR.Inst.retInst;

import java.util.ArrayList;

public class IRBlock {
    Entity name;
    ArrayList<Inst> allocaList, instList;
    Inst terminator = null;
    IRFunction inFunc;

    IRBlock(Entity name, IRFunction inFunc) {
        this.name = name;
        this.inFunc = inFunc;
    }

    public void addInst(Inst i) {
        if (terminator != null) return ;
        if (i instanceof brInst || i instanceof retInst)
            terminator = i;
        else if (i instanceof allocaInst)
            allocaList.add(i);
        else instList.add(i);
    }
}