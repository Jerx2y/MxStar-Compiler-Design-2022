package Asm.Ins;

import Asm.Operand.Operand;

public class liIns extends Ins {

    Operand rd, imm;

    public liIns(Operand rd, Operand imm) {
        this.rd = rd;
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
