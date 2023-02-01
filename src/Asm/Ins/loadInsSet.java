package Asm.Ins;

import Asm.Operand.Operand;

import java.util.Spliterator;

public class loadInsSet extends Ins {

    String type;
    Operand rd, rs, imm;
    String symbol;

    public loadInsSet(Operand rd, Operand rs, Operand imm, int bytes) {
        type = switch (bytes) {
            case 1 -> "lb";
            case 2 -> "lh";
            default -> "lw";
        };
        this.rd = rd;
        this.rs = rs;
        this.imm = imm;
        symbol = null;
    }

    public loadInsSet(Operand rd, String symbol, int bytes) {
        type = switch (bytes) {
            case 1 -> "lb";
            case 2 -> "lh";
            default -> "lw";
        };
        this.rd = rd;
        this.symbol = symbol;
        this.rs = null;
        this.imm = null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void accept() {

    }
}
