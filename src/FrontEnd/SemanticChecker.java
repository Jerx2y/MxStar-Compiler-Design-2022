package FrontEnd;

import AST.*;
import AST.Stmt.*;
import AST.Expr.*;
import AST.Def.*;
import Util.Type.classType;
import Util.Type.funcType;
import Util.Scope.*;
import Util.Type.varType;
import Util.error.semanticError;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    private final globalScope gScope;
    private int inLoop = 0;

    private final varType intType;
    private final varType stringType;
    private final varType boolType;

    public SemanticChecker(globalScope gScope) {
        currentScope = this.gScope = gScope;
        intType = new varType(varType.BuiltinType.INT);
        boolType = new varType(varType.BuiltinType.BOOL);
        stringType = new varType(varType.BuiltinType.STRING);
    }

    @Override
    public void visit(RootNode it) {
        for (DefNode def : it.defs)
            def.accept(this);
    }

    public void visit(DefNode it) {}
    public void visit(classDefNode it) {
        currentScope = new classScope(gScope.getClassTypeFromName(it.identifier, it.pos), currentScope);
        it.funcDefs.forEach(fd -> fd.accept(this));
        currentScope = new funcScope(new funcType(new varType(varType.BuiltinType.VOID)), currentScope);
        it.constructor.accept(this);
        currentScope = currentScope.parentScope();
        currentScope = currentScope.parentScope();
    }
    public void visit(funcDefNode it) {
        currentScope = new funcScope(currentScope.getFuncType(it.identifier, true), currentScope);
        it.parameter.singleVarDefs.forEach(svd -> svd.accept(this));
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope();
    }
    public void visit(singleVarDefNode it) {
        varType itype = new varType(it.typename, gScope);
        if (it.initExpr != null) {
            it.initExpr.accept(this);
            if (itype.isVar(varType.BuiltinType.INT) ||
                    itype.isVar(varType.BuiltinType.BOOL) ||
                    itype.isVar(varType.BuiltinType.STRING) ||
                    it.initExpr.type.basicType != varType.BuiltinType.NULL)
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
        if (!it.index.type.isVar(varType.BuiltinType.INT))
            throw new semanticError("[array expression] index not int", it.index.pos);
        it.type = new varType(it.caller.type);
        it.type.dimension--;
        if (it.type.dimension < 0)
            throw new semanticError("[array expression] dimension too more", it.pos);
    }
    public void visit(assignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.isAssignable())
            throw new semanticError("[assign expression] lhs not assignable", it.pos);
        it.type = new varType(it.lhs.type);
        if (!it.lhs.type.isVar(varType.BuiltinType.INT) &&
                !it.lhs.type.isVar(varType.BuiltinType.BOOL) &&
                !it.lhs.type.isVar(varType.BuiltinType.STRING) &&
                it.rhs.type.basicType == varType.BuiltinType.NULL)
            return ;
        if (!it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[assign expression] value not match", it.pos);
    }
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[binary expression] type not match", it.pos);
        it.type = new varType(it.lhs.type);
        switch (it.opCode) {
            case ADD:
                if (!it.type.isVar(varType.BuiltinType.INT) && !it.type.isVar(varType.BuiltinType.STRING))
                    throw new semanticError("[binary expression] type not match (I)", it.pos);
                break;

            case DOUBLE_OR: case DOUBLE_AND:
                if (!it.type.isVar(varType.BuiltinType.BOOL))
                    throw new semanticError("[binary expression] type not match (II)", it.pos);
                break;

            case SUB: case MUL: case DIV: case MOD:
            case DOUBLE_L: case DOUBLE_R: case AND: case OR: case XOR:
                if (!it.type.isVar(varType.BuiltinType.INT))
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
        if (!it.lhs.type.isVar(varType.BuiltinType.INT) && !it.lhs.type.isVar(varType.BuiltinType.STRING))
            throw new semanticError("[cmp expression] type not valid", it.pos);
    }
    public void visit(eqExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        it.type = boolType;
        if (it.lhs.type.isArray() && it.rhs.type.isVar(varType.BuiltinType.NULL) ||
            it.rhs.type.isArray() && it.lhs.type.isVar(varType.BuiltinType.NULL))
            return ;
        if (!it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[eq expression] type not match", it.pos);
    }
    public void visit(funcExprNode it) {
        it.caller.accept(this);
        it.exprs.forEach(ex -> ex.accept(this));
        if (!it.caller.type.isVar(varType.BuiltinType.FUNC))
            throw new semanticError("[function expression] type wrong", it.pos);
        funcType fn = it.caller.type.ftype;
        if (fn.para.size() != it.exprs.size())
            throw new semanticError("[function expression] parameter number wrong", it.pos);
        for (int i = 0, sz = fn.para.size(); i < sz; ++i)
            if (!it.exprs.get(i).type.equal(fn.para.get(i)))
                throw new semanticError("[function expression] parameter type wrong", it.pos);
        it.type = new varType(fn.ret);
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
        if (it.type == null) it.type = new varType(varType.BuiltinType.VOID);
        for (int i = 0, sz = it.exprs.size(); i < sz; ++i) {
            if (!it.exprs.get(i).type.equal(new varType(it.parameter.singleVarDefs.get(i).typename, gScope)))
                throw new semanticError("[lambda expression] parameter type not match", it.pos);
        }
        currentScope = currentScope.parentScope();
    }
    public void visit(literalExprNode it) { ; }
    public void visit(memberExprNode it) {
        it.caller.accept(this);
        if (it.caller.type.isArray()) {
            if (!it.member.equals("size"))
                throw new semanticError("[member expression] array.member wrong", it.pos);
            it.type = new varType(new funcType(intType));
            return ;
        }
//        if (it.caller.type.isVar(varType.BuiltinType.THIS)) {
//            classScope cs = currentScope.getClassScope();
//            if (cs == null)
//                throw new semanticError("[member expression] 'this' in wrong place", it.caller.pos);
//            funcType fn = cs.getFuncType(it.member, false);
//            varType var = cs.getVarType(it.member, false);
//            if (fn != null) it.type = new varType(fn);
//            else if (var != null) it.type = new varType(var);
//            else throw new semanticError("[member expression] identifier undefined in class", it.pos);
//            return ;
//        }
        if (it.caller.type.isVar(varType.BuiltinType.STRING)) {
            if (it.member.equals("length"))
                it.type = new varType(new funcType(intType));
            else if (it.member.equals("substring")) {
                funcType fn = new funcType(stringType);
                fn.para.add(intType);
                fn.para.add(intType);
                it.type = new varType(fn);
            } else if (it.member.equals("parseInt"))
                it.type = new varType(new funcType(intType));
            else if (it.member.equals("ord")) {
                funcType fn = new funcType(intType);
                fn.para.add(intType);
                it.type = new varType(fn);
            } else throw new semanticError("[member expression] string method undefined", it.pos);
            return ;
        }
        if (!it.caller.type.isVar(varType.BuiltinType.CLASS))
            throw new semanticError("[member expression] caller type invalid", it.pos);
        classType c = it.caller.type.ctype;
        funcType fn = c.getFunc(it.member);
        varType var = c.getVar(it.member);
        if (fn != null) it.type = new varType(fn);
        else if (var != null) it.type = new varType(var);
        else throw new semanticError("[member expression] member not found", it.pos);
    }
    public void visit(newExprNode it) {
        // TODO: check class type valid
        it.type = new varType(it.ctx, gScope);
        boolean nowNull = false;
        for (ExprNode expr : it.dimExpr) {
            if (expr == null)
                nowNull = true;
            else {
                if (nowNull)
                    throw new semanticError("[new expression] new on null", expr.pos);
                expr.accept(this);
                if (!expr.type.isVar(varType.BuiltinType.INT))
                    throw new semanticError("[new expression] array index not int", expr.pos);
            }
        }
    }
    public void visit(unaryExprNode it) {
        it.expr.accept(this);
        it.type = new varType(it.expr.type);
        if (it.opCode == unaryExprNode.unaryOpType.NOT) {
            if (!it.expr.type.isVar(varType.BuiltinType.BOOL))
                throw new semanticError("[unary expression] expr type wrong (I)", it.pos);
        } else {
            if (!it.expr.type.isVar(varType.BuiltinType.INT))
                throw new semanticError("[unary expression] expr type wrong (II)", it.pos);
        }
        if (it.opCode == unaryExprNode.unaryOpType.DOUBLE_ADD || it.opCode == unaryExprNode.unaryOpType.DOUBLE_SUB ||
            it.opCode == unaryExprNode.unaryOpType.R_DOUBLE_ADD || it.opCode == unaryExprNode.unaryOpType.R_DOUBLE_SUB)
            if (!it.expr.isAssignable())
                throw new semanticError("[unary expression] ++-- expression not assignable", it.pos);
    }
    public void visit(varExprNode it) {

        if (it.identifier.equals("this")) {
            classScope cs = currentScope.getClassScope();
            if (cs == null)
                throw new semanticError("[var expression] 'this' out of class", it.pos);
            classType ctype = new classType(cs.classname, cs.getVarsList(), cs.getFuncList());
            it.type = new varType(ctype);
            return ;
        }

        varType vtype = currentScope.getVarType(it.identifier, true);
        if (vtype != null) {
            it.type = new varType(vtype);
            return ;
        }

        funcType ftype = currentScope.getFuncType(it.identifier, true);
        if (ftype != null) {
            it.type = new varType(ftype);
            return;
        }

        classType ctype = gScope.getClassType(it.identifier);
        if (ctype != null) {
            it.type = new varType(ctype);
            return;
        }

        throw new semanticError("[var expression] identifier '" + it.identifier + "' undefined", it.pos);
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
        if (!it.condition.type.isVar(varType.BuiltinType.BOOL))
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
        if (!it.condition.type.isVar(varType.BuiltinType.BOOL))
            throw new semanticError("[if statement] condition type wrong", it.condition.pos);
    }
    public void visit(returnStmtNode it) {
        funcType nowFunc = currentScope.getThisFuncInfo();
        if (nowFunc == null)
            throw new semanticError("[return statement] return position wrong", it.pos);
        if (it.value != null) {
            it.value.accept(this);
//            if (it.value.type.isVar(varType.BuiltinType.THIS)) {
//                if (nowFunc.ret.basicType != varType.BuiltinType.CLASS ||
//                        !nowFunc.ret.ctype.classname.equals(currentScope.getClassScope().classname))
//                    throw new semanticError("[return statement] 'return this' invalid", it.pos);
//                return;
//            }
            if (nowFunc.ret == null)
                nowFunc.ret = it.value.type;
            if (!it.value.type.equal(nowFunc.ret))
                throw new semanticError("[return statement] return type wrong (I)", it.pos);
        } else if (nowFunc.ret.isVar(varType.BuiltinType.VOID)) {
            if (nowFunc.ret == null)
                nowFunc.ret = new varType(varType.BuiltinType.VOID);
        } else throw new semanticError("[return statement] return type wrong (II)", it.pos);
    }
    public void visit(whileStmtNode it) {
        currentScope = new Scope(currentScope);
        inLoop++;
        it.condition.accept(this);
        it.stmt.accept(this);
        if (!it.condition.type.isVar(varType.BuiltinType.BOOL))
            throw new semanticError("[while statement] condition type is not bool", it.condition.pos);
        currentScope = currentScope.parentScope();
        inLoop--;
    }
}
