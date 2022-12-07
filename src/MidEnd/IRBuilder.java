package MidEnd;

import AST.ASTVisitor;
import AST.*;
import AST.Def.*;
import AST.Expr.*;
import AST.Stmt.*;
import IR.Entity.*;
import IR.IRBlock;
import IR.IRClass;
import IR.Inst.*;
import IR.IRType.*;
import IR.IRFunction;
import Util.Scope.*;
import Util.Type.*;
import Util.error.codegenError;

import java.util.ArrayList;
import java.util.HashMap;

public class IRBuilder implements ASTVisitor {

    globalScope gScope;
    Module topModule;

    Scope curScope = null;
    IRClass curClass = null;
    Entity curClassEntity = null;
    IRFunction curFunction = null;
    IRBlock curBlock = null;

    IRBuilder(globalScope gScope, Module topModule) {
        this.gScope = gScope;
        this.topModule = topModule;
    }

    Entity loadAddrType(Entity ptr) {
        Entity res = new register(((addrIRType) ptr.type).getPtrType(), curFunction.getRegId());
        curBlock.addInst(new loadInst(res, ptr));
        return res;
    }

    IRType getIRType(classType type) {
        IRType res = switch (type.classname) {
            case "int" -> new iIRType(32);
            case "bool" -> new iIRType(1);
            case "string" -> new ptrIRType(new iIRType(8));
            default -> new ptrIRType(new classIRType(gScope.getIRClasses(type.classname)));
        };

        for (int i = 0; i < type.dimension; ++i)
            res = new ptrIRType(res);

        return res;
    }


    public void visit(RootNode it) {
        it.defs.forEach(x -> x.accept(this));
    }

    public void visit(DefNode it) { }

    public void visit(classDefNode it) {

        curClass = new IRClass(it.identifier);
        curScope = new classScope(gScope.getClassType(it.identifier), curScope);

        for (varDefNode vd : it.varDecs)
            for (singleVarDefNode svd : vd.singleVarDefs)
                curClass.vars.add(getIRType(new classType(svd.typename, gScope)));


        if (it.constructor != null) {
            it.constructor.accept(this);
            curClass.constructor = true;
        }

        gScope.putIRClasses(it.identifier, curClass);

        it.funcDefs.forEach(fd -> fd.accept(this));

        curScope = curScope.parentScope();
        curClass = null;
    }

    public void visit(funcDefNode it) {
        funcType fType = curScope.getFuncType(it.identifier);
        curScope = new funcScope(fType, curScope);

        curFunction = new IRFunction((curClass != null ? curClass.identifier + "." : "") + it.identifier);

        if (curClass != null)
            curFunction.paraEntity.add(new register(new ptrIRType(new classIRType(curClass)), curFunction.getRegId()));
        fType.para.forEach(type -> curFunction.paraEntity.add(new register(getIRType(type), curFunction.getRegId())));

        curFunction.retType = getIRType(fType.ret);

        curFunction.buildEntry();

        curBlock = curFunction.entry;

        for (int i = (curClass != null) ? 1 : 0; i < curFunction.paraEntity.size(); i++) {
            Entity val = curFunction.paraEntity.get(i);
            Entity ptr = new register(new addrIRType(val.type), curFunction.getRegId());
            curBlock.addInst(new allocaInst(ptr, val.type));
            curBlock.addInst(new storeInst(ptr, val));
        }

        it.stmts.forEach(s -> s.accept(this));

        // TODO:
        // 所有 return 语句相当于将值存在 retEntity 中，并跳转到 retBlock。

        curScope = curScope.parentScope();
    }

