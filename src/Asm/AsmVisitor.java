package Asm;


import Asm.Ins.*;

public interface AsmVisitor {

    void visit(AsmModule it);
    void visit(AsmFunction it);
    void visit(AsmBlock it);
    void visit(AsmData it);

    void visit(binaryInsSet it);
    void visit(brInsSet it);
    void visit(callIns it);
    void visit(cmpInsSet it);
    void visit(Ins it);
    void visit(jIns it);
    void visit(laIns it);
    void visit(liIns it);
    void visit(loadInsSet it);
    void visit(mvIns it);
    void visit(retIns it);
    void visit(storeInsSet it);
}
