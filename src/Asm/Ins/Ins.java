package Asm.Ins;

import Asm.AsmVisitor;

public class Ins {
    public Ins prev = null, next = null;

    public String toString() { return "Ins"; }

    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
