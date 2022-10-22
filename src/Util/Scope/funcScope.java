package Util.Scope;

import Util.Type.funcType;

public class funcScope extends Scope {
    private final funcType info;

    public funcScope(funcType info, Scope parentScope) {
        super(parentScope);
        this.info = info;
    }

    @Override public funcType getThisFuncInfo() {
        return info;
    }
}