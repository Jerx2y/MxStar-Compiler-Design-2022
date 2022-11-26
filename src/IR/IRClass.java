package IR;

import IR.IRType.IRType;

import java.util.ArrayList;

public class IRClass {
    String identifier;
    ArrayList<IRType> vars;
    ArrayList<IRFunction> funcs;

    public IRClass() {
        vars = new ArrayList<>();
        funcs = new ArrayList<>();
    }
}
