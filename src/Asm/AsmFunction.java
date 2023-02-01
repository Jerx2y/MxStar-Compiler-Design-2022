package Asm;

import Asm.Operand.reg;
import Asm.Operand.vReg;

import java.util.ArrayList;
import java.util.HashMap;

public class AsmFunction {
    int index;
    String name;

    int vRegNum = 0;
    int blockNum = 0;

    int offset = 0;
    public HashMap<reg, Integer> stkOffset;

    public AsmBlock entry;
    public ArrayList<AsmBlock> blocks;

    public AsmFunction(int index, String name) {
        this.index = index;
        this.name = name;
        stkOffset = new HashMap<>();
        blocks = new ArrayList<>();
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
}
