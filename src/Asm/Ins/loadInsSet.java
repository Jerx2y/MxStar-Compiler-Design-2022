package Asm.Ins;

import Asm.AsmVisitor;
import Asm.Operand.Operand;

import java.util.Spliterator;

public class loadInsSet extends Ins {

    String type;
    public Operand rd, rs, imm;
    String symbol;

    public loadInsSet(Operand rd, Operand rs, Operand imm, int bytes) {
        type = switch (bytes) {
            case 1 -> "lb";
            case 2 -> "lh";
            default -> "lw";
        };
        this.rd = rd;
        this.rs = rs;
        this.imm = imm;
        symbol = null;
    }

    public loadInsSet(Operand rd, String symbol, int bytes) {
        type = switch (bytes) {
            case 1 -> "lb";
            case 2 -> "lh";
            default -> "lw";
        };
        this.rd = rd;
        this.symbol = symbol;
        this.rs = null;
        this.imm = null;
    }

    @Override
    public String toString() {
        String str = "\t" + type + "\t" + rd + ", ";
        if (rs == null) str += symbol;
        else str += imm + "(" + rs + ")";
        return str;
    }

    @Override
    public void accept(AsmVisitor visitor) {
        visitor.visit(this);
    }
}
