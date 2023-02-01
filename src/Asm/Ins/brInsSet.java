package Asm.Ins;

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
        return null;
    }

    @Override
    public void accept() {

    }
}
