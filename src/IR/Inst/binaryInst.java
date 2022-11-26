package IR.Inst;

import AST.Expr.binaryExprNode;
import IR.Entity.Entity;
import IR.IRVisitor;

public class binaryInst extends Inst {

    public enum binaryOpType {
        ADD, SUB, MUL, UDIV, SDIV, UREM, SREM,
        SHL, LSHR, ASHR, AND, OR, XOR
    }

    public Entity rd, rs1, rs2;
    public boolean nuw, nsw;
    public binaryOpType opt;

    public binaryInst(binaryExprNode.binaryOpType opt, Entity rd, Entity rs1, Entity rs2) {
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.opt =switch (opt) {
            case ADD -> binaryOpType.ADD;
            case SUB -> binaryOpType.SUB;
            case MUL -> binaryOpType.MUL;
            case DIV -> binaryOpType.SDIV;
            case MOD -> binaryOpType.SREM;
            case DOUBLE_AND, AND -> binaryOpType.AND;
            case DOUBLE_OR , OR -> binaryOpType.OR;
            case XOR -> binaryOpType.XOR;
            case DOUBLE_L -> binaryOpType.SHL;
            case DOUBLE_R -> binaryOpType.ASHR;
        };
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String res = rd.getValue() + " = " + opt.name().toLowerCase();
        if (nsw) res += " nsw";
        if (nuw) res += " nuw";
        res += " " + rs1 + ", " + rs2.getValue();
        return res;
    }
}
