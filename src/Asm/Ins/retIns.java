package Asm.Ins;

import Asm.AsmVisitor;

public class retIns extends Ins {

    @Override
    public String toString() {
        return "\tret";
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
