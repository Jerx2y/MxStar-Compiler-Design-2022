package FrontEnd;

import AST.Stmt.*;
import AST.Expr.*;
import AST.Def.*;
import AST.ASTVisitor;
import AST.RootNode;
import Util.Info.*;
import Util.Scope.globalScope;

public class SymbolCollector implements ASTVisitor {
    private globalScope gScope;
    public SymbolCollector(globalScope gScope) {
        this.gScope = gScope;
    }
    @Override
    public void visit(RootNode it) {
        it.classDefs.forEach(cd -> cd.accept(this));
        it.funcDefs.forEach(fd -> fd.accept(this));
    }

    public void visit(DefNode it) {}
    public void visit(classDefNode it) {
        classInfo ci = new classInfo();
        it.varDecs.forEach(vd -> vd.singleVarDefs.forEach(svd -> ci.vars.put(svd.identifier, svd.type)));
        for (funcDefNode fd : it.funcDefs) {
            funcInfo fi = new funcInfo();
            fi.ret = fd.type;
            fd.parameter.singleVarDefs.forEach(svd -> fi.para.add(svd.type));
            ci.funcs.put(fd.identifier, fi);
        }
        gScope.addClass(it.identifier, ci, it.pos);
    }
    public void visit(funcDefNode it) {
        funcInfo fi = new funcInfo();
        fi.ret = it.type;
        it.parameter.singleVarDefs.forEach(svd -> fi.para.add(svd.type));
        gScope.addFunc(it.identifier, fi, it.pos);
    }
    public void visit(singleVarDefNode it) {}
    public void visit(varDefNode it) {}

    public void visit(ExprNode it) {}
    public void visit(arrayExprNode it) {}
    public void visit(assignExprNode it) {}
    public void visit(binaryExprNode it) {}
    public void visit(cmpExprNode it) {}
    public void visit(eqExprNode it) {}
    public void visit(funcExprNode it) {}
    public void visit(lambdaExprNode it) {}
    public void visit(literalExprNode it) {}
    public void visit(memberExprNode it) {}
    public void visit(newExprNode it) {}
    public void visit(unaryExprNode it) {}
    public void visit(varExprNode it) {}

    public void visit(StmtNode it) {}
    public void visit(compoundStmtNode it) {}
    public void visit(defStmtNode it) {}
    public void visit(exprStmtNode it) {}
    public void visit(flowStmtNode it) {}
    public void visit(forStmtNode it) {}
    public void visit(ifStmtNode it) {}
    public void visit(returnStmtNode it) {}
    public void visit(whileStmtNode it) {}

}
