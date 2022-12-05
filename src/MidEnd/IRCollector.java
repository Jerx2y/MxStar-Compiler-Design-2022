package MidEnd;

public class IRCollector implements ASTVisitor {

    globalScope gScope;
    Scope curScope;
    Module topModule;
    IRClass curClass;
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

    IRType getIRType(classType type) {
        IRType res = switch (type.classname) {
            case "int" -> new iIRType(32);
            case "bool" -> new iIRType(1);
            case "string" -> new ptrIRType(new iIRType(8));
            default -> new ptrIRType(new classIRType(gScope.irclasses.get(type.classname)));
        };

        for (int i = 0; i < type.dimension; ++i)
            res = new ptrIRType(res);

        return res;
    }


    public void visit(RootNode it) {

        for (classType c: gScope.getClasses().values()) {
            if (c.classname.equals("int") || c.classname.equals("bool")
                    || c.classname.equals("string")) continue;
            IRClass ic = new IRClass(c.classname);
            gScope.putIRClasses(c.classname, ic);
        }

        for (classType c: gScope.getClasses().values()) {
            if (c.classname.equals("int") || c.classname.equals("bool")
                    || c.classname.equals("string")) continue;
            IRClass ic = gScope.getIRClasses(c.classname);
            c.getVarsList().values().forEach(v -> ic.vars.add(getIRType(v)));
        }

        for (classType c: gScope.getClasses().values()) { // 只扫一遍会导致有些 classIRType 的 byte 不对
            if (c.classname.equals("int") || c.classname.equals("bool")
                    || c.classname.equals("string")) continue;
            IRClass ic = gScope.getIRClasses(c.classname);
            int i = 0;
            for (classType v : c.getVarsList().values())
                ic.vars.set(i++, getIRType(v));
        }

        it.defs.forEach(x -> x.accept(this));
    }

    public void visit(DefNode it) { }

    public void visit(classDefNode it) {
        curClass = new IRClass(it.identifier);

        if (it.constructor != null)
            it.constructor.accept(this);

        it.funcDefs.forEach(fd -> fd.accept(this));

        curClass = null;
    }

    public void visit(funcDefNode it) {
        funcType fType = curScope.getFuncType(it.identifier);
        curScope = new funcScope(fType, curScope);
        curFunction = new IRFunction(it.identifier);

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
                if (retEntity.type instanceof addrIRType)
                    retEntity = loadAddrType(retEntity);
                if (retEntity.isNULL())
                    retEntity.type = ((ptrIRType) regPtr.type).type;
                curBlock.addInst(new storeInst(regPtr, retEntity));
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
        Entity lhs = retEntity;

        it.rhs.accept(this);
        Entity rhs = retEntity;

        if (!(lhs.type instanceof addrIRType))
            throw new codegenError("[assign expression] lhs is not an address", it.pos);

        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        curBlock.addInst(new storeInst(lhs, rhs));
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
        retEntity = curScope.getEntity(it.identifier);
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
