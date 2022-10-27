package Util.Scope;

import Util.Type.varType;
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

    public funcType getFuncType(String name, boolean lookUpon) {
        if (functions.containsKey(name))
            return functions.get(name);
        if (parentScope != null && lookUpon)
            return parentScope.getFuncType(name, true);
        return null;
    }

    @Override
    public void defineVariable(String name, varType t, position pos) {
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
    public Pair<varType, funcType> getIdentifier(String name, boolean lookUpon) {
        varType vtype = members.get(name);
        funcType ftype = functions.get(name);
        if (vtype != null)
            return new Pair<>(vtype, null);
        if (ftype != null)
            return new Pair<>(null, ftype);
        if (parentScope != null && lookUpon && lookup)
            return parentScope.getIdentifier(name, true);
        return new Pair<>(null, null);
    }

    public HashMap<String, varType> getVarsList() {
        return members;
    }
    public HashMap<String, funcType> getFuncList() {
        return functions;
    }
}
