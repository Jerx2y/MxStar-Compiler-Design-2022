package IR.Entity;

import IR.IRType.IRType;

public class register extends Entity {
    String name;

    public register(IRType type, String name) {
        super(type);
        this.name = name;
    }

    @Override
    public String getValue() {
        return "%" + name;
    }

    public String toString() {
        return super.toString() + " " + getValue();
    }
}
