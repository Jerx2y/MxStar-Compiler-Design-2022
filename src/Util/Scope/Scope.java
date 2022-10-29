package Util.Scope;

// import MIR.register;
import Util.Type.classType;
import Util.Type.funcType;
import Util.error.semanticError;
import Util.position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;

public class Scope {

    protected HashMap<String, classType> members;
    protected Scope parentScope;
    public boolean lookup = true;

    public Scope(Scope parentScope) {
        members = new HashMap<>();
        this.parentScope = parentScope;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public void defineVariable(String name, classType t, position pos) {
        if (members.containsKey(name))
            throw new semanticError("[define variable] variable redefine", pos);
        if (parentScope != null && parentScope.getClassType(name) != null)
            throw new semanticError("[define variable] variable name invalid", pos);
        members.put(name, t);
    }

    public classType getVarType(String name) {
        if (members.containsKey(name)) return members.get(name);
        else if (parentScope != null && lookup)
            return parentScope.getVarType(name);
        return null;
    }

    public funcType getFuncType(String name) {
        return parentScope.getFuncType(name);
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

    public Pair<classType, funcType> getIdentifier(String name) {
        classType vtype = members.get(name);
        if (vtype != null)
            return new Pair<>(vtype, null);
        if (parentScope != null && lookup)
            return parentScope.getIdentifier(name);
        return new Pair<>(null, null);
    }
}