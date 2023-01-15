package IR;

import IR.Inst.*;

public interface IRVisitor {
    void visit(allocaInst it);
    void visit(binaryInst it);
    void visit(brInst it);
    void visit(callInst it);
    void visit(getelementptrInst it);
    void visit(globalInst it);
    void visit(icmpInst it);
    void visit(loadInst it);
    void visit(retInst it);
    void visit(storeInst it);
}
