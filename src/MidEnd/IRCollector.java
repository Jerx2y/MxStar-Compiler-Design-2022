package MidEnd;

import AST.ASTVisitor;
import AST.Def.*;
import AST.Expr.*;
import AST.RootNode;
import AST.Stmt.*;
import IR.Entity.Entity;
import IR.IRBlock;
import IR.IRClass;
import IR.IRFunction;
import IR.IRModule;
import IR.IRType.*;
import Util.Scope.Scope;
import Util.Scope.globalScope;
import Util.Type.classType;

import java.util.ArrayList;

public class IRCollector implements ASTVisitor {

    globalScope gScope;
    Scope curScope;
    IRModule topModule;
    IRClass curClass;
    IRFunction curFunction;
    IRBlock curBlock;
    Entity retEntity;

    public IRCollector(globalScope gScope, IRModule topModule) {
        this.gScope = gScope;
        this.topModule = topModule;
    }

    IRType getIRType(classType type) {
        IRType res = switch (type.classname) {
            case "int" -> new iIRType(32);
            case "bool" -> new iIRType(1);
            case "string" -> new ptrIRType(new iIRType(8));
            default -> new ptrIRType(new voidIRType());
        };

        for (int i = 0; i < type.dimension; ++i)
            res = new ptrIRType(res);

        return res;
    }

    @Override
    public void visit(RootNode it) {

        for (DefNode def : it.defs)
            if (def instanceof classDefNode)
                def.accept(this);

        for (DefNode def : it.defs)
            if (def instanceof funcDefNode)
                def.accept(this);

    }

    @Override
    public void visit(DefNode it) { }

    @Override
    public void visit(classDefNode it) {
        curClass = new IRClass(it.identifier);
        it.varDecs.forEach(vd -> vd.accept(this));


        for (varDefNode varDec : it.varDecs) {
            for (int i = 0; i < varDec.singleVarDefs.size(); i++) {
                curClass.vars.add(getIRType(new classType(varDec.singleVarDefs.get(i).typename, gScope)));
                curClass.offsets.put(varDec.singleVarDefs.get(i).identifier, i);
            }
        }

        it.funcDefs.forEach(fd -> fd.accept(this));
        gScope.putIRClasses(it.identifier, curClass);
        curClass = null;
    }

    @Override
    public void visit(funcDefNode it) {
        ;
    }

    @Override
    public void visit(singleVarDefNode it) { }

    @Override
    public void visit(varDefNode it) { }

    @Override
    public void visit(ExprNode it) { }

    @Override
    public void visit(arrayExprNode it) { }

    @Override
    public void visit(assignExprNode it) { }

    @Override
    public void visit(binaryExprNode it) { }

    @Override
    public void visit(cmpExprNode it) { }

    @Override
    public void visit(eqExprNode it) { }

    @Override
    public void visit(funcExprNode it) { }

    @Override
    public void visit(lambdaExprNode it) { }

    @Override
    public void visit(literalExprNode it) { }

    @Override
    public void visit(memberExprNode it) { }

    @Override
    public void visit(newExprNode it) { }

    @Override
    public void visit(unaryExprNode it) { }

    @Override
    public void visit(varExprNode it) { }

    @Override
    public void visit(StmtNode it) { }

    @Override
    public void visit(compoundStmtNode it) { }

    @Override
    public void visit(defStmtNode it) { }

    @Override
    public void visit(exprStmtNode it) { }

    @Override
    public void visit(flowStmtNode it) { }

    @Override
    public void visit(forStmtNode it) { }

    @Override
    public void visit(ifStmtNode it) { }

    @Override
    public void visit(returnStmtNode it) { }

    @Override
    public void visit(whileStmtNode it) { }
}
