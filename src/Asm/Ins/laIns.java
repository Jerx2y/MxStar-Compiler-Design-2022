package Asm.Ins;

import Asm.Operand.Operand;

public class laIns extends Ins {

    Operand rd;
    String name;

    public laIns(Operand rd, String name) {
        this.rd = rd;
        this.name = name;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void accept() {

    }
}
