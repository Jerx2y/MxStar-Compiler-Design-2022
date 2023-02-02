package Asm.Ins;

import Asm.AsmVisitor;
import Asm.Operand.Operand;

public class cmpInsSet extends Ins {

    String type;
    public Operand rd, rs;

    public cmpInsSet(String type, Operand rd, Operand rs) {
        this.type = type;
        this.rd = rd;
        this.rs = rs;
    }

    @Override
    public String toString() {
        return "\t" + type + "\t" + rd + ", " + rs;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
