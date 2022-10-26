package Util.Scope;

// import MIR.register;
import Util.Type.classType;
import Util.Type.funcType;
import Util.Type.varType;
import Util.error.semanticError;
import Util.position;

import java.util.HashMap;

public class Scope {

    protected HashMap<String, varType> members;
    protected Scope parentScope;


    public Scope(Scope parentScope) {
        members = new HashMap<>();
        this.parentScope = parentScope;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, varType t, position pos) {
        if (members.containsKey(name))
            throw new semanticError("Semantic Error: variable redefine", pos);
        members.put(name, t);
    }

    public boolean containsVariable(String name, boolean lookUpon) {
        if (members.containsKey(name)) return true;
        else if (parentScope != null && lookUpon)
            return parentScope.containsVariable(name, true);
        else return false;
    }

    public varType getVarType(String name, boolean lookUpon) {
        if (members.containsKey(name)) return members.get(name);
        else if (parentScope != null && lookUpon)
            return parentScope.getVarType(name, true);
        return null;
    }

    public funcType getFuncType(String name, boolean lookUpon) {
        return parentScope.getFuncType(name, lookUpon);
    }

    public classType getClassType(String name) {
        return null;
    }

    public funcType getThisFuncInfo() {
        if (parentScope != null)
            return parentScope.getThisFuncInfo();
        return null;
    }

    public classScope getClassScope() {
        if (parentScope != null)
            return parentScope.getClassScope();
        return null;
    }
}