package Asm.Ins;

import Asm.AsmVisitor;
import Asm.Operand.Operand;

public class laIns extends Ins {

    public Operand rd;
    String name;

    public laIns(Operand rd, String name) {
        this.rd = rd;
        this.name = name;
    }

    @Override
    public String toString() {
        return "\tla\t" + rd + ", " + name;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