    public void visit(singleVarDefNode it) {
        IRType type = getIRType(new classType(it.typename, gScope));
        if (curScope instanceof globalScope) {
            // TODO: global variables
        } else {
            register regPtr = new register(new addrIRType(type), curFunction.getRegId());
            curBlock.addInst(new allocaInst(regPtr, type));
            curScope.addEntity(it.identifier, regPtr);
            if (it.initExpr != null) {
                it.initExpr.accept(this);
                Entity reg = it.initExpr.entity;
                if (reg.type instanceof addrIRType)
                    reg = loadAddrType(reg);
                if (reg.isNULL())
                    reg.type = ((ptrIRType) regPtr.type).type;
                curBlock.addInst(new storeInst(regPtr, reg));
            }
        }
    }

    public void visit(varDefNode it) {
        it.singleVarDefs.forEach(svd -> svd.accept(this));
    }

    public void visit(ExprNode it) { }

    public void visit(arrayExprNode it) { }

    public void visit(assignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);

        Entity lhs = it.lhs.entity, rhs = it.rhs.entity;
        if (!(lhs.type instanceof addrIRType))
            throw new codegenError("[assign expression] lhs is not an address", it.pos);

        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        curBlock.addInst(new storeInst(lhs, rhs));
    }

    public void visit(binaryExprNode it) {

        it.lhs.accept(this);
        Entity lhs = it.lhs.entity;

        it.rhs.accept(this);
        Entity rhs = it.rhs.entity;

        if (lhs.type instanceof addrIRType)
            lhs = loadAddrType(lhs);
        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        if (lhs.isStr() && rhs.isStr()) {
            // TODO
            return ;
        }

        Entity reg = new register(lhs.type, curFunction.getRegId());
        curBlock.addInst(new binaryInst(it.opCode, reg, lhs, rhs));
        it.entity = reg;
    }

    public void visit(cmpExprNode it) {

        it.lhs.accept(this);
        Entity lhs = it.lhs.entity;

        it.rhs.accept(this);
        Entity rhs = it.rhs.entity;

        if (lhs.type instanceof addrIRType)
            lhs = loadAddrType(lhs);
        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        if (lhs.isStr() && rhs.isStr()) {
            // TODO

        }

        Entity reg = new register(new iIRType(1), curFunction.getRegId());
        curBlock.addInst(new icmpInst(reg, it.opCode, lhs, rhs));
        it.entity = reg;
    }

    public void visit(eqExprNode it) {
        it.lhs.accept(this);
        Entity lhs = it.lhs.entity;

        it.rhs.accept(this);
        Entity rhs = it.rhs.entity;

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

        Entity reg = new register(new iIRType(1), curFunction.getRegId());
        curBlock.addInst(new icmpInst(reg, it.opt, lhs, rhs));
        it.entity = reg;
    }

    public void visit(funcExprNode it) {
        String name;
        funcType fType;
        ArrayList<Entity> para = new ArrayList<>();

        if (it.caller instanceof varExprNode) {
            fType = curScope.getFuncType(((varExprNode) it.caller).identifier);
            if (curClass != null && fType != null) {
                Entity classPtr = curClassEntity;
                if (classPtr.type instanceof addrIRType)
                    classPtr = loadAddrType(classPtr);
                name = curClass.identifier + "." + ((varExprNode) it.caller).identifier;
                para.add(classPtr);
            } else {
                fType = gScope.getFuncType(((varExprNode) it.caller).identifier);
                name = ((varExprNode) it.caller).identifier;
            }
        } else if (it.caller instanceof memberExprNode) {
            ((memberExprNode) it.caller).caller.accept(this);

            Entity callerEntity = ((memberExprNode) it.caller).caller.entity;
            if (callerEntity.type instanceof addrIRType)
                callerEntity = loadAddrType(callerEntity);

            assert callerEntity.type instanceof ptrIRType;
            IRType cType = ((ptrIRType) callerEntity.type).type;
            assert cType instanceof classIRType;

            String className = ((classIRType) cType).getIdentifier();

            fType = gScope.getClassType(className).getFunc(((memberExprNode) it.caller).member);

            name = className + "." + ((memberExprNode) it.caller).member;
            para.add(callerEntity);
        } else throw new codegenError("[function expression]", it.pos);

        Entity ret;
        if (!fType.ret.isType("void")) {
            ret = new register(getIRType(fType.ret), curFunction.getRegId());
            curBlock.addInst(new callInst(ret, getIRType(fType.ret), name, para));
        } else {
            ret = null;
            curBlock.addInst(new callInst(name, para));
        }

        it.entity = ret;

    }

    public void visit(lambdaExprNode it) { }

    public void visit(literalExprNode it) {
        assert (it.type instanceof classType);
        it.entity = switch (((classType) it.type).classname) {
            case "int" -> new constant(new iIRType(32), it.intVal);
            case "bool" -> new constant(new iIRType(1), it.boolVal);
            case "string" -> new constant(new ptrIRType(new iIRType(8)), it.stringVal);
            case "null" -> new constant(new ptrIRType(new voidIRType()), constant.constantType.NULL);
        };
    }

    public void visit(memberExprNode it) {
        // as func condition is considered in FuncExprNode, we can only consider var or class condition;
        it.caller.accept(this);

        Entity classptr;
        if (it.caller.entity.type instanceof addrIRType)
            classptr = loadAddrType(it.caller.entity);
        else classptr = it.caller.entity;

        assert classptr.type instanceof ptrIRType;
        assert ((ptrIRType) classptr.type).type instanceof classIRType;
        classIRType cType = (classIRType) ((ptrIRType) classptr.type).type;

        curBlock.addInst(getelementptrInst());

        // maybe not hard
        // gep is not ok.
    }

    public void visit(newExprNode it) {
        //
    }

    public void visit(unaryExprNode it) {
        it.expr.accept(this);

        Entity ptr = it.expr.entity, lhs;
        if (ptr.type instanceof addrIRType)
            lhs = loadAddrType(ptr);
        else lhs = ptr;

        Entity ret;
        switch (it.opCode) {
            case NOT, TILDE -> {
                if (!(lhs instanceof constant)) {
                    ret = new register(lhs.type, curFunction.getRegId());
                    Entity rhs = it.opCode == unaryExprNode.unaryOpType.NOT ?
                            new constant(lhs.type, true) : new constant(lhs.type, -1);
                    curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.XOR, ret, lhs, rhs));
                } else ret = ((constant) lhs).getNot();
            }
            case SUB -> {
                if (!(lhs instanceof constant)) {
                    ret = new register(lhs.type, curFunction.getRegId());
                    Entity rhs = new constant(lhs.type, 0);
                    curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.SUB, ret, rhs, lhs));
                } else ret = ((constant) lhs).getNeg();
            }
            case DOUBLE_ADD, DOUBLE_SUB -> {
                Entity rhs = new constant(lhs.type, 1);
                ret = new register(lhs.type, curFunction.getRegId());
                curBlock.addInst(new binaryInst(it.opCode == unaryExprNode.unaryOpType.DOUBLE_ADD ?
                        binaryExprNode.binaryOpType.ADD : binaryExprNode.binaryOpType.SUB, ret, lhs, rhs));
                assert (ptr.type instanceof addrIRType);
                curBlock.addInst(new storeInst(ptr, ret));
            }
            case R_DOUBLE_ADD, R_DOUBLE_SUB -> {
                Entity rhs = new constant(lhs.type, 1);
                Entity res = new register(lhs.type, curFunction.getRegId());
                curBlock.addInst(new binaryInst(it.opCode == unaryExprNode.unaryOpType.R_DOUBLE_ADD ?
                        binaryExprNode.binaryOpType.ADD : binaryExprNode.binaryOpType.SUB, res, lhs, rhs));
                assert (ptr.type instanceof addrIRType);
                curBlock.addInst(new storeInst(ptr, res));
                ret = lhs;
            }
            default -> {
                assert false;
                ret = null;
            }
        }
        it.entity = ret;
    }

    public void visit(varExprNode it) {
        it.entity = curScope.getEntity(it.identifier);
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
