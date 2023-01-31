package IR.IRType;

public class addrIRType extends IRType {

    public IRType type;

    public addrIRType(IRType type) {
        this.type = type;
    }

    public IRType getPtrType() {
        return type;
    }

    @Override
    public int getBytes() {
        return 4;
    }

    @Override
    public String toString() {
        return type + "*";
    }
}
