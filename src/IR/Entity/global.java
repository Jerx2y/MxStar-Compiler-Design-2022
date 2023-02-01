package IR.Entity;

import IR.IRType.IRType;

public class global extends Entity {
    public String name;
    public global(IRType type, String name) {
        super(type);
        this.name = name;
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
