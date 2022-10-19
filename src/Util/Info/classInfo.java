package Util.Info;

import Util.Type;
import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class classInfo {
    private HashMap<String, Type> vars;
    private HashMap<String, funcInfo> funcs;
    public classInfo() {
        vars = new HashMap<>();
        funcs = new HashMap<>();
    }
    public void addVar(String name, Type type, position pos) {
        if (vars.containsKey(name))
            throw new semanticError("multiple definition of var " + name + " in class", pos);
        vars.put(name, type);
    }
    public void addFunc(String name, funcInfo type, position pos) {
        if (funcs.containsKey(name))
            throw new semanticError("multiple definition of func " + name + " in class", pos);
        funcs.put(name, type);

    }
}
