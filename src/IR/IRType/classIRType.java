package IR.IRType;

import IR.IRClass;

public class classIRType extends IRType {
    public IRClass c;
    int bytes;

    public classIRType(IRClass c) {
        this.c = c;
        c.vars.forEach(v -> bytes += v.getBytes());
    }

    @Override
    public int getBytes() {
        return bytes;
    }

    public String getOriginIdentifier() {
        return c.identifier.substring(6);
    }

    public String toString() {
        return "%" + c.identifier;
    }
}
