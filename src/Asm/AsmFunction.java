package Asm;

import Asm.Operand.reg;
import Asm.Operand.vReg;

import java.util.ArrayList;
import java.util.HashMap;

public class AsmFunction {
    int index;
    public String name;

    int vRegNum = 0;
    int blockNum = 0;

    public int offset = 0;
    public HashMap<reg, Integer> stkOffset;
    public int callSize = 0;

    public AsmBlock entry;
    public ArrayList<AsmBlock> blocks;

    public AsmFunction(AsmModule topModule, String name) {
        this.index = topModule.functions.size();
        this.name = name;
        stkOffset = new HashMap<>();
        blocks = new ArrayList<>();
        stkOffset.put(topModule.ra, offset += 4);
        stkOffset.put(topModule.s.get(0), offset += 4);
    }


    public String getvRegId() {
        return Integer.toString(vRegNum++);
    }

    public String getBlockId() {
        return ".BB" + index + "_" + (blockNum++);
    }

    public void alloca(vReg rg) {
        offset += rg.size;
        stkOffset.put(rg, offset);
    }

    public AsmBlock getExit() {
        return blocks.size() > 0 ? blocks.get(blocks.size() - 1) : entry;
    }

    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
