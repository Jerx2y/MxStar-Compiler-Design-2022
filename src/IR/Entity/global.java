package IR.Entity;

import IR.IRType.IRType;

public class global extends Entity {
    String name;
    public global(IRType type) {
        super(type);
    }

    @Override
    public String getValue() {
        return "@" + name;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getValue();
    }
}
