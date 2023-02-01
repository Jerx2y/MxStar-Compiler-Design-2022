package BackEnd;

import Asm.AsmBlock;
import Asm.AsmFunction;
import Asm.AsmModule;
import Asm.Ins.Ins;
import Asm.Ins.binaryInsSet;
import Asm.Ins.loadInsSet;
import Asm.Ins.mvIns;
import Asm.Operand.imm;
import Asm.Operand.pReg;
import Asm.Operand.reg;
import Asm.Operand.vReg;
import IR.*;
import IR.Entity.Entity;
import IR.Inst.*;

import java.util.HashMap;

public class InsSelector implements IRVisitor {

    AsmModule topModule;
    AsmFunction curFunction;
    AsmBlock curBlock;
    HashMap<Entity, reg> regMap;
    pReg zero, ra, sp, s0, a0;

    reg getReg(Entity x) {
        return regMap.get(x);
    }

    @Override
    public void visitIRModule(IRModule irmodule) {
        irmodule.globals.forEach(inst -> inst.accept(this));
        irmodule.classes.forEach(this::visitIRClass);
        irmodule.functions.forEach(this::visitIRFunction);
    }

    @Override
    public void visitIRClass(IRClass irclass) {
    }

    @Override
    public void visitIRFunction(IRFunction irfunction) {
        curFunction = topModule;

        // map para. Entity to Reg
        for (int i = 0; i < Integer.min(8, irfunction.paraEntity.size()); ++i) {
            Entity para = irfunction.paraEntity.get(i);
            vReg rg = new vReg(curFunction.getvRegId(), para.type.getBytes());
            curBlock.addBack(new mvIns(rg, topModule.a.get(i)));
            regMap.put(para, rg);
        }
        for (int i = 8; i < irfunction.paraEntity.size(); ++i) {
            Entity para = irfunction.paraEntity.get(i);
            vReg rg = new vReg(curFunction.getvRegId(), para.type.getBytes());
            curBlock.addBack(new loadInsSet(rg, s0, new imm((i - 8) * 4), para.type.getBytes()));
            regMap.put(para, rg);
        }

        ;
    }

    @Override
    public void visitIRBlock(IRBlock irblock) {
        irblock.instList.forEach(inst -> inst.accept(this));
        irblock.terminator.accept(this);
    }

    @Override
    public void visit(allocaInst it) {
        vReg rg = new vReg(curFunction.getvRegId(), it.type.getBytes());
        regMap.put(it.rd, rg);
        curFunction.alloca(rg);
    }

    @Override
    public void visit(binaryInst it) {
        vReg res = new vReg(curFunction.getvRegId(), it.rd.type.getBytes());
        switch (it.opt) {
            case ADD -> curBlock.addBack(new binaryInsSet("add", res, getReg(it.rs1), getReg(it.rs2)));
            case SUB -> curBlock.addBack(new binaryInsSet("sub", res, getReg(it.rs1), getReg(it.rs2)));
            case MUL -> curBlock.addBack(new binaryInsSet("mul", res, getReg(it.rs1), getReg(it.rs2)));
            case SDIV -> curBlock.addBack(new binaryInsSet("div", res, getReg(it.rs1), getReg(it.rs2)));
            case SREM -> curBlock.addBack(new binaryInsSet("rem", res, getReg(it.rs1), getReg(it.rs2)));
            case AND -> curBlock.addBack(new binaryInsSet("and", res, getReg(it.rs1), getReg(it.rs2)));
            case OR -> curBlock.addBack(new binaryInsSet("or", res, getReg(it.rs1), getReg(it.rs2)));
            case XOR -> curBlock.addBack(new binaryInsSet("xor", res, getReg(it.rs1), getReg(it.rs2)));
            case SHL -> curBlock.addBack(new binaryInsSet("sll", res, getReg(it.rs1), getReg(it.rs2)));
            case ASHR -> curBlock.addBack(new binaryInsSet("sra", res, getReg(it.rs1), getReg(it.rs2)));
            default -> { }
        }
        regMap.put(it.rd, res);
    }

    @Override
    public void visit(bitcastInst it) {
        ;
    }

    @Override
    public void visit(brInst it) {
    }

    @Override
    public void visit(callInst it) {

    }

    @Override
    public void visit(declareInst it) {

    }

    @Override
    public void visit(getelementptrInst it) {

    }

    @Override
    public void visit(globalInst it) {

    }

    @Override
    public void visit(icmpInst it) {

    }

    @Override
    public void visit(loadInst it) {

    }

    @Override
    public void visit(phiInst it) {

    }

    @Override
    public void visit(retInst it) {

    }

    @Override
    public void visit(storeInst it) {

    }

    ;
}
