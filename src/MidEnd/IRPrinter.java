package MidEnd;

import IR.*;
import IR.Inst.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class IRPrinter implements IRVisitor {

    PrintStream os;

    public IRPrinter(String filename) throws FileNotFoundException {
        os = new PrintStream(new FileOutputStream(filename));
    }

    @Override
    public void visitIRModule(IRModule irmodule) {
        irmodule.globals.forEach(i -> os.println(i));
        os.println();
        irmodule.classes.forEach(this::visitIRClass);
        irmodule.functions.forEach(f -> {
            os.println();
            visitIRFunction(f);
        });
    }

    @Override
    public void visitIRClass(IRClass irclass) {
        os.print("%" + irclass.identifier + " = type { ");
        for (int i = 0; i < irclass.vars.size(); ++i) {
            if (i > 0) os.print(", ");
            os.print(irclass.vars.get(i));
        }
        os.println(" }");
    }

    @Override
    public void visitIRFunction(IRFunction irfunction) {
        os.print("define " + irfunction.retType + " @" + irfunction.identifier + "(");
        for (int i = 0; i < irfunction.paraEntity.size(); ++i) {
            if (i > 0) os.print(", ");
            os.print(irfunction.paraEntity.get(i));
        }
        os.println(") {");
        for (int i = 0; i < irfunction.blocks.size(); i++) {
            IRBlock b = irfunction.blocks.get(i);
            if (i > 0) os.println(b.name.getValue() + ":");
            visitIRBlock(b);
            if (i != irfunction.blocks.size() - 1)
                os.println();
        }
        os.println("}");
    }

    @Override
    public void visitIRBlock(IRBlock irblock) {
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
