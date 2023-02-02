package Asm.Ins;

import Asm.AsmVisitor;
import Asm.Operand.Operand;

public class storeInsSet extends Ins {

    String type;
    public Operand val, addr, imm;
    String symbol;
    public Operand rt;

    public storeInsSet(Operand val, Operand addr, Operand imm, int bytes) {
        type = switch (bytes) {
            case 1 -> "sb";
            case 2 -> "sh";
            default -> "sw";
        };
        this.val = val;
        this.addr = addr;
        this.imm = imm;
        symbol = null;
        rt = null;
    }

    public storeInsSet(Operand val, String symbol, Operand rt, int bytes) {
        type = switch (bytes) {
            case 1 -> "sb";
            case 2 -> "sh";
            default -> "sw";
        };
        this.val = val;
        this.symbol = symbol;
        this.rt = rt;
        addr = null;
        imm = null;
    }

    @Override
    public String toString() {
        String str = "\t" + type + "\t" + val + ", ";
        if (addr == null) str += symbol + ", " + rt;
        else str += imm + "(" + addr + ")";
        return str;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
