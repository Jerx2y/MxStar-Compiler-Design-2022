package IR.Inst;

import AST.Expr.cmpExprNode;
import AST.Expr.eqExprNode;
import IR.Entity.Entity;
import IR.IRVisitor;

public class icmpInst extends Inst {
    enum icmpOpType {
        EQ, NE,
        SGT, SGE, SLT, SLE
    }

    Entity rd, rs1, rs2;
    icmpOpType opt;

    public icmpInst(Entity rd, cmpExprNode.cmpOpType opt, Entity rs1, Entity rs2) {
        this.opt = switch (opt) {
            case GR -> icmpOpType.SGT;
            case LE -> icmpOpType.SLT;
            case GEQ -> icmpOpType.SGE;
            case LEQ -> icmpOpType.SLE;
        };
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
    }

    public icmpInst(Entity rd, eqExprNode.eqOpType opt, Entity rs1, Entity rs2) {
        this.opt = switch (opt) {
            case EQ -> icmpOpType.EQ;
            case NE -> icmpOpType.NE;
        };
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;
    }

    @Override
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return rd.getValue() + " = icmp " + opt.name().toLowerCase() + " " + rs1 + ", " + rs2.getValue();
    }
}
