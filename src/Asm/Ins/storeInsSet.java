package Asm.Ins;

import Asm.Operand.Operand;

import java.util.Spliterator;

public class storeInsSet extends Ins {

    String type;
    Operand val, addr, imm;
    String symbol;
    Operand rt;

    public storeInsSet(Operand val, Operand addr, Operand imm, int bytes) {
        type = switch (bytes) {
            case 1 -> "lb";
            case 2 -> "lh";
            default -> "lw";
        };
        this.val = val;
        this.addr = addr;
        this.imm = imm;
        symbol = null;
        rt = null;
    }

    public storeInsSet(Operand val, String symbol, Operand rt, int bytes) {
        type = switch (bytes) {
            case 1 -> "lb";
            case 2 -> "lh";
            default -> "lw";
        };
        this.val = val;
        this.symbol = symbol;
        this.rt = rt;
        addr = null;
        imm = null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void accept() {

    }
}
