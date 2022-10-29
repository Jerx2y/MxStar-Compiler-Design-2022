package Util.Scope;

import Util.Type.classType;
import Util.Type.funcType;
import Util.error.semanticError;
import Util.position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;

public class classScope extends Scope {
    public final String classname;
    private final HashMap<String, funcType> functions;

    public classScope(classType info, Scope parentScope) {
        super(parentScope);
        members = info.getVarsList();
        functions = info.getFuncList();
        classname = info.classname;
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
    public void defineVariable(String name, classType t, position pos) {
        if (members.containsKey(name) || functions.containsKey(name))
            throw new semanticError("variable redefine", pos);
        if (parentScope != null && parentScope.getClassType(name) != null)
            throw new semanticError("define variable with class name", pos);
        members.put(name, t);
    }

    @Override
    public classScope getClassScope() {
        return this;
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

    public HashMap<String, classType> getVarsList() {
        return members;
    }
    public HashMap<String, funcType> getFuncList() {
        return functions;
    }
}
