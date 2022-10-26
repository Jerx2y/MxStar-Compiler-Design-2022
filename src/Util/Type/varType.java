package Util.Type;

import Parser.MxParser;
import Util.Scope.Scope;
import Util.Scope.globalScope;
import Util.error.semanticError;
import Util.position;

public class varType {
    public enum BuiltinType{INT, BOOL, STRING, VOID, NULL, THIS, CLASS, FUNC};
    public BuiltinType basicType;
    public classType ctype = null;
    public funcType ftype = null;
    public int dimension = 0;

    public boolean isVar(BuiltinType basicType) {
        return basicType == this.basicType && dimension == 0;
    }

    public boolean isArray() {
        return dimension > 0;
    }

    public varType(varType that) {
        this.basicType = that.basicType;
        this.ctype = that.ctype;
        this.ftype = that.ftype;
        this.dimension = that.dimension;
    }

    public varType(BuiltinType type) {
        this.basicType = type;
    }

    public varType(classType ctype) {
        this.basicType = BuiltinType.CLASS;
        this.ctype = ctype;
    }

    public varType(BuiltinType type, int dim) {
        this.basicType = type;
        this.dimension = dim;
    }

    public varType(classType ctype, int dim) {
        this.basicType = BuiltinType.CLASS;
        this.ctype = ctype;
        this.dimension = dim;
    }

    public varType(funcType ftype) {
        this.basicType = BuiltinType.FUNC;
        this.ftype = ftype;
    }

    public varType(MxParser.TypenameContext ctx, globalScope gScope) {
        BuiltinType basetype;
        classType ctype = null;
        if (ctx == null) basetype = BuiltinType.VOID;
        else if (ctx.Int() != null) basetype = BuiltinType.INT;
        else if (ctx.Bool() != null) basetype = BuiltinType.BOOL;
        else if (ctx.String() != null) basetype = BuiltinType.STRING;
        else {
            basetype = BuiltinType.CLASS;
            ctype = gScope.getClassTypeFromName(ctx.Identifier().getText(), new position(ctx));
        }
        this.basicType = basetype;
        this.ctype = ctype;
        if (ctx != null) this.dimension = ctx.bracket().size();
    }

    public boolean equal(varType that) {
        if (this.basicType != that.basicType || this.dimension != that.dimension)
            return false;
        if (ctype != null)
            return ctype.classname.equals(that.ctype.classname);
        return true;
    }

}
