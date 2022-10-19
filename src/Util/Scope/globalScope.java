package Util.Scope;

import Util.Info.*;
import Util.Type;
import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class globalScope extends Scope {
    private HashMap<String, classInfo> classes = new HashMap<>();
    private HashMap<String, funcInfo> functions = new HashMap<>();
    public globalScope(Scope parentScope) {
        super(parentScope);
    }
    public void addClass(String name, classInfo c, position pos) {
        if (classes.containsKey(name))
            throw new semanticError("multiple definition of class " + name, pos);
        classes.put(name, c);
    }
    public classInfo getClassTypeFromName(String name, position pos) {
        if (classes.containsKey(name)) return classes.get(name);
        throw new semanticError("no such class: " + name, pos);
    }
    public void addFunc(String name, funcInfo f, position pos) {
        if (functions.containsKey(name))
            throw new semanticError("multiple definition of function " + name, pos);
        functions.put(name, f);
    }
    public funcInfo getFuncTypeFromName(String name, position pos) {
        if (functions.containsKey(name)) return functions.get(name);
        throw new semanticError("no such function: " + name, pos);
    }
}
