package MidEnd;

import IR.*;
import IR.Inst.*;

import java.io.PrintStream;

public class IRPrinter implements IRVisitor {

    PrintStream os;

    public IRPrinter(PrintStream os) {
        this.os = os;
    }

    @Override
    public void visit(IRModule irmodule) {
        irmodule.globals.forEach(i -> os.println(i));
        os.println();
        irmodule.classes.forEach(c -> c.accept(this));
        irmodule.functions.forEach(f -> {
            os.println();
            f.accept(this);
        });
    }

    @Override
    public void visit(IRClass irclass) {
        os.print("%" + irclass.identifier + " = type { ");
        for (int i = 0; i < irclass.vars.size(); ++i) {
            if (i > 0) os.print(", ");
            os.print(irclass.vars.get(i));
        }
        os.println(" }");
    }

    @Override
    public void visit(IRFunction irfunction) {
        os.print("define " + irfunction.retType + " @" + irfunction.identifier + "(");
        for (int i = 0; i < irfunction.paraEntity.size(); ++i) {
            if (i > 0) os.print(", ");
            os.print(irfunction.paraEntity.get(i));
        }
        os.println(") {");
        for (int i = 0; i < irfunction.blocks.size(); i++) {
            IRBlock b = irfunction.blocks.get(i);
            if (i > 0) os.println(b.name.getValue() + ":");
            b.accept(this);
            if (i != irfunction.blocks.size() - 1)
                os.println();
        }
        os.println("}");
    }

    @Override
    public void visit(IRBlock irblock) {
        irblock.instList.forEach(i -> os.println("    " + i));
        os.println("    " + irblock.terminator);
    }

    @Override public void visit(allocaInst it) { }

    @Override public void visit(binaryInst it) { }

    @Override public void visit(bitcastInst it) { }

    @Override public void visit(brInst it) { }

    @Override public void visit(callInst it) { }

    @Override public void visit(declareInst it) { }

    @Override public void visit(getelementptrInst it) { }

    @Override public void visit(globalInst it) { }

    @Override public void visit(icmpInst it) { }

    @Override public void visit(loadInst it) { }

    @Override public void visit(retInst it) { }

    @Override public void visit(storeInst it) { }
}
