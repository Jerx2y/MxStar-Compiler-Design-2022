package Util;

import Parser.MxParser;

import java.util.HashMap;

public class Type {
    public enum BuiltinType{INT, BOOL, STRING, CLASS, VOID};
    public String className = null;
    int dimension = 0;
    public BuiltinType basicType;
    // public HashMap<String, Type> members = null;

    public Type(BuiltinType type) {
        this.basicType = type;
    }

    public Type(String classname) {
        this.basicType = BuiltinType.CLASS;
        this.className = classname;
    }

    public Type(BuiltinType type, int dim) {
        this.basicType = type;
        this.dimension = dim;
    }

    public Type(String classname, int dim) {
        this.basicType = BuiltinType.CLASS;
        this.className = classname;
        this.dimension = dim;
    }

    public Type(MxParser.TypenameContext ctx) {
        BuiltinType basetype;
        String classname = null;
        int dimension;
        if (ctx.Int() != null) basetype = BuiltinType.INT;
        else if (ctx.Bool() != null) basetype = BuiltinType.BOOL;
        else if (ctx.String() != null) basetype = BuiltinType.STRING;
        else {
            basetype = BuiltinType.CLASS;
            classname = ctx.Identifier().getText();
        }
        if (ctx.arrayParameter().isEmpty()) dimension = 0;
        else dimension = ctx.arrayParameter().LBrack().size();
        this.basicType = basetype;
        this.className = classname;
        this.dimension = dimension;
    }
}
