package IR;

import IR.Inst.Inst;

import java.util.ArrayList;

public class IRModule {
    public ArrayList<Inst> globals;
    public ArrayList<IRFunction> functions;
    public ArrayList<IRClass> classes;

    public IRModule() {
        globals = new ArrayList<>();
        functions = new ArrayList<>();
        classes = new ArrayList<>();
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}