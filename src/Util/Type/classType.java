package Util.Type;

import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class classType {
    private final HashMap<String, varType> vars;
    private final HashMap<String, funcType> func;
    public classType() {
        vars = new HashMap<>();
        func = new HashMap<>();
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
}
