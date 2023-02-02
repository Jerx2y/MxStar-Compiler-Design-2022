package Asm.Ins;

import Asm.AsmVisitor;

public class callIns extends Ins {

    String name;

    public callIns(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\tcall\t" + name;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
