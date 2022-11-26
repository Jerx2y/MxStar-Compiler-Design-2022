package MidEnd;

import AST.ASTVisitor;
import AST.*;
import AST.Def.*;
import AST.Expr.*;
import AST.Stmt.*;
import IR.Entity.*;
import IR.IRBlock;
import IR.Inst.*;
import IR.IRType.*;
import IR.IRFunction;
import Util.Scope.globalScope;
import Util.Type.classType;

import javax.xml.stream.events.EntityReference;

public class IRBuilder implements ASTVisitor {

    globalScope gScope, curScope;
    Module topModule;
    IRFunction curFunction;
    IRBlock curBlock;
    Entity retEntity;

    IRBuilder(globalScope gScope, Module topModule) {
        this.gScope = gScope;
        this.topModule = topModule;
    }

    Entity loadAddrType(Entity ptr) {
        Entity res = new register(((addrIRType) ptr.type).getPtrType(), curFunction.getRegId());
        curBlock.addInst(new loadInst(res, ptr));
        return res;
    }



    public void visit(RootNode it) {
        it.defs.forEach(x -> x.accept(this));
    }

    public void visit(DefNode it) { }

    public void visit(classDefNode it) {
        ;
    }

    public void visit(funcDefNode it) {
        ;
    }

    public void visit(singleVarDefNode it) {
        IRType type = IRType.fromClassType(new classType(it.typename, gScope));
        if (curScope == gScope) {
            ;
        } else {
            register regPtr = new register(new addrIRType(type), curFunction.getRegId());
            curBlock.addInst(new allocaInst(regPtr, type));
            curScope.addEntity(it.identifier, regPtr);
            if (it.initExpr != null) {
                it.initExpr.accept(this);
                // TODO
            }
        }
    }

    public void visit(varDefNode it) {
        it.singleVarDefs.forEach(svd -> svd.accept(this));
    }

    public void visit(ExprNode it) { }

    public void visit(arrayExprNode it) { }

    public void visit(assignExprNode it) {
        ;
    }

    public void visit(binaryExprNode it) {

        it.lhs.accept(this);
        Entity lhs = retEntity;

        it.rhs.accept(this);
        Entity rhs = retEntity;

        if (lhs.type instanceof addrIRType)
            lhs = loadAddrType(lhs);
        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        if (lhs.isStr() && rhs.isStr()) {
            // TODO
            return ;
        }

        retEntity = new register(lhs.type, curFunction.getRegId());
        curBlock.addInst(new binaryInst(it.opCode, retEntity, lhs, rhs));
    }

    public void visit(cmpExprNode it) {

        it.lhs.accept(this);
        Entity lhs = retEntity;

        it.rhs.accept(this);
        Entity rhs = retEntity;

        if (lhs.type instanceof addrIRType)
            lhs = loadAddrType(lhs);
        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        if (lhs.isStr() && rhs.isStr()) {
            // TODO

        }

        retEntity = new register(new iIRType(1), curFunction.getRegId());
        curBlock.addInst(new icmpInst(retEntity, it.opCode, lhs, rhs));
    }

    public void visit(eqExprNode it) {
        it.lhs.accept(this);
        Entity lhs = retEntity;

        it.rhs.accept(this);
        Entity rhs = retEntity;

        if (lhs.type instanceof addrIRType)
            lhs = loadAddrType(lhs);
        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        if (lhs.isNULL())
            lhs.type = rhs.type;
        if (rhs.isNULL())
            rhs.type = lhs.type;

        if (lhs.isStr() && rhs.isStr()) {
            // TODO
        }

        retEntity = new register(new iIRType(1), curFunction.getRegId());
        curBlock.addInst(new icmpInst(retEntity, it.opt, lhs, rhs));
    }

    public void visit(funcExprNode it) {
        ;
    }

    public void visit(lambdaExprNode it) { }

    public void visit(literalExprNode it) {
        assert (it.type instanceof classType);
        retEntity = switch (((classType) it.type).classname) {
            case "int" -> new constant(new iIRType(32), it.intVal);
            case "bool" -> new constant(new iIRType(1), it.boolVal);
            case "string" -> new constant(new ptrIRType(new iIRType(8)), it.stringVal);
            case "null" -> new constant(new ptrIRType(new voidIRType()), constant.constantType.NULL);
        };
    }

    public void visit(memberExprNode it) { }

    public void visit(newExprNode it) { }

    public void visit(unaryExprNode it) {
        it.expr.accept(this);

        Entity ptr = retEntity, lhs;
        if (ptr.type instanceof addrIRType)
            lhs = loadAddrType(ptr);
        else lhs = ptr;

        switch (it.opCode) {
            case NOT, TILDE -> {
                if (!(lhs instanceof constant)) {
                    retEntity = new register(lhs.type, curFunction.getRegId());
                    Entity rhs = it.opCode == unaryExprNode.unaryOpType.NOT ?
                            new constant(lhs.type, true) : new constant(lhs.type, -1);
                    curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.XOR, retEntity, lhs, rhs));
                } else retEntity = ((constant) lhs).getNot();
            }
            case SUB -> {
                if (!(lhs instanceof constant)) {
                    retEntity = new register(lhs.type, curFunction.getRegId());
                    Entity rhs = new constant(lhs.type, 0);
                    curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.SUB, retEntity, rhs, lhs));
                } else retEntity = ((constant) lhs).getNeg();
            }
            case DOUBLE_ADD, DOUBLE_SUB -> {
                Entity rhs = new constant(lhs.type, 1);
                retEntity = new register(lhs.type, curFunction.getRegId());
                curBlock.addInst(new binaryInst(it.opCode == unaryExprNode.unaryOpType.DOUBLE_ADD ?
                        binaryExprNode.binaryOpType.ADD : binaryExprNode.binaryOpType.SUB, retEntity, lhs, rhs));
                assert (ptr.type instanceof addrIRType);
                curBlock.addInst(new storeInst(ptr, retEntity));
            }
            case R_DOUBLE_ADD, R_DOUBLE_SUB -> {
                Entity rhs = new constant(lhs.type, 1);
                Entity res = new register(lhs.type, curFunction.getRegId());
                curBlock.addInst(new binaryInst(it.opCode == unaryExprNode.unaryOpType.R_DOUBLE_ADD ?
                        binaryExprNode.binaryOpType.ADD : binaryExprNode.binaryOpType.SUB, res, lhs, rhs));
                assert (ptr.type instanceof addrIRType);
                curBlock.addInst(new storeInst(ptr, res));
                retEntity = lhs;
            }
        }
    }

    public void visit(varExprNode it) {
        ;
    }


    public void visit(StmtNode it) { }

    public void visit(compoundStmtNode it) { }

    public void visit(defStmtNode it) { }

    public void visit(exprStmtNode it) { }

    public void visit(flowStmtNode it) { }

    public void visit(forStmtNode it) { }

    public void visit(ifStmtNode it) { }

    public void visit(returnStmtNode it) { }

    public void visit(whileStmtNode it) { }

}
