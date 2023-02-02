package Asm.Ins;

import Asm.AsmVisitor;
import Asm.Operand.Operand;

public class mvIns extends Ins {
    public Operand rd, rs;

    public mvIns(Operand rd, Operand rs) {
        this.rd = rd;
        this.rs = rs;
    }

    @Override
    public String toString() {
        return "\tmv\t" + rd + ", " + rs;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
