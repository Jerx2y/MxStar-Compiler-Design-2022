package Util.Scope;

import IR.IRClass;
import IR.IRFunction;
import Util.Type.classType;
import Util.Type.funcType;
import Util.error.semanticError;
import Util.position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;

public class globalScope extends Scope {
    private final HashMap<String, classType> classes = new HashMap<>();
    private final HashMap<String, funcType> functions = new HashMap<>();

    private final HashMap<String, IRClass> IRClasses = new HashMap<>();
    public globalScope(Scope parentScope) {
        super(parentScope);
    }

    public void addClass(String name, classType c, position pos) {
        if (classes.containsKey(name) || functions.containsKey(name) || members.containsKey(name))
            throw new semanticError("[global scope] multiple definition of class " + name, pos);
        classes.put(name, c);
    }

    public void addFunc(String name, funcType f, position pos) {
        if (functions.containsKey(name) || classes.containsKey(name) || members.containsKey(name))
            throw new semanticError("[global scope] multiple definition of function " + name, pos);
        functions.put(name, f);
    }

    @Override
    public void defineVariable(String name, classType t, position pos) {
        if (members.containsKey(name) || classes.containsKey(name) || functions.containsKey(name))
            throw new semanticError("[global scope] variable redefine", pos);
        members.put(name, t);
    }

    @Override
    public funcType getFuncType(String name) {
        if (functions.containsKey(name))
            return functions.get(name);
        if (parentScope != null)
            return parentScope.getFuncType(name);
        return null;
    }

    @Override
    public classType getClassType(String name) {
        return classes.get(name);
    }

    public HashMap<String, classType> getClasses() {
        return classes;
    }

    @Override
    public Pair<classType, funcType> getIdentifier(String name) {
        classType vtype = members.get(name);
        funcType ftype = functions.get(name);
        if (vtype != null)
            return new Pair<>(vtype, null);
        if (ftype != null)
            return new Pair<>(null, ftype);
        if (parentScope != null && lookup)
            return parentScope.getIdentifier(name);
        return new Pair<>(null, null);
    }

    public IRClass getIRClasses(String identifier) {
        return IRClasses.get(identifier);
    }

    public void putIRClasses(String identifier, IRClass ic) {
        IRClasses.put(identifier, ic);
    }
}
