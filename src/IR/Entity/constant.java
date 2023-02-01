package IR.Entity;

import IR.IRType.*;

public class constant extends Entity {
    public enum constantType {
        BOOL, INT, STRING, NULL, VOID
    }

    constantType cType;
    boolean i1;
    public int i32;
    public String str;

    public constant(IRType type) {
        super(type);
        if (type instanceof addrIRType || type instanceof arrayIRType || type instanceof classIRType || type instanceof ptrIRType)
            cType = constantType.NULL;
        else if (type instanceof iIRType) {
            if (((iIRType) type).getBits() == 1)
                cType = constantType.BOOL;
            else
                cType = constantType.INT;
        } else if (type instanceof voidIRType)
            cType = constantType.VOID;
        i1 = false; i32 = 0; str = "";
    }

    public constant(IRType type, boolean i1) {
        super(type);
        cType = constantType.BOOL;
        this.i1 = i1;
    }

    public constant(IRType type, int i32) {
        super(type);
        cType = constantType.INT;
        this.i32 = i32;
    }

    public constant(IRType type, String str) {
        super(type);
        cType = constantType.STRING;
        this.str = str;
    }

    public constant(IRType type, constantType cType) {
        super(type);
        this.cType = cType;
    }

    @Override
    public String getValue() {
        return switch (cType) {
            case BOOL -> Boolean.toString(i1);
            case INT -> Integer.toString(i32);
            case STRING -> "c\"" + str + "\\00\"";
            case NULL -> "null";
            case VOID -> "";
        };
    }

    public int getInt() {
        if (cType == constantType.BOOL)
            return i1 ? 1 : 0;
        else return i32;
    }

    public boolean isBool() {
        return cType == constantType.BOOL;
    }

    public String toString() {
        return super.toString() + " " + getValue();
    }

    @Override
    public boolean isNULL() {
        return cType == constantType.NULL;
    }

    public constant getNeg() {
        assert (cType == constantType.INT);
        return new constant(type, -i32);
    }

    public constant getNot() {
        return switch (cType) {
            case INT -> new constant(type, i32);
            case BOOL -> new constant(type, i1);
            default -> this;
        };
    }
}
