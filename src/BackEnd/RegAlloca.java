package BackEnd;

import Asm.*;
import Asm.Ins.*;
import Asm.Operand.Operand;
import Asm.Operand.imm;
import Asm.Operand.pReg;
import Asm.Operand.vReg;

public class RegAlloca implements AsmVisitor {

    AsmModule topModule;
    AsmFunction curFunction;
    AsmBlock curBlock;

    pReg s0, t0, t1, t2, t3;

    Operand getPhyReg(Ins it, Operand vreg, Operand t, boolean iStore) {
        if (vreg instanceof vReg) {
            vReg v = (vReg) vreg;
            if (!curFunction.stkOffset.containsKey(v))
                curFunction.alloca(v);
            int offset = -curFunction.stkOffset.get(v);
            if (-2048 <= offset && offset < 2048) {
                if (!iStore) curBlock.insertBefore(new loadInsSet(t, s0, new imm(offset), v.size), it);
                else curBlock.insertAfter(new storeInsSet(t, s0, new imm(offset), v.size), it);
            } else {
                if (!iStore) {
                    curBlock.insertBefore(new liIns(t3, new imm(offset)), it);
                    curBlock.insertBefore(new binaryInsSet("add", t3, s0, t3), it);
                    curBlock.insertBefore(new loadInsSet(t, t3, new imm(0), v.size), it);
                } else {
                    curBlock.insertAfter(new storeInsSet(t, t3, new imm(0), v.size), it);
                    curBlock.insertAfter(new binaryInsSet("add", t3, s0, t3), it);
                    curBlock.insertAfter(new liIns(t3, new imm(offset)), it);
                }
            }
            return t;
        } else return vreg;
    }

    @Override
    public void visit(AsmModule it) {
        topModule = it;
        s0 = topModule.s.get(0);
        t0 = topModule.t.get(0);
        t1 = topModule.t.get(1);
        t2 = topModule.t.get(2);
        t3 = topModule.t.get(3);
        it.functions.forEach(f -> f.accept(this));
    }

    @Override
    public void visit(AsmFunction it) {
        curFunction = it;
        curFunction.entry.accept(this);
        curFunction.blocks.forEach(b -> b.accept(this));
    }

    @Override
    public void visit(AsmBlock it) {
        curBlock = it;
        for (Ins i = it.head.next; i != it.tail; i = i.next)
            i.accept(this);
    }

    @Override
    public void visit(AsmData it) { }

    @Override
    public void visit(binaryInsSet it) {
        it.rs1 = getPhyReg(it, it.rs1, t0, false);
        it.rs2 = getPhyReg(it, it.rs2, t1, false);
        it.rd = getPhyReg(it, it.rd, t2, true);
    }

    @Override
    public void visit(brInsSet it) {
        it.condition = getPhyReg(it, it.condition, t0, false);
    }

    @Override
    public void visit(callIns it) { }

    @Override
    public void visit(cmpInsSet it) {
        it.rs = getPhyReg(it, it.rs, t0, false);
        it.rd = getPhyReg(it, it.rd, t1, true);
    }

    @Override
    public void visit(Ins it) { }

    @Override
    public void visit(jIns it) { }

    @Override
    public void visit(laIns it) {
        it.rd = getPhyReg(it, it.rd, t0, true);
    }

    @Override
    public void visit(liIns it) {
        it.rd = getPhyReg(it, it.rd, t0, true);
    }

    @Override
    public void visit(loadInsSet it) {
        it.rs = getPhyReg(it, it.rs, t0, false);
        it.rd = getPhyReg(it, it.rd, t1, true);
    }

    @Override
    public void visit(mvIns it) {
        it.rs = getPhyReg(it, it.rs, t0, false);
        it.rd = getPhyReg(it, it.rd, t1, true);
    }

    @Override
    public void visit(retIns it) { }

    @Override
    public void visit(storeInsSet it) {
        it.val = getPhyReg(it, it.val, t0, false);
        it.addr = getPhyReg(it, it.addr, t1, false);
        it.rt = getPhyReg(it, it.rt, t2, false);
    }
}
