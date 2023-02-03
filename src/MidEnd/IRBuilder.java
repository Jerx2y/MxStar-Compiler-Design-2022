package MidEnd;

import AST.ASTVisitor;
import AST.*;
import AST.Def.*;
import AST.Expr.*;
import AST.Stmt.*;
import IR.Entity.*;
import IR.IRBlock;
import IR.IRClass;
import IR.IRModule;
import IR.Inst.*;
import IR.IRType.*;
import IR.IRFunction;
import Util.Scope.*;
import Util.Type.*;
import Util.error.codegenError;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor {

    globalScope gScope;
    IRModule topModule;

    Scope curScope = null;
    IRClass curClass = null;
    IRFunction curFunction = null;
    IRBlock curBlock = null;

    IRBlock curBeginBlock = null;
    IRBlock curEndBlock = null;

    int strNum = 0;

    IRFunction globalVarInit, malloc;

    IRType i1Type, i8Type, i32Type, voidType, strType;


    public IRBuilder(globalScope gScope, IRModule topModule) {
        this.gScope = gScope;
        this.topModule = topModule;
        declareBuiltin();
    }

    void declareBuiltin() {
        assert topModule != null;

        i1Type = new iIRType(1);
        i8Type = new iIRType(8);
        i32Type = new iIRType(32);
        voidType = new voidIRType();
        strType = new ptrIRType(i8Type);

        // seems to be needless
        malloc = new IRFunction("malloc", 1, strType, new register(i32Type, "0"));
        topModule.globals.add(new declareInst(malloc));

        IRFunction print = new IRFunction("print", 1, voidType, new register(strType, "0"));
        IRFunction println = new IRFunction("println", 1, voidType, new register(strType, "0"));
        IRFunction printInt = new IRFunction("printInt", 1, voidType, new register(i32Type, "0"));
        IRFunction printlnInt = new IRFunction("printlnInt", 1, voidType, new register(i32Type, "0"));
        IRFunction getString = new IRFunction("getString", 0, strType);
        IRFunction getInt = new IRFunction("getInt", 0, i32Type);
        IRFunction toString = new IRFunction("toString", 1, strType, new register(i32Type, "0"));
        topModule.globals.add(new declareInst(print));
        topModule.globals.add(new declareInst(println));
        topModule.globals.add(new declareInst(printInt));
        topModule.globals.add(new declareInst(printlnInt));
        topModule.globals.add(new declareInst(getString));
        topModule.globals.add(new declareInst(getInt));
        topModule.globals.add(new declareInst(toString));

        IRFunction array_size = new IRFunction("__array_size", 1, i32Type, new register(strType, "0"));
        topModule.globals.add(new declareInst(array_size));

        IRFunction string_length = new IRFunction("__string_length", 1, i32Type, new register(strType, "0"));
        IRFunction string_substring = new IRFunction("__string_substring", 3, strType,
                new register(strType, "0"), new register(i32Type, "1"), new register(i32Type, "2"));
        IRFunction string_parseInt = new IRFunction("__string_parseInt", 1, i32Type, new register(strType, "0"));
        IRFunction string_ord = new IRFunction("__string_ord", 2, i32Type, new register(strType, "0"), new register(i32Type, "1'"));
        topModule.globals.add(new declareInst(string_length));
        topModule.globals.add(new declareInst(string_substring));
        topModule.globals.add(new declareInst(string_parseInt));
        topModule.globals.add(new declareInst(string_ord));

        IRFunction string_add = new IRFunction("__string_add", 2, strType, new register(strType, "0"), new register(strType, "1"));
        IRFunction string_eq = new IRFunction("__string_eq", 2, i1Type, new register(strType, "0"), new register(strType, "1"));
        IRFunction string_ne = new IRFunction("__string_ne", 2, i1Type, new register(strType, "0"), new register(strType, "1"));
        IRFunction string_lt = new IRFunction("__string_lt", 2, i1Type, new register(strType, "0"), new register(strType, "1"));
        IRFunction string_le = new IRFunction("__string_le", 2, i1Type, new register(strType, "0"), new register(strType, "1"));
        IRFunction string_gt = new IRFunction("__string_gt", 2, i1Type, new register(strType, "0"), new register(strType, "1"));
        IRFunction string_ge = new IRFunction("__string_ge", 2, i1Type, new register(strType, "0"), new register(strType, "1"));
        topModule.globals.add(new declareInst(string_add));
        topModule.globals.add(new declareInst(string_eq));
        topModule.globals.add(new declareInst(string_ne));
        topModule.globals.add(new declareInst(string_lt));
        topModule.globals.add(new declareInst(string_le));
        topModule.globals.add(new declareInst(string_gt));
        topModule.globals.add(new declareInst(string_ge));
    }

    Entity loadAddrType(Entity ptr) {
        Entity res = new register(((addrIRType) ptr.type).getPtrType(), curFunction.getRegId());
        curBlock.addInst(new loadInst(res, ptr));
        return res;
    }

    IRType getIRType(classType type) {
        IRType res = switch (type.classname) {
            case "int" -> i32Type;
            case "bool" -> i1Type;
            case "string" -> new ptrIRType(new iIRType(8));
            case "void" -> voidType;
            default -> new ptrIRType(new classIRType(gScope.getIRClasses(type.classname)));
        };

        for (int i = 0; i < type.dimension; ++i)
            res = new ptrIRType(res);

        return res;
    }


    public void visit(RootNode it) {
        globalVarInit = new IRFunction("__cxx_global_var_init", 0, voidType);
        topModule.functions.add(globalVarInit);
        globalVarInit.entry = new IRBlock(globalVarInit);
        curScope = gScope;

        it.defs.forEach(x -> x.accept(this));

        globalVarInit.entry.addInst(new retInst(new constant(new voidIRType())));
        globalVarInit.blocks.add(globalVarInit.entry);
    }

    public void visit(DefNode it) { }

    void visitConstructor(classDefNode it) {
        curScope = new Scope(curScope);

        curClass = gScope.getIRClasses(it.identifier);

        curFunction = new IRFunction("class." + it.identifier + "." + it.identifier, 1, voidType, new register(new ptrIRType(new classIRType(curClass)), "0"));
        curBlock = curFunction.entry = new IRBlock(curFunction);

        topModule.functions.add(curFunction);

        it.constructor.accept(this);

        curFunction.blocks.add(curBlock);

        Entity retReg = new constant(curFunction.retType);
        curBlock.addInst(new retInst(retReg));

        curScope = curScope.parentScope();
    }

    public void visit(classDefNode it) {

        curClass = gScope.getIRClasses(it.identifier);
        curScope = new classScope(gScope.getClassType(it.identifier), curScope);

        topModule.classes.add(curClass);

        curClass.vars = new ArrayList<>();
        for (varDefNode vd : it.varDecs)
            for (singleVarDefNode svd : vd.singleVarDefs) {
                curClass.vars.add(getIRType(new classType(svd.typename, gScope)));
                curScope.addEntity(svd.identifier, new label("__class_var"));
            }


        if (it.constructor != null) {
            curClass.constructor = true;
            visitConstructor(it);
        }

        gScope.putIRClasses(it.identifier, curClass);

        it.funcDefs.forEach(fd -> fd.accept(this));

        curScope = curScope.parentScope();
        curClass = null;
    }

    public void visit(funcDefNode it) {

        // TODO: Check Scope add and topModule.functions

        funcType fType = curScope.getFuncType(it.identifier);
        curScope = new funcScope(fType, curScope);

        curFunction = new IRFunction((curClass != null ? curClass.identifier + "." : "") + it.identifier);
        topModule.functions.add(curFunction);

        curFunction.retType = getIRType(fType.ret);

        curFunction.initialBuild(it.identifier.equals("main") && curScope.parentScope() == gScope);
        curBlock = curFunction.entry;

        if (curClass != null)
            curFunction.paraEntity.add(new register(new ptrIRType(new classIRType(curClass)), curFunction.getRegId()));

        for (int i = 0; i < fType.para.size(); ++i) {
            Entity val = new register(getIRType(fType.para.get(i)), curFunction.getRegId());
            Entity ptr = new register(new addrIRType(val.type), curFunction.getRegId());
            curFunction.paraEntity.add(val);
            curBlock.addInst(new allocaInst(ptr, val.type));
            curBlock.addInst(new storeInst(ptr, val));
            curScope.addEntity(it.parameter.singleVarDefs.get(i).identifier, ptr);
        }

        it.stmts.forEach(s -> s.accept(this));

        curScope = curScope.parentScope();

        if (!curBlock.br) {
            if (it.retype != null) {
                Entity initRet = new constant(curFunction.retType);
                curBlock.addInst(new storeInst(curFunction.retEntity, initRet));
            }
            curFunction.retBlocks.add(curBlock);
        }

        curFunction.blocks.add(curBlock);

        curBlock = new IRBlock(curFunction);

        curFunction.retBlocks.forEach(b -> b.addTerminatorInst(new brInst(curBlock.name)));

        if (it.retype != null)
            curBlock.addInst(new retInst(loadAddrType(curFunction.retEntity)));
        else curBlock.addInst(new retInst(new constant(new voidIRType())));

        curFunction.blocks.add(curBlock);

        curFunction = null;

    }

    public void visit(singleVarDefNode it) {
        IRType type = getIRType(new classType(it.typename, gScope));
        Entity regPtr;

        if (curScope instanceof globalScope) {
            regPtr = new global(new addrIRType(type), it.identifier);
            topModule.globals.add(new globalInst(regPtr, new constant(type), globalInst.defineType.GLOBAL));
            if (it.initExpr != null) {
                curFunction = globalVarInit;
                curBlock = globalVarInit.entry;
            }
        } else {
            regPtr = new register(new addrIRType(type), curFunction.getRegId());
            curBlock.addInst(new allocaInst(regPtr, type));
        }

        curScope.addEntity(it.identifier, regPtr);

        if (it.initExpr != null) {
            it.initExpr.accept(this);
            Entity reg = it.initExpr.entity;
            if (reg.type instanceof addrIRType)
                reg = loadAddrType(reg);
            if (reg.isNULL())
                reg.type = ((addrIRType) regPtr.type).type;
            curBlock.addInst(new storeInst(regPtr, reg));

            if (curScope instanceof globalScope) {
                globalVarInit.entry = curBlock;
                curFunction = null;
            }
        }
    }

    public void visit(varDefNode it) {
        it.singleVarDefs.forEach(svd -> svd.accept(this));
    }

    public void visit(ExprNode it) { }

    public void visit(arrayExprNode it) {
        it.caller.accept(this);
        Entity caller = it.caller.entity;
        if (caller.type instanceof addrIRType)
            caller = loadAddrType(caller);

        it.index.accept(this);
        Entity index = it.index.entity;
        if (index.type instanceof addrIRType)
            index = loadAddrType(index);

        assert caller.type instanceof ptrIRType;
        IRType type = ((ptrIRType) caller.type).type;
        Entity res = new register(new addrIRType(type), curFunction.getRegId());

        getelementptrInst inst = new getelementptrInst(res, caller, type);
        inst.idx.add(index);
        curBlock.addInst(inst);

        it.entity = res;
    }

    public void visit(assignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);

        Entity lhs = it.lhs.entity, rhs = it.rhs.entity;


        if (!(lhs.type instanceof addrIRType)) {
            throw new codegenError("[assign expression] lhs is not an address", it.pos);
        }

        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        curBlock.addInst(new storeInst(lhs, rhs));
    }

    public void visit(binaryExprNode it) {

        // TODO: Short way get value

        it.lhs.accept(this);
        Entity lhs = it.lhs.entity;

        it.rhs.accept(this);
        Entity rhs = it.rhs.entity;

        if (lhs.type instanceof addrIRType)
            lhs = loadAddrType(lhs);
        if (rhs.type instanceof addrIRType)
            rhs = loadAddrType(rhs);

        Entity reg = new register(lhs.type, curFunction.getRegId());

        if (lhs.isStr() && rhs.isStr()) {
            ArrayList<Entity> para = new ArrayList<>();
            para.add(lhs); para.add(rhs);
            curBlock.addInst(new callInst(reg, reg.type, "__string_add", para));
        } else curBlock.addInst(new binaryInst(it.opCode, reg, lhs, rhs));

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

        Entity reg = new register(new iIRType(1), curFunction.getRegId());

        if (lhs.isStr() && rhs.isStr()) {
            String type = switch (it.opCode) {
                case LE -> "__string_lt";
                case GR -> "__string_gt";
                case LEQ -> "__string_le";
                case GEQ -> "__string_ge";
            };
            ArrayList<Entity> para = new ArrayList<>();
            para.add(lhs);
            para.add(rhs);
            curBlock.addInst(new callInst(reg, reg.type, type, para));
        } else curBlock.addInst(new icmpInst(reg, it.opCode, lhs, rhs));

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

        Entity reg = new register(new iIRType(1), curFunction.getRegId());

        if (lhs.isStr() && rhs.isStr()) {
            ArrayList<Entity> para = new ArrayList<>();
            para.add(lhs); para.add(rhs);
            curBlock.addInst(new callInst(reg, reg.type, "__string_eq", para));
        } else curBlock.addInst(new icmpInst(reg, it.opt, lhs, rhs));

        it.entity = reg;
    }

    public void visit(funcExprNode it) {
        String name;
        funcType fType;
        ArrayList<Entity> para = new ArrayList<>();

        if (it.caller instanceof varExprNode) {
            fType = curScope.getClassFuncType(((varExprNode) it.caller).identifier);
            if (curClass != null && fType != null) {
                Entity classPtr = curFunction.paraEntity.get(0);
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

            name = ((memberExprNode) it.caller).member;

            if (cType instanceof classIRType) { // class

                String className = ((classIRType) cType).getOriginIdentifier();

                name = "class." + className + "." + name;

                fType = gScope.getClassType(className).getFunc(((memberExprNode) it.caller).member);

                para.add(callerEntity);

            } else if (cType instanceof iIRType && ((iIRType) cType).getBits() == 8) { // string

                name = "__string_" + name;
                fType = gScope.getClassType("string").getFunc(((memberExprNode) it.caller).member);
                para.add(callerEntity);

            } else  { // array

                name = "__array_size";
                fType = new funcType();
                fType.ret = new classType("int");
                para.add(callerEntity);

            }


        } else throw new codegenError("[function expression]", it.pos);

        for (int i = 0; i < it.exprs.size(); ++i) {
            ExprNode e = it.exprs.get(i);
            e.accept(this);
            Entity expr = e.entity;
            if (expr.type instanceof addrIRType)
                expr = loadAddrType(expr);
            if (expr.isNULL())
                expr.type = getIRType(fType.para.get(i));
            para.add(expr);
        }

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

        switch (((classType) it.type).classname) {
            case "int" -> it.entity = new constant(new iIRType(32), it.intVal);
            case "bool" -> it.entity = new constant(new iIRType(1), it.boolVal);
            case "null" -> it.entity = new constant(new ptrIRType(new voidIRType()), constant.constantType.NULL);
            case "string" -> {
                String str = it.stringVal;
                str = str.replace("\\\\", "\\").replace("\\n", "\n")
                        .replace("\\t", "\t").replace("\\\"", "\"")
                        .replace("\\0", "\0");
                IRType iType = new arrayIRType(str.length() + 1, i8Type);
                str = str.replace("\\", "\\5C").replace("\n", "\\0A")
                        .replace("\t", "\\09").replace("\"", "\\22")
                        .replace("\0", "\\00");
                Entity constStr = new global(new ptrIRType(iType), ".str." + (strNum++));
                topModule.globals.add(new globalInst(constStr, new constant(iType, str), globalInst.defineType.CONSTANT));
                it.entity = new register(strType, curFunction.getRegId());
                curBlock.addInst(new bitcastInst(it.entity, constStr));
            }
        };
    }

    public void visit(memberExprNode it) {
        // func condition is considered in FuncExprNode
        // so only consider [var in class] condition

        it.caller.accept(this);

        Entity classptr;
        if (it.caller.entity.type instanceof addrIRType)
            classptr = loadAddrType(it.caller.entity);
        else classptr = it.caller.entity;

        assert(classptr.type instanceof ptrIRType);
        assert(((ptrIRType) classptr.type).type instanceof classIRType);
        classIRType cType = (classIRType) ((ptrIRType) classptr.type).type;

        int offset = cType.c.offsets.get(it.member);

        // System.err.println(it.member + " " + offset);

        IRType type = cType.c.vars.get(offset);

        register res = new register(new addrIRType(type), curFunction.getRegId());

        getelementptrInst inst = new getelementptrInst(res, classptr, cType);
        inst.idx.add(new constant(new iIRType(32), 0));
        inst.idx.add(new constant(new iIRType(32), offset));

        curBlock.addInst(inst);

        it.entity = res;
    }

    public void visit(newExprNode it) {
        assert it.dimExpr != null;

        classType cType = new classType(it.ctx, gScope);
        IRType iType = getIRType(cType);

        // class
        if (it.dimExpr.size() == 0) {
            IRClass iClass = gScope.getIRClasses(cType.classname);
            Entity tmp = new register(new addrIRType(new iIRType(8)), curFunction.getRegId());

            ArrayList<Entity> parameter = new ArrayList<>();
            assert iType instanceof ptrIRType;
            iType = ((ptrIRType) iType).type;
            // System.err.println(iType.getBytes());
            parameter.add(new constant(new iIRType(32), iClass.bytes));
            curBlock.addInst(new callInst(tmp, tmp.type, "malloc", parameter));
            it.entity = new register(new ptrIRType(new classIRType(iClass)), curFunction.getRegId());
            curBlock.addInst(new bitcastInst(it.entity, tmp));

            if (iClass.constructor) {
                parameter = new ArrayList<>();
                parameter.add(it.entity);
                curBlock.addInst(new callInst(iClass.identifier + "." + iClass.identifier, parameter));
            }
            return ;
        }

        // array
        int dim = 0;
        for (int i = 0; i < it.dimExpr.size(); ++i)
            if (it.dimExpr.get(i) != null) dim = i + 1;
        assert dim > 0;

        // array
        ExprNode expr = it.dimExpr.get(0);
        expr.accept(this);
        Entity size = expr.entity;
        if (size.type instanceof addrIRType)
            size = loadAddrType(size);

        int singleSize = dim == 1 ? iType.getBytes() : 4;
        register pro = new register(new iIRType(32), curFunction.getRegId());
        curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.MUL, pro, size, new constant(new iIRType(32), singleSize)));

        register sum = new register(new iIRType(32), curFunction.getRegId());
        curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.ADD, sum, pro, new constant(new iIRType(32), 4)));

        register sizeTmpPtr = new register(new addrIRType(new iIRType(8)), curFunction.getRegId());
        ArrayList<Entity> parameter = new ArrayList<>();
        parameter.add(sum);
        curBlock.addInst(new callInst(sizeTmpPtr, sizeTmpPtr.type, "malloc", parameter));

        register sizePtr = new register(new addrIRType(new iIRType(32)), curFunction.getRegId());
        curBlock.addInst(new bitcastInst(sizePtr, sizeTmpPtr));

        curBlock.addInst(new storeInst(sizePtr, size));

        register arrayTmpPtr = new register(sizePtr.type, curFunction.getRegId());
        curBlock.addInst(new getelementptrInst(arrayTmpPtr, sizePtr, new iIRType(32), new constant(new iIRType(32), 1)));

        IRType curTpye = iType;
        for (int i = 0; i < dim; ++i)
            curTpye = new ptrIRType(curTpye);
        register arrayPtr = new register(curTpye, curFunction.getRegId());
        curBlock.addInst(new bitcastInst(arrayPtr, arrayTmpPtr));

        it.entity = arrayPtr;

        for (int i = 1; i < dim; ++i) {
            register preArrayPtr = arrayPtr;
            Entity preSize = size;

            IRBlock conditionBlock = new IRBlock(curFunction); // condition
            IRBlock bodyBlock = new IRBlock(curFunction); // loop body
            IRBlock stepBlock = new IRBlock(curFunction); // increase
            IRBlock endBlock = new IRBlock(curFunction); // end

            register stepAddr = new register(new addrIRType(new iIRType(32)), curFunction.getRegId());
            curBlock.addInst(new allocaInst(stepAddr, new iIRType(32)));
            curBlock.addInst(new storeInst(stepAddr, new constant(new iIRType(32), 0)));

            curBlock.addInst(new brInst(conditionBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = conditionBlock;
            register step = new register(new iIRType(32), curFunction.getRegId());
            curBlock.addInst(new loadInst(step, stepAddr));
            register cmp = new register(new iIRType(1), curFunction.getRegId());
            curBlock.addInst(new icmpInst(cmp, cmpExprNode.cmpOpType.LE, step, preSize));
            curBlock.addInst(new brInst(cmp, bodyBlock.name, endBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = bodyBlock;
            expr = it.dimExpr.get(i); // Optimize: 放到循环之外
            expr.accept(this);
            size = expr.entity;
            if (size.type instanceof addrIRType)
                size = loadAddrType(size);

            singleSize = dim - i == 1 ? iType.getBytes() : 4;
            pro = new register(new iIRType(32), curFunction.getRegId());
            curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.MUL, pro, size, new constant(new iIRType(32), singleSize)));

            sum = new register(new iIRType(32), curFunction.getRegId());
            curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.ADD, sum, pro, new constant(new iIRType(32), 4)));

            sizeTmpPtr = new register(new addrIRType(new iIRType(8)), curFunction.getRegId());
            parameter = new ArrayList<>();
            parameter.add(sum);
            curBlock.addInst(new callInst(sizeTmpPtr, sizeTmpPtr.type, "malloc", parameter));

            sizePtr = new register(new addrIRType(new iIRType(32)), curFunction.getRegId());
            curBlock.addInst(new bitcastInst(sizePtr, sizeTmpPtr));

            curBlock.addInst(new storeInst(sizePtr, size));

            arrayTmpPtr = new register(sizePtr.type, curFunction.getRegId());
            curBlock.addInst(new getelementptrInst(arrayTmpPtr, sizePtr, new iIRType(32), new constant(new iIRType(32), 1)));

            curTpye = iType;
            for (int j = 0; j < dim - i; ++j)
                curTpye = new ptrIRType(curTpye);
            arrayPtr = new register(curTpye, curFunction.getRegId());
            curBlock.addInst(new bitcastInst(arrayPtr, arrayTmpPtr));

            register addrReg = new register(preArrayPtr.type, curFunction.getRegId());
            curBlock.addInst(new getelementptrInst(addrReg, preArrayPtr, ((ptrIRType) preArrayPtr.type).type, step));
            curBlock.addInst(new storeInst(addrReg, arrayPtr));

            curBlock.addInst(new brInst(stepBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = stepBlock;
            register nextStep = new register(new iIRType(32), curFunction.getRegId());
            curBlock.addInst(new binaryInst(binaryExprNode.binaryOpType.ADD, nextStep, step, new constant(new iIRType(32), 1)));
            curBlock.addInst(new storeInst(stepAddr, nextStep));
            curBlock.addInst(new brInst(conditionBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = endBlock;
        }
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
        if (it.identifier.equals("this")) {
            it.entity = curFunction.paraEntity.get(0);
            return ;
        }

        it.entity = curScope.getEntity(it.identifier);

        if (it.entity instanceof label) {
            assert(curClass != null);
            Entity classptr = curFunction.paraEntity.get(0);

            assert(classptr.type instanceof ptrIRType);
            assert(((ptrIRType) classptr.type).type instanceof classIRType);
            classIRType cType = (classIRType) ((ptrIRType) classptr.type).type;

            int offset = cType.c.offsets.get(it.identifier);
            IRType type = cType.c.vars.get(offset);

            register res = new register(new addrIRType(type), curFunction.getRegId());

            getelementptrInst inst = new getelementptrInst(res, classptr, cType);
            inst.idx.add(new constant(new iIRType(32), 0));
            inst.idx.add(new constant(new iIRType(32), offset));

            curBlock.addInst(inst);

            it.entity = res;
        }
    }


    public void visit(StmtNode it) { }

    public void visit(compoundStmtNode it) {
        curScope = new Scope(curScope);
        it.stmts.forEach(stmt -> stmt.accept(this));
        curScope = curScope.parentScope();
    }

    public void visit(defStmtNode it) {
        it.def.accept(this);
    }

    public void visit(exprStmtNode it) {
        if (it.expr != null)
            it.expr.accept(this);
    }

    public void visit(flowStmtNode it) {
        curBlock.addInst(new brInst((it.isbreak ? curEndBlock : curBeginBlock).name));
        curBlock.br = true;
    }

    public void visit(forStmtNode it) {
        curScope = new Scope(curScope);
        it.initial.accept(this);

        IRBlock tmpBeginBlock = curBeginBlock;
        IRBlock tmpEndBlock = curEndBlock;

        IRBlock conditionBlock = new IRBlock(curFunction); // condition
        IRBlock bodyBlock = new IRBlock(curFunction); // loop body
        curBeginBlock = new IRBlock(curFunction); // increase
        curEndBlock = new IRBlock(curFunction); // end

        curBlock.addInst(new brInst(conditionBlock.name));
        curFunction.blocks.add(curBlock);

        curBlock = conditionBlock;
        it.condition.accept(this);
        Entity condition =  it.condition.entity;
        if (condition.type instanceof addrIRType)
            condition = loadAddrType(condition);
        curBlock.addInst(new brInst(condition, bodyBlock.name, curEndBlock.name));
        curFunction.blocks.add(curBlock);

        curBlock = bodyBlock;
        it.stmt.accept(this);
        curBlock.addInst(new brInst(curBeginBlock.name));
        curFunction.blocks.add(curBlock);

        curBlock = curBeginBlock;
        if (it.step != null)
            it.step.accept(this);
        curBlock.addInst(new brInst(conditionBlock.name));
        curFunction.blocks.add(curBlock);

        curBlock = curEndBlock;

        curBeginBlock = tmpBeginBlock;
        curEndBlock = tmpEndBlock;
        curScope = curScope.parentScope();
    }

    public void visit(ifStmtNode it) {
        it.condition.accept(this);

        // Constant optimization

        Entity condition = it.condition.entity;
        if (condition.type instanceof addrIRType)
            condition = loadAddrType(condition);

        if (it.elseStmt == null) {
            IRBlock thenBlock = new IRBlock(curFunction);
            IRBlock endBlock = new IRBlock(curFunction);

            curBlock.addInst(new brInst(condition, thenBlock.name, endBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = thenBlock;
            curScope = new Scope(curScope);
            it.thenStmt.accept(this);
            curScope = curScope.parentScope();
            curBlock.addInst(new brInst(endBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = endBlock;
        } else {
            IRBlock thenBlock = new IRBlock(curFunction);
            IRBlock elseBlock = new IRBlock(curFunction);
            IRBlock endBlock = new IRBlock(curFunction);

            curBlock.addInst(new brInst(condition, thenBlock.name, elseBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = thenBlock;
            curScope = new Scope(curScope);
            it.thenStmt.accept(this);
            curScope = curScope.parentScope();
            curBlock.addInst(new brInst(endBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = elseBlock;
            curScope = new Scope(curScope);
            it.elseStmt.accept(this);
            curScope = curScope.parentScope();
            curBlock.addInst(new brInst(endBlock.name));
            curFunction.blocks.add(curBlock);

            curBlock = endBlock;
        }
    }

    public void visit(returnStmtNode it) {
        if (it.value != null) {
            it.value.accept(this);
            Entity res = it.value.entity;
            if (res.type instanceof addrIRType)
                res = loadAddrType(res);
            if (res.isNULL())
                res.type = curFunction.retType;
            curBlock.addInst(new storeInst(curFunction.retEntity, res));
        }
        curBlock.br = true;
        curFunction.retBlocks.add(curBlock);
    }

    public void visit(whileStmtNode it) {
        IRBlock tmpBeginBlock = curBeginBlock;
        IRBlock tmpEndBlock = curEndBlock;

        curBeginBlock = new IRBlock(curFunction);
        IRBlock bodyBlock = new IRBlock(curFunction);
        curEndBlock = new IRBlock(curFunction);

        curBlock.addInst(new brInst(curBeginBlock.name));
        curFunction.blocks.add(curBlock);

        curBlock = curBeginBlock;
        it.condition.accept(this);
        Entity condition = it.condition.entity;
        if (condition.type instanceof addrIRType)
            condition = loadAddrType(condition);
        curBeginBlock.addInst(new brInst(condition, bodyBlock.name, curEndBlock.name));
        curFunction.blocks.add(curBlock);

        curBlock = bodyBlock;
        curScope = new Scope(curScope);
        it.stmt.accept(this);
        curScope = curScope.parentScope();
        curBlock.addInst(new brInst(curBeginBlock.name));
        curFunction.blocks.add(curBlock);

        curBlock = curEndBlock;

        curBeginBlock = tmpBeginBlock;
        curEndBlock = tmpEndBlock;
    }

}
