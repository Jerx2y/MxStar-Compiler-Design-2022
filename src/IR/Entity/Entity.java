package IR.Entity;

import IR.IRType.*;

public abstract class Entity {
    public IRType type;

    Entity(IRType type) {
        this.type = type;
    }

    public abstract String getValue();

    public String toString() {
        return type.toString();
    }

    public boolean isNULL() {
        return false;
    }

    public boolean isStr() {
        if (type instanceof ptrIRType) {
            IRType tmp = ((ptrIRType) type).getPtrType();
            if (tmp instanceof iIRType)
                return ((iIRType) tmp).getBits() == 8;
        }
        return false;
    }
}
