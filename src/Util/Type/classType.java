package Util.Type;

import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class classType {
    public String classname;
    private final HashMap<String, varType> vars;
    private final HashMap<String, funcType> func;
    public classType(String name) {
        vars = new HashMap<>();
        func = new HashMap<>();
        classname = name;
    }
    public classType(String name, HashMap<String, varType> vars, HashMap<String, funcType> func) {
        classname = name;
        this.vars = vars;
        this.func = func;
    }
    public void addVar(String name, varType varType, position pos) {
        if (vars.containsKey(name))
            throw new semanticError("multiple definition of var " + name + " in class", pos);
        vars.put(name, varType);
    }
    public void addFunc(String name, funcType type, position pos) {
        if (func.containsKey(name))
            throw new semanticError("multiple definition of func " + name + " in class", pos);
        func.put(name, type);
    }
    public HashMap<String, varType> getVarsList() {
        return vars;
    }
    public HashMap<String, funcType> getFuncList() {
        return func;
    }

    public varType getVar(String name) {
        return vars.get(name);
    }

    public funcType getFunc(String name) {
        return func.get(name);
    }

}
