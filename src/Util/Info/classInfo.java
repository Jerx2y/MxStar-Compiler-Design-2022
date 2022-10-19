package Util.Info;

import Util.Type;

import java.util.HashMap;

public class classInfo {
    public HashMap<String, Type> vars;
    public HashMap<String, funcInfo> funcs;
    public classInfo() {
        vars = new HashMap<>();
        funcs = new HashMap<>();
    }
}
