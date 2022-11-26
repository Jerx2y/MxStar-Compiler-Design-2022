package IR.IRType;

import Util.Type.classType;

public abstract class IRType {
    public abstract int getBytes();

    static public IRType fromClassType(classType type) {
        IRType res = switch (type.classname) {
            case "int" -> new iIRType(32);
            case "bool" -> new iIRType(1);
            case "string" -> new ptrIRType(new iIRType(8));
            default -> new ptrIRType(new classIRType());
        };

        for (int i = 0; i < type.dimension; ++i)
            res = new ptrIRType(res);

        return res;
    }
}