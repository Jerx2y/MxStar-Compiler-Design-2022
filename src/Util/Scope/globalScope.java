package Util.Scope;

import Util.Type.varType;
import Util.Type.classType;
import Util.Type.funcType;
import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class globalScope extends Scope {
    private final HashMap<String, classType> classes = new HashMap<>();
    private final HashMap<String, funcType> functions = new HashMap<>();
    public globalScope(Scope parentScope) {
        super(parentScope);
    }
    public void addClass(String name, classType c, position pos) {
        if (classes.containsKey(name) || functions.containsKey(name) || members.containsKey(name))
            throw new semanticError("[global scope] multiple definition of class " + name, pos);
        classes.put(name, c);
    }
    public classType getClassTypeFromName(String name, position pos) {
        if (classes.containsKey(name)) return classes.get(name);
        throw new semanticError("[global scope] no such class: " + name, pos);
    }
    public void addFunc(String name, funcType f, position pos) {
        if (functions.containsKey(name) || classes.containsKey(name) || members.containsKey(name))
            throw new semanticError("[global scope] multiple definition of function " + name, pos);
        functions.put(name, f);
    }

    @Override
    public funcType getFuncType(String name, boolean lookUpon) {
        if (functions.containsKey(name))
            return functions.get(name);
        if (parentScope != null && lookUpon)
            return parentScope.getFuncType(name, true);
        return null;
    }

    @Override
    public void defineVariable(String name, varType t, position pos) {
        if (members.containsKey(name) || classes.containsKey(name) || functions.containsKey(name))
            throw new semanticError("[global scope] Semantic Error: variable redefine", pos);
        members.put(name, t);
    }

    @Override
    public classType getClassType(String name) {
        return classes.get(name);
    }
}
