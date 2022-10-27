package Util.Scope;

// import MIR.register;
import Util.Type.classType;
import Util.Type.funcType;
import Util.Type.varType;
import Util.error.semanticError;
import Util.position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;

public class Scope {

    protected HashMap<String, varType> members;
    protected Scope parentScope;
    public boolean lookup = true;

    public Scope(Scope parentScope) {
        members = new HashMap<>();
        this.parentScope = parentScope;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, varType t, position pos) {
        if (members.containsKey(name))
            throw new semanticError("variable redefine", pos);
        if (parentScope != null && parentScope.getClassType(name) != null)
            throw new semanticError("define variable with class name", pos);
        members.put(name, t);
    }

    public varType getVarType(String name, boolean lookUpon) {
        if (members.containsKey(name)) return members.get(name);
        else if (parentScope != null && lookUpon && lookup)
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

    public Pair<varType, funcType> getIdentifier(String name, boolean lookUpon) {
        varType vtype = members.get(name);
        if (vtype != null)
            return new Pair<>(vtype, null);
        if (parentScope != null && lookUpon && lookup)
            return parentScope.getIdentifier(name, true);
        return new Pair<>(null, null);
    }
}