package Asm.Operand;

public class vReg extends reg {
    public int size;

    public vReg(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public String toString() {
        return "%" + name;
    }
}
