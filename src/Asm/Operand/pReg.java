package Asm.Operand;

public class pReg extends reg {
    public pReg(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
