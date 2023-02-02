package BackEnd;

import Asm.AsmBlock;
import Asm.AsmData;
import Asm.AsmFunction;
import Asm.AsmModule;
import Asm.Ins.*;
import Asm.Operand.imm;
import Asm.Operand.pReg;
import Asm.Operand.reg;
import Asm.Operand.vReg;
import IR.*;
import IR.Entity.Entity;
import IR.Entity.constant;
import IR.Entity.global;
import IR.IRType.IRType;
import IR.IRType.arrayIRType;
import IR.IRType.classIRType;
import IR.IRType.iIRType;
import IR.Inst.*;

import java.util.HashMap;

public class InsSelector implements IRVisitor {

    AsmModule topModule;
    AsmFunction curFunction;
    AsmBlock curBlock;
    HashMap<Entity, reg> regMap;
    pReg zero, ra, sp, s0, a0;

    HashMap<Entity, AsmBlock> blockMap;

    public InsSelector(AsmModule topModule) {
        this.topModule = topModule;
        regMap = new HashMap<>();
        blockMap = new HashMap<>();

        zero = topModule.zero;
        ra = topModule.ra;
        sp = topModule.sp;
        s0 = topModule.s.get(0);
        a0 = topModule.a.get(0);
    }

    @Override
    public void visit(IRModule irmodule) {
        irmodule.globals.forEach(inst -> inst.accept(this));
        irmodule.classes.forEach(c -> c.accept(this));
        irmodule.functions.forEach(f -> f.accept(this));
    }

    @Override
    public void visit(IRClass irclass) {
        int sum = 0;
        for (IRType type : irclass.vars) {
            irclass.pos.add(sum);
            sum += type.getBytes();
        }
    }

    @Override
    public void visit(IRFunction irfunction) {
        curFunction = new AsmFunction(topModule, irfunction.identifier);

        curBlock = curFunction.entry = new AsmBlock(curFunction.getBlockId());

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

        blockMap = new HashMap<>();
        irfunction.blocks.forEach(b -> {
            curBlock = new AsmBlock(curFunction.getBlockId());
            blockMap.put(b.name, curBlock);
            curFunction.blocks.add(curBlock);
        });

        // System.err.println(curFunction.name + "#");
        curFunction.entry.addBack(new jIns(curFunction.blocks.get(0).label));

        irfunction.blocks.forEach(b -> b.accept(this));

        topModule.functions.add(curFunction);
    }

    @Override
    public void visit(IRBlock irblock) {
        curBlock = blockMap.get(irblock.name);
        irblock.instList.forEach(inst -> inst.accept(this));
        irblock.terminator.accept(this);
    }

    reg getReg(Entity x) {
        if (x instanceof constant) {
            int val = ((constant) x).getInt();
            if (val == 0) return zero;
            reg rg = new vReg(curFunction.getvRegId(), 4);
            curBlock.addBack(new liIns(rg, new imm(val)));
            return rg;
        } else if (x instanceof global) {
            reg rg = new vReg(curFunction.getvRegId(), 4);
            curBlock.addBack(new laIns(rg, ((global) x).name));
            return rg;
        }
        return regMap.get(x);
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
        regMap.put(it.rd, getReg(it.rs));
    }

    @Override
    public void visit(brInst it) {
        // br cond d1 d2 -> beqz cond d2
        if (it.cond != null)
            curBlock.addBack(new brInsSet("beqz", getReg(it.cond), blockMap.get(it.d2).label));

        // j d1
        curBlock.addBack(new jIns(blockMap.get(it.d1).label));
    }

    @Override
    public void visit(callInst it) {
        for (int i = 0; i < Integer.min(8, it.para.size()); ++i)
            curBlock.addBack(new mvIns(topModule.a.get(i), getReg(it.para.get(i))));
        int size = 0;
        for (int i = 8; i < it.para.size(); ++i) {
            Entity val = it.para.get(i);
            size += val.type.getBytes();
            curBlock.addBack(new storeInsSet(getReg(val), sp, new imm((i - 8) * 4), val.type.getBytes()));
        }
        curFunction.callSize = Integer.max(curFunction.callSize, size);

        curBlock.addBack(new callIns(it.name));

        if (it.rd != null) {
            vReg rg = new vReg(curFunction.getvRegId(), it.rd.type.getBytes());
            curBlock.addBack(new mvIns(rg, a0));
            regMap.put(it.rd, rg);
        }
    }

    @Override
    public void visit(declareInst it) { }

