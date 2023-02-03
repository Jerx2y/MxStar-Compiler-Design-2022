package BackEnd;

import Asm.*;
import Asm.Ins.*;
import Asm.Operand.imm;
import Asm.Operand.pReg;

import java.io.PrintStream;

public class AsmPrinter implements AsmVisitor {

    PrintStream os;

    AsmModule topModule;

    pReg sp, ra, s0, t0, t1;

    public AsmPrinter(PrintStream os) {
        this.os = os;
    }

    @Override
    public void visit(AsmModule it) {
        os.println("\t.text");
        os.println("\t.file\t\"test.ll\"\n");

        topModule = it;
        sp = topModule.sp;
        ra = topModule.ra;
        s0 = topModule.s.get(0);
        t0 = topModule.t.get(0);
        t1 = topModule.t.get(1);

        topModule.functions.forEach(f -> f.accept(this));
        topModule.data.forEach(d -> os.println(d));
    }

    @Override
    public void visit(AsmFunction it) {
        os.println("\t.globl\t" + it.name);
        os.println("\t.p2align\t2");
        os.println(it.name + ":");

        int stkSize = it.offset + it.callSize;
        if (stkSize % 16 != 0)
            stkSize = (stkSize / 16 + 1) * 16;
        AsmBlock exit = it.getExit();
        Ins terminal = exit.tail.prev;

        it.entry.addFront(new binaryInsSet("add", s0, sp, t0));
        it.entry.addFront(new storeInsSet(s0, t1, new imm(-8), 4));
        it.entry.addFront(new storeInsSet(ra, t1, new imm(-4), 4));
        it.entry.addFront(new binaryInsSet("add", t1, sp, t0));
        it.entry.addFront(new binaryInsSet("sub", sp, sp, t0));
        it.entry.addFront(new liIns(t0, new imm(stkSize)));

        exit.insertBefore(new liIns(t0, new imm(stkSize)), terminal);
        exit.insertBefore(new binaryInsSet("add", t1, sp, t0), terminal);
        exit.insertBefore(new loadInsSet(s0, t1, new imm(-8), 4), terminal);
        exit.insertBefore(new loadInsSet(ra, t1, new imm(-4), 4), terminal);
        exit.insertBefore(new binaryInsSet("add", sp, sp, t0), terminal);

        it.entry.accept(this);
        it.blocks.forEach(b -> {
            os.println(b.label + ":");
            b.accept(this);
        });

        os.println();
    }

    @Override
    public void visit(AsmBlock it) {
        for (Ins iter = it.head.next; iter != it.tail; iter = iter.next)
            os.println(iter);
    }

    @Override public void visit(AsmData it) { }

    @Override public void visit(binaryInsSet it) { }

    @Override public void visit(brInsSet it) { }

    @Override public void visit(callIns it) { }

    @Override public void visit(cmpInsSet it) { }

    @Override public void visit(Ins it) { }

    @Override public void visit(jIns it) { }

    @Override public void visit(laIns it) { }

    @Override public void visit(liIns it) { }

    @Override public void visit(loadInsSet it) { }

    @Override public void visit(mvIns it) { }

    @Override public void visit(retIns it) { }

    @Override public void visit(storeInsSet it) { }
}
