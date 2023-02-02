package Asm.Operand;

public class imm extends Operand {
    int val;

    public imm(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }
}
