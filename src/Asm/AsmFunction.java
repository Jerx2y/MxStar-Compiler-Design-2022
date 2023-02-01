package Asm;

import Asm.Operand.reg;
import Asm.Operand.vReg;

import java.util.HashMap;

public class AsmFunction {
    int vRegNum = 0;

    int offset = 0;
    HashMap<reg, Integer> stkOffset;


    public String getvRegId() {
        return Integer.toString(vRegNum++);
    }

    public void alloca(vReg rg) {
        offset += rg.size;
        stkOffset.put(rg, offset);
    }
}
