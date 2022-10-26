package Util.Scope;

import Util.Type.varType;
import Util.Type.classType;
import Util.Type.funcType;
import Util.error.semanticError;
import Util.position;

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
            throw new semanticError("Semantic Error: variable redefine", pos);
        members.put(name, t);
    }

    @Override
    public classScope getClassScope() {
        return this;
    }
}
