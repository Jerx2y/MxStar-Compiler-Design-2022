package IR.IRType;

public class arrayIRType extends IRType {
    int size;
    public IRType type;

    public arrayIRType(int size, IRType type) {
        this.size = size;
        this.type = type;
    }

    @Override
    public int getBytes() {
        return size * type.getBytes();
    }

    public String toString() {
        return "[" + size + " x " + type + "]";
    }
}
