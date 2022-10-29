package FrontEnd;

import AST.*;
import AST.Stmt.*;
import AST.Expr.*;
import AST.Def.*;
import Util.Type.classType;
import Util.Type.funcType;
import Util.Scope.*;
import Util.error.semanticError;
import org.antlr.v4.runtime.misc.Pair;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    private final globalScope gScope;
    private int inLoop = 0;

    private final classType intType;
    private final classType boolType;
    private final classType stringType;
    private final classType voidType;

    public SemanticChecker(globalScope gScope) {
        currentScope = this.gScope = gScope;
        intType = gScope.getClassType("int");
        boolType = gScope.getClassType("bool");
        stringType = gScope.getClassType("string");
        voidType = gScope.getClassType("void");
    }

    @Override
    public void visit(RootNode it) {
        for (DefNode def : it.defs)
            def.accept(this);
    }

    public void visit(DefNode it) {}
    public void visit(classDefNode it) {
        currentScope = new classScope(gScope.getClassType(it.identifier), currentScope);
        it.funcDefs.forEach(fd -> fd.accept(this));
        if (it.constructor != null) {
            currentScope = new funcScope(new funcType(voidType), currentScope);
            it.constructor.accept(this);
            currentScope = currentScope.parentScope();
        }
        currentScope = currentScope.parentScope();
    }
    public void visit(funcDefNode it) {
        currentScope = new funcScope(currentScope.getFuncType(it.identifier), currentScope);
        it.parameter.singleVarDefs.forEach(svd -> svd.accept(this));
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope();
    }
    public void visit(singleVarDefNode it) {
        classType itype = new classType(it.typename, gScope);
        if (it.initExpr != null) {
            it.initExpr.accept(this);
            if (!itype.equal(it.initExpr.type))
                throw new semanticError("[var declaration] initial expression type wrong", it.pos);
        }
        currentScope.defineVariable(it.identifier, itype, it.pos);
    }
    public void visit(varDefNode it) {
        it.singleVarDefs.forEach(svd -> svd.accept(this));
    }

    public void visit(ExprNode it) {}
    public void visit(arrayExprNode it) {
        it.caller.accept(this);
        it.index.accept(this);
        if (!it.index.type.isType("int"))
            throw new semanticError("[array expression] index not int", it.index.pos);
        classType itype = new classType(it.caller.type);
        itype.dimension--;
        if (itype.dimension < 0)
            throw new semanticError("[array expression] dimension too more", it.pos);
        it.type = itype;
    }
    public void visit(assignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.isAssignable())
            throw new semanticError("[assign expression] lhs not assignable", it.pos);
        it.type = voidType;
        if (!it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[assign expression] value not match", it.pos);
    }
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[binary expression] type not match", it.pos);
        it.type = new classType(it.lhs.type);
        switch (it.opCode) {
            case ADD:
                if (!it.type.isType("int") && !it.type.isType("string"))
                    throw new semanticError("[binary expression] type not match (I)", it.pos);
                break;

            case DOUBLE_OR: case DOUBLE_AND:
                if (!it.type.isType("bool"))
                    throw new semanticError("[binary expression] type not match (II)", it.pos);
                break;

            case SUB: case MUL: case DIV: case MOD:
            case DOUBLE_L: case DOUBLE_R: case AND: case OR: case XOR:
                if (!it.type.isType("int"))
                    throw new semanticError("[binary expression] type not match (III)", it.pos);
                break;
        }
    }
    public void visit(cmpExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[cmp expression] type not match", it.pos);
        it.type = boolType;
        if (!it.lhs.type.isType("int") && !it.lhs.type.isType("string"))
            throw new semanticError("[cmp expression] type not valid", it.pos);
    }
    public void visit(eqExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        it.type = boolType;
        if (!it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[eq expression] type not match", it.pos);
    }
    public void visit(funcExprNode it) {
        it.caller.accept(this);
        it.exprs.forEach(ex -> ex.accept(this));
        if (!(it.caller.type instanceof funcType))
            throw new semanticError("[function expression] type wrong", it.pos);
        funcType ftype = (funcType) it.caller.type;
        if (ftype.para.size() != it.exprs.size())
            throw new semanticError("[function expression] parameter number wrong", it.pos);
        for (int i = 0, sz = ftype.para.size(); i < sz; ++i)
            if (!it.exprs.get(i).type.equal(ftype.para.get(i)))
                throw new semanticError("[function expression] parameter type wrong", it.pos);
        it.type = new classType(ftype.ret);
    }
    public void visit(lambdaExprNode it) {
        currentScope = new funcScope(new funcType(), currentScope);
        currentScope.lookup = it.lookUpon;
        it.parameter.accept(this);
        it.stmts.accept(this);
        it.exprs.forEach(ex -> ex.accept(this));
        if (it.exprs.size() != it.parameter.singleVarDefs.size())
            throw new semanticError("[lambda expression] parameter number not match", it.pos);
        it.type = currentScope.getThisFuncInfo().ret;
        if (it.type == null) it.type = voidType;
        for (int i = 0, sz = it.exprs.size(); i < sz; ++i) {
            if (!it.exprs.get(i).type.equal(new classType(it.parameter.singleVarDefs.get(i).typename, gScope)))
                throw new semanticError("[lambda expression] parameter type not match", it.pos);
        }
        currentScope = currentScope.parentScope();
    }
    public void visit(literalExprNode it) {}
    public void visit(memberExprNode it) {
        it.caller.accept(this);
        if (it.caller.type.isArray()) {
            if (!it.member.equals("size"))
                throw new semanticError("[member expression] array.member wrong", it.pos);
            it.type = new funcType(intType);
            return ;
        }
//        if (it.caller.type.isVar(varType.BuiltinType.STRING)) {
//            if (it.member.equals("length"))
//                it.type = new varType(new funcType(intType));
//            else if (it.member.equals("substring")) {
//                funcType fn = new funcType(stringType);
//                fn.para.add(intType);
//                fn.para.add(intType);
//                it.type = new varType(fn);
//            } else if (it.member.equals("parseInt"))
//                it.type = new varType(new funcType(intType));
//            else if (it.member.equals("ord")) {
//                funcType fn = new funcType(intType);
//                fn.para.add(intType);
//                it.type = new varType(fn);
//            } else throw new semanticError("[member expression] string method undefined", it.pos);
//            return ;
//        }
        if (!(it.caller.type instanceof classType))
            throw new semanticError("[member expression] caller type wrong", it.caller.pos);
        classType c = (classType) it.caller.type;
        funcType fn = c.getFunc(it.member);
        classType var = c.getVar(it.member);
        if (fn != null) it.type = fn;
        else if (var != null) it.type = new classType(var);
        else throw new semanticError("[member expression] member not found", it.pos);
    }
    public void visit(newExprNode it) {
        it.type = new classType(it.ctx, gScope);
        boolean nowNull = false;
        for (ExprNode expr : it.dimExpr) {
            if (expr == null)
                nowNull = true;
            else {
                if (nowNull)
                    throw new semanticError("[new expression] new on null", expr.pos);
                expr.accept(this);
                if (!expr.type.isType("int"))
                    throw new semanticError("[new expression] array index not int", expr.pos);
            }
        }
    }
    public void visit(unaryExprNode it) {
        it.expr.accept(this);
        it.type = new classType(it.expr.type);
        if (it.opCode == unaryExprNode.unaryOpType.NOT) {
            if (!it.expr.type.isType("bool"))
                throw new semanticError("[unary expression] expr type wrong (bool)", it.pos);
        } else {
            if (!it.expr.type.isType("int"))
                throw new semanticError("[unary expression] expr type wrong (int)", it.pos);
        }
        if (it.opCode == unaryExprNode.unaryOpType.DOUBLE_ADD || it.opCode == unaryExprNode.unaryOpType.DOUBLE_SUB ||
            it.opCode == unaryExprNode.unaryOpType.R_DOUBLE_ADD || it.opCode == unaryExprNode.unaryOpType.R_DOUBLE_SUB)
            if (!it.expr.isAssignable())
                throw new semanticError("[unary expression] expression not assignable", it.pos);
    }
    public void visit(varExprNode it) {
        if (it.identifier.equals("this")) {
            classScope cs = currentScope.getClassScope();
            if (cs == null)
                throw new semanticError("[var expression] 'this' out of class", it.pos);
            it.type = new classType(cs.classname, cs.getVarsList(), cs.getFuncList());
            return ;
        }

        classType ctype = gScope.getClassType(it.identifier);
        if (ctype != null) {
            it.type = ctype;
            return;
        }

        Pair<classType, funcType> vftype = currentScope.getIdentifier(it.identifier);

        if (vftype.a != null)
            it.type = vftype.a;
        else if (vftype.b != null)
            it.type = vftype.b;
        else throw new semanticError("[var expression] identifier '" + it.identifier + "' undefined", it.pos);
    }

    public void visit(StmtNode it) {}
    public void visit(compoundStmtNode it) {
        currentScope = new Scope(currentScope);
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope();
    }
    public void visit(defStmtNode it) {
        it.def.accept(this);
    }
    public void visit(exprStmtNode it) {
        if (it.expr != null) it.expr.accept(this);
    }
    public void visit(flowStmtNode it) {
        if (inLoop == 0)
            throw new semanticError("[flow statement] flow position wrong", it.pos);
    }
    public void visit(forStmtNode it) {
        currentScope = new Scope(currentScope);
        inLoop++;
        it.initial.accept(this);
        it.condition.accept(this);
        if (it.step != null) it.step.accept(this);
        it.stmt.accept(this);
        if (!it.condition.type.isType("bool"))
            throw new semanticError("[for statement] condition type wrong", it.condition.pos);
        currentScope = currentScope.parentScope();
        inLoop--;
    }
    public void visit(ifStmtNode it) {
        it.condition.accept(this);
        currentScope = new Scope(currentScope);
        it.thenStmt.accept(this);
        currentScope = currentScope.parentScope();
        if (it.elseStmt != null) {
            currentScope = new Scope(currentScope);
            it.elseStmt.accept(this);
            currentScope = currentScope.parentScope();
        }
        if (!it.condition.type.isType("bool"))
            throw new semanticError("[if statement] condition type wrong", it.condition.pos);
    }
    public void visit(returnStmtNode it) {
        funcType nowFunc = currentScope.getThisFuncInfo();
        if (nowFunc == null)
            throw new semanticError("[return statement] return position wrong", it.pos);
        if (it.value != null) {
            it.value.accept(this);
            if (nowFunc.ret == null)
                nowFunc.ret = (classType) it.value.type;
            if (!it.value.type.equal(nowFunc.ret))
                throw new semanticError("[return statement] return type wrong (I)", it.pos);
        } else if (nowFunc.ret.isType("void")) {
            if (nowFunc.ret == null)
                nowFunc.ret = voidType;
        } else throw new semanticError("[return statement] return type wrong (II)", it.pos);
    }
    public void visit(whileStmtNode it) {
        currentScope = new Scope(currentScope);
        inLoop++;
        it.condition.accept(this);
        it.stmt.accept(this);
        if (!it.condition.type.isType("bool"))
            throw new semanticError("[while statement] condition type is not bool", it.condition.pos);
        currentScope = currentScope.parentScope();
        inLoop--;
    }
}
