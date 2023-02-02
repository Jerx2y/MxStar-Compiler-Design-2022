package Asm.Ins;

import Asm.AsmVisitor;

public class jIns extends Ins {

    String label;

    public jIns(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "\tj\t" + label;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
