package Asm.Ins;

import Asm.AsmVisitor;
import Asm.Operand.Operand;

public class brInsSet extends Ins {
    String type, label;

    public Operand condition;

    public brInsSet(String type, Operand condition, String label) {
        this.type = type;
        this.condition = condition;
        this.label = label;
    }

    @Override
    public String toString() {
        return "\t" + type + "\t" + condition + ", " + label;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