    @Override
    public void visit(getelementptrInst it) {
        reg res = new vReg(curFunction.getvRegId(), it.rd.type.getBytes());
        if (it.idx.size() == 1) { // array
            Entity idx = it.idx.get(0);
            reg pos = new vReg(curFunction.getvRegId(), idx.type.getBytes());
            curBlock.addBack(new binaryInsSet("mul", pos, getReg(idx), getReg(new constant(idx.type, it.type.getBytes()))));
            curBlock.addBack(new binaryInsSet("add", res, getReg(it.rs), pos));
        } else { // class
            Entity idx = it.idx.get(1);
            assert idx instanceof constant;
            assert it.type instanceof classIRType;
            int pos = ((classIRType) it.type).c.pos.get(((constant) idx).i32);
            if (pos != 0)
                curBlock.addBack(new binaryInsSet("addi", res, getReg(it.rs), new imm(pos)));
        }
        regMap.put(it.rd, res);
    }

    @Override
    public void visit(globalInst it) {
        assert it.init instanceof constant;
        assert it.rd instanceof global;
        if (it.dType == globalInst.defineType.CONSTANT) {
            assert it.init.type instanceof arrayIRType;
            assert ((arrayIRType) it.init.type).type instanceof iIRType;
            String str = ((constant) it.init).str;
            str = str.replace("\\5C", "\\\\").replace("\\0A", "\\n")
                    .replace("\\09", "\\t").replace("\\22", "\\\"")
                    .replace("\\00", "\\0");

            topModule.data.add(new AsmData(((global) it.rd).name, str, ".rodata"));
        } else {
            constant c = (constant) it.init;
            topModule.data.add(new AsmData(((global) it.rd).name, c.getInt(), ".sdata", c.isBool()));
        }
    }

    @Override
    public void visit(icmpInst it) {
        reg res = new vReg(curFunction.getvRegId(), it.rd.type.getBytes());
        switch (it.opt) {
            case EQ -> {
                reg tmp = new vReg(curFunction.getvRegId(), it.rs1.type.getBytes());
                curBlock.addBack(new binaryInsSet("xor", tmp, getReg(it.rs1), getReg(it.rs2)));
                curBlock.addBack(new cmpInsSet("seqz", res, tmp));
            }
            case NE -> {
                reg tmp = new vReg(curFunction.getvRegId(), it.rs1.type.getBytes());
                curBlock.addBack(new binaryInsSet("xor", tmp, getReg(it.rs1), getReg(it.rs2)));
                curBlock.addBack(new cmpInsSet("snez", res, tmp));
            }
            case SLT -> curBlock.addBack(new binaryInsSet("slt", res, getReg(it.rs1), getReg(it.rs2)));
            case SGT -> curBlock.addBack(new binaryInsSet("slt", res, getReg(it.rs2), getReg(it.rs1)));
            case SLE -> {
                curBlock.addBack(new binaryInsSet("slt", res, getReg(it.rs2), getReg(it.rs1)));
                curBlock.addBack(new binaryInsSet("xor", res, res, new imm(1)));
            }
            case SGE -> {
                curBlock.addBack(new binaryInsSet("slt", res, getReg(it.rs1), getReg(it.rs2)));
                curBlock.addBack(new binaryInsSet("xor", res, res, new imm(1)));
            }
        }
        regMap.put(it.rd, res);
    }

    @Override
    public void visit(loadInst it) {
        vReg res = new vReg(curFunction.getvRegId(), it.rd.type.getBytes());
        if (it.rs instanceof global)
            curBlock.addBack(new loadInsSet(res, ((global) it.rs).name, it.rd.type.getBytes()));
        else {
            reg rs = getReg(it.rs);
            if (curFunction.stkOffset.containsKey(rs))
                curBlock.addBack(new mvIns(res, rs));
            else curBlock.addBack(new loadInsSet(res, rs, new imm(0), it.rd.type.getBytes()));
        }
        regMap.put(it.rd, res);
    }

    @Override
    public void visit(retInst it) {
        curBlock.addBack(new mvIns(a0, getReg(it.ret)));
        curBlock.addBack(new retIns());
    }

    @Override
    public void visit(storeInst it) {
        if (it.rd instanceof global)
            curBlock.addBack(new storeInsSet(getReg(it.rs), ((global) it.rd).name,
                    new vReg(curFunction.getvRegId(), 4), it.rs.type.getBytes()));
        else {
            reg addr = getReg(it.rd);
            if (curFunction.stkOffset.containsKey(addr))
                curBlock.addBack(new mvIns(addr, getReg(it.rs)));
            else curBlock.addBack(new storeInsSet(getReg(it.rs), addr, new imm(0), it.rs.type.getBytes()));
        }
    }
}
