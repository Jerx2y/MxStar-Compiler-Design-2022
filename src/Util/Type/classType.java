package Util.Type;

import Parser.MxParser;
import Util.Scope.globalScope;
import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class classType extends Type {
    public String classname;
    public int dimension = 0;
    private final HashMap<String, classType> vars;
    private final HashMap<String, funcType> func;

    public classType(String name) {
        vars = new HashMap<>();
        func = new HashMap<>();
        classname = name;
    }

    public classType(String name, HashMap<String, classType> vars, HashMap<String, funcType> func) {
        classname = name;
        this.vars = vars;
        this.func = func;
    }

    public classType(MxParser.TypenameContext ctx, globalScope gScope) {
        classType ctype = null;
        if (ctx.Int() != null) ctype = gScope.getClassType("int");
        else if (ctx.Bool() != null) ctype = gScope.getClassType("bool");
        else if (ctx.String() != null) ctype = gScope.getClassType("string");
        else ctype = gScope.getClassType(ctx.Identifier().getText());
        if (ctype == null)
            throw new semanticError("[class type] type not found", null);
        classname = ctype.classname;
        vars = ctype.vars;
        func = ctype.func;
        dimension = ctx.bracket().size();
    }

    public classType(Type that) {
        if (!(that instanceof classType))
            throw new semanticError("[class type] type invalid", null);
        classname = ((classType) that).classname;
        dimension = ((classType) that).dimension;
        vars = ((classType) that).vars;
        func = ((classType) that).func;
    }

    @Override
    public boolean isType(String name) {
        return classname.equals(name) && dimension == 0;
    }

    @Override
    public boolean isArray() {
        return dimension > 0;
    }

    @Override
    public boolean equal(Type that) {
        if (!(that instanceof classType)) return false;
        if (this.isType("null") && !that.isType("int") && !that.isType("bool") && !that.isType("string") ||
            that.isType("null") && !this.isType("int") && !this.isType("bool") && !this.isType("string"))
            return true;
        return classname.equals(((classType) that).classname) && dimension == ((classType) that).dimension;
    }

    public void addVar(String name, classType varType, position pos) {
        if (vars.containsKey(name))
            throw new semanticError("multiple definition of var " + name + " in class", pos);
        vars.put(name, varType);
    }

    public void addFunc(String name, funcType type, position pos) {
        if (func.containsKey(name))
            throw new semanticError("multiple definition of function " + name + " in class", pos);
        func.put(name, type);
    }

    public HashMap<String, classType> getVarsList() {
        return vars;
    }

    public HashMap<String, funcType> getFuncList() {
        return func;
    }

    public classType getVar(String name) {
        return vars.get(name);
    }

    public funcType getFunc(String name) {
        return func.get(name);
    }

}
