package Asm.Ins;

import Asm.Operand.Operand;

public class mvIns extends Ins {
    Operand rd, rs;

    public mvIns(Operand rd, Operand rs) {
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
