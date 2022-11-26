package IR.IRType;

public class iIRType extends IRType {
    int bit;

    public iIRType(int bit) {
        this.bit = bit;
    }

    public int getBits() {
        return bit;
    }

    @Override
    public int getBytes() {
        return (bit - 1) / 8 + 1;
    }

    public String toString() {
        return "i" + bit;
    }
}
