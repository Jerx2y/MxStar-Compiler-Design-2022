package Asm.Ins;

import Asm.Operand.Operand;

public class binaryInsSet extends Ins {

    String type;
    Operand rd, rs1, rs2;
    boolean isIType;

    public binaryInsSet(String type, Operand rd, Operand rs1, Operand rs2) {
        this.type = type;
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        isIType = type.charAt(type.length() - 1) == 'I';
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void accept() {

    }
}
