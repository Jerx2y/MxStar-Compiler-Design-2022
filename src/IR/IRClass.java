package IR;

import IR.IRType.IRType;

import java.util.ArrayList;

public class IRClass {
    String identifier;
    public ArrayList<IRType> vars;
    public ArrayList<IRFunction> funcs;

    public IRClass(String identifier) {
        this.identifier = identifier;
        vars = new ArrayList<>();
        funcs = new ArrayList<>();
    }
}
