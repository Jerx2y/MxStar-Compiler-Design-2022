package IR.IRType;

import IR.IRClass;

public class classIRType extends IRType {
    public IRClass c;
    int bytes;

    public classIRType(IRClass c) {
        this.c = c;
    }

    @Override
    public int getBytes() {
        return bytes;
    }

    public String getIdentifier() {
        return c.identifier;
    }

    public String toString() {
        return c.identifier;
    }
}
