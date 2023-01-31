package IR;

import IR.Inst.*;

public interface IRVisitor {
    void visitIRModule(IRModule irmodule);
    void visitIRFunction(IRFunction irfunction);
    void visitIRClass(IRClass irclass);
    void visitIRBlock(IRBlock irblock);
    void visitInst(Inst inst);

    void visit(allocaInst it);
    void visit(binaryInst it);
    void visit(bitcastInst it);
    void visit(brInst it);
    void visit(callInst it);
    void visit(declareInst it);
    void visit(getelementptrInst it);
    void visit(globalInst it);
    void visit(icmpInst it);
    void visit(loadInst it);
    void visit(phiInst it);
    void visit(retInst it);
    void visit(storeInst it);
}
