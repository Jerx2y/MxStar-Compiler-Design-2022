package Asm.Ins;

import Asm.Operand.Operand;

public class loadInsSet extends Ins {

    String type;
    Operand rd, rs, imm;
    // null

    public loadInsSet(Operand rd, Operand rs, Operand imm, int Bytes) {
        type = switch (Bytes) {
            case 1 -> "lb";
            case 2 -> "lh";
            default -> "lw";
        };
        this.rd = rd;
        this.rs = rs;
        this.imm = imm;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void accept() {

    }
}
