package IR;

import IR.Inst.Inst;

import java.util.ArrayList;

public class IRModule {
    public ArrayList<Inst> gvars;
    public ArrayList<IRFunction> funcs;
    public ArrayList<IRClass> classes;

    IRModule() {
        gvars = new ArrayList<>();
        funcs = new ArrayList<>();
        classes = new ArrayList<>();
    }

}