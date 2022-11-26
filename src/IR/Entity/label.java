package IR.Entity;

import IR.IRType.IRType;
import IR.IRType.labelIRType;

public class label extends Entity {
    String name;

    label(String name) {
        super(new labelIRType());
        this.name = name;
    }

    @Override
    public String getValue() {
        return name;
    }

    public String toString() {
        return super.toString() + " %" + name;
    }
}
