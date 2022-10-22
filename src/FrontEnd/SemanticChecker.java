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

import java.lang.reflect.Type;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    private final globalScope gScope;
    private int inLoop = 0;

    public SemanticChecker(globalScope gScope) {
        currentScope = this.gScope = gScope;
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
        currentScope = currentScope.parentScope();
    }
    public void visit(funcDefNode it) {
        currentScope = new funcScope(currentScope.getFuncType(it.identifier, true), currentScope);
        it.parameter.singleVarDefs.forEach(svd -> svd.accept(this));
        it.stmts.forEach(stmt -> stmt.accept(this));
        currentScope = currentScope.parentScope();
    }
    public void visit(singleVarDefNode it) {
        currentScope.defineVariable(it.identifier, it.varType, it.pos);
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
        if (it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[assign expression] value not match", it.pos);
        it.type = new varType(it.lhs.type);
    }
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type.equal(it.rhs.type))
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
        if (it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[cmp expression] type not match", it.pos);
        it.type = new varType(varType.BuiltinType.BOOL);
        if (!it.lhs.type.isVar(varType.BuiltinType.INT) || !it.lhs.type.isVar(varType.BuiltinType.STRING))
            throw new semanticError("[cmp expression] type not valid", it.pos);
    }
    public void visit(eqExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type.isArray() && it.rhs.type.basicType == varType.BuiltinType.NULL ||
            it.rhs.type.isArray() && it.lhs.type.basicType == varType.BuiltinType.NULL)
            return ;
        if (it.lhs.type.equal(it.rhs.type))
            throw new semanticError("[eq expression] type not match", it.pos);
    }
    public void visit(funcExprNode it) {
        if (!(it.caller instanceof varExprNode) || !it.caller.type.isVar(varType.BuiltinType.FUNC))
            throw new semanticError("[function call] call expression not valid", it.pos);

        funcType func = currentScope.getFuncType(((varExprNode) it.caller).identifier, true);
        it.exprs.forEach(ex -> ex.accept(this));
        if (func.para.size() != it.exprs.size())
            throw new semanticError("[function call] parameter number not match", it.pos);
        for (int i = 0, sz = func.para.size(); i < sz; ++i)
            if (!func.para.get(i).equal(it.exprs.get(i).type))
                throw new semanticError("[function call] parameter type not match", it.pos);
    }
    public void visit(lambdaExprNode it) {
        it.parameter.accept(this);
        it.stmts.accept(this);
        it.exprs.forEach(ex -> ex.accept(this));
        if (it.exprs.size() != it.parameter.singleVarDefs.size())
            throw new semanticError("[lambda expression] parameter number not match", it.pos);
        for (int i = 0, sz = it.exprs.size(); i < sz; ++i) {
            if (it.exprs.get(i).type.equal(it.parameter.singleVarDefs.get(i).varType))
                throw new semanticError("[lambda expression] parameter type not match", it.pos);
        }
    }
    public void visit(literalExprNode it) { ; }
    public void visit(memberExprNode it) {
        if ()
        if (it.caller.type.basicType != varType.BuiltinType.CLASS)
            throw new semanticError("[member expression] caller not a class", it.pos);
        classType ctype = gScope.getClassType(it.caller)
    }
    public void visit(newExprNode it) {
        boolean isNull = false;
        for (ExprNode expr : it.dimExpr) {
            if (expr == null)
                isNull = true;
            else {
                if (isNull)
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
    }
    public void visit(varExprNode it) {
        if (it.identifier.equals("this")) {
            it.type = new varType(varType.BuiltinType.THIS);
            return ;
        }
        it.type = currentScope.getVarType(it.identifier, true);
        if (it.type == null) {
            if (currentScope.getFuncType(it.identifier, true) != null)
                it.type = new varType(it.identifier, true);
        } else it.type = new varType(it.type);
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
        it.step.accept(this);
        it.stmt.accept(this);
        if (!it.condition.type.isVar(varType.BuiltinType.BOOL))
            throw new semanticError("[for statement] condition type wrong", it.condition.pos);
        currentScope = currentScope.parentScope();
        inLoop--;
    }
    public void visit(ifStmtNode it) {
        it.condition.accept(this);
        it.thenStmt.accept(this);
        if (it.elseStmt != null)
            it.elseStmt.accept(this);
        if (!it.condition.type.isVar(varType.BuiltinType.BOOL))
            throw new semanticError("[if statement] condition type wrong", it.condition.pos);
    }
    public void visit(returnStmtNode it) {
        funcType nowFunc = currentScope.getThisFuncInfo();
        if (nowFunc == null)
            throw new semanticError("[return statement] return position wrong", it.pos);
        if (it.value != null) {
            it.value.accept(this);
            if (!it.value.type.equal(nowFunc.ret))
                throw new semanticError("[return statement] return type wrong (I)", it.pos);
        } else if (!nowFunc.ret.isVar(varType.BuiltinType.VOID))
            throw new semanticError("[return statement] return type wrong (II)", it.pos);
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
