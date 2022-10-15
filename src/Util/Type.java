package Util;

import java.util.HashMap;

abstract public class Type {
    public enum BuiltinType{INT, BOOL, STRING, CLASS, VOID};
    public String className;
    int dimension = 0;
    public BuiltinType nowType;
    // public HashMap<String, Type> members = null;

    public Type(BuiltinType type) {
        this.nowType = type;
    }
}
