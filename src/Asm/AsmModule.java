package Asm;

import Asm.Operand.pReg;

import java.util.ArrayList;

public class AsmModule {
    public pReg zero, ra, sp, gp, tp;
    public ArrayList<pReg> t, s, a;

    public ArrayList<AsmData> data;

    public ArrayList<AsmFunction> functions;

    public AsmModule() {
        t = new ArrayList<>();
        s = new ArrayList<>();
        a = new ArrayList<>();
        data = new ArrayList<>();
        functions = new ArrayList<>();

        zero = new pReg("zero");
        ra = new pReg("ra");
        sp = new pReg("sp");
        gp = new pReg("gp");
        tp = new pReg("tp");
        for (int i = 0; i <= 6; ++i)
            t.add(new pReg("t" + i));
        for (int i = 0; i <= 11; ++i)
            s.add(new pReg("s" + i));
        for (int i = 0; i <= 7; ++i)
            a.add(new pReg("a" + i));

    }

    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
