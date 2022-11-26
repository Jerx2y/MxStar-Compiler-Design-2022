package IR.IRType;

public class voidIRType extends IRType {
    @Override
    public int getBytes() {
        return 0;
    }

    public String toString() {
        return "void";
    }
}
