package Asm.Ins;

import Asm.AsmVisitor;
import Asm.Operand.Operand;

public class liIns extends Ins {

    public Operand rd;
    Operand imm;

    public liIns(Operand rd, Operand imm) {
        this.rd = rd;
        this.imm = imm;
    }

    @Override
    public String toString() {
        return "\tli\t" + rd + ", " + imm;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
