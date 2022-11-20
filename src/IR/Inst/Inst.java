package IR.Inst;

import IR.IRVisitor;

public abstract class Inst {

    public Inst() {

    }

    public abstract void accept(IRVisitor visitor);
    public abstract String toString();
}
