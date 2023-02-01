package Asm.Ins;

import Asm.Operand.Operand;

public class cmpInsSet extends Ins {

    String type;
    Operand rd, rs;

    public cmpInsSet(String type, Operand rd, Operand rs) {
        this.type = type;
        this.rd = rd;
        this.rs = rs;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void accept() {

    }
}
