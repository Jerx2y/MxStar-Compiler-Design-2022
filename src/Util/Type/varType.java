package Util.Type;

import Parser.MxParser;

public class varType {
    public enum BuiltinType{INT, BOOL, STRING, CLASS, VOID, NULL, FUNC, THIS};
    public BuiltinType basicType;
    public String name = null;
    public int dimension = 0;

    public boolean isVar(BuiltinType basicType) {
        return basicType == this.basicType && dimension == 0;
    }

    public boolean isArray() {
        return dimension > 0;
    }

    public varType(varType that) {
        this.basicType = that.basicType;
        this.name = that.name;
        this.dimension = that.dimension;
    }

    public varType(BuiltinType type) {
        this.basicType = type;
    }

    public varType(String classname) {
        this.basicType = BuiltinType.CLASS;
        this.name = classname;
    }

    public varType(BuiltinType type, int dim) {
        this.basicType = type;
        this.dimension = dim;
    }

    public varType(String classname, int dim) {
        this.basicType = BuiltinType.CLASS;
        this.name = classname;
        this.dimension = dim;
    }

    public varType(MxParser.TypenameContext ctx) {
        BuiltinType basetype;
        String classname = null;
        if (ctx.Int() != null) basetype = BuiltinType.INT;
        else if (ctx.Bool() != null) basetype = BuiltinType.BOOL;
        else if (ctx.String() != null) basetype = BuiltinType.STRING;
        else {
            basetype = BuiltinType.CLASS;
            classname = ctx.Identifier().getText();
        }
        this.basicType = basetype;
        this.name = classname;
        this.dimension = ctx.bracket().size();
    }

    public varType(String name, boolean isFunction) {
        this.name = name;
        this.basicType = BuiltinType.FUNC;
    }

    public boolean equal(varType that) {
        return this.basicType != that.basicType || !this.name.equals(that.name) || this.dimension != that.dimension;
    }

}
