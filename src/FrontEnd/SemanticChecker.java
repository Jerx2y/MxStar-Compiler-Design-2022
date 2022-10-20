package FrontEnd;

import AST.*;
import AST.Stmt.*;
import AST.Expr.*;
import AST.Def.*;
import Util.Info.classInfo;
import Util.Info.funcInfo;
import Util.Scope.*;
import Util.Type;
import Util.error.semanticError;

public class SemanticChecker implements ASTVisitor {
    private Scope currentScope;
    private globalScope gScope;
    private classInfo currentClass = null;

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
        currentClass = gScope.getClassTypeFromName(((classDefNode) def).identifier, it.pos);
        it.funcDefs.forEach(fd -> fd.accept(this));
        currentClass = null;
    }
    public void visit(funcDefNode it) {
        currentScope = new Scope(currentScope);
        it.parameter.singleVarDefs.forEach(svd -> svd.accept(this));
        it.stmts.accept(this);
        currentScope = currentScope.parentScope();
    }
    public void visit(singleVarDefNode it) {
        currentScope.defineVariable(it.identifier, it.type, it.pos);
    }
    public void visit(varDefNode it) {
        it.singleVarDefs.forEach(svd -> svd.accept(this));
    }

    public void visit(ExprNode it) {}
    public void visit(arrayExprNode it) {

    }
    public void visit(assignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.isAssignable())
            throw new semanticError("the left hand side value is not assignable", it.pos);
        if (!it.lhs.type.equals(it.rhs.type))
            throw new semanticError("the assignment's left/right hand side value type do not match", it.pos);
        it.type = new Type(it.lhs.type);
    }
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.equals(it.rhs.type))
            throw new semanticError("the binary operation's left/right hand side value type do not match", it.pos);
        it.type = new Type(it.lhs.type);
        switch (it.opCode) {
            case ADD:
                if (!it.type.isVar(Type.BuiltinType.INT) && !it.type.isVar(Type.BuiltinType.STRING))
                    throw new semanticError("binary operation do not match type", it.pos);
                break;

            case DOUBLE_OR: case DOUBLE_AND:
                if (!it.type.isVar(Type.BuiltinType.BOOL))
                    throw new semanticError("binary operation do not match type", it.pos);
                break;

            case SUB: case MUL: case DIV: case MOD:
            case DOUBLE_L: case DOUBLE_R: case AND: case OR: case XOR:
                if (!it.type.isVar(Type.BuiltinType.INT))
                    throw new semanticError("binary operation do not match type", it.pos);
                break;
        }
    }
    public void visit(cmpExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.equals(it.rhs.type))
            throw new semanticError("the cmp operation's left/right hand side value type do not match", it.pos);
        it.type = new Type(Type.BuiltinType.BOOL);
        if (!it.lhs.type.isVar(Type.BuiltinType.INT) || !it.lhs.type.isVar(Type.BuiltinType.STRING))
            throw new semanticError("the cmp operation's left/right hand side value type is not valid", it.pos);
    }
    public void visit(eqExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type.isArray() && it.rhs.type.basicType == Type.BuiltinType.NULL ||
            it.rhs.type.isArray() && it.lhs.type.basicType == Type.BuiltinType.NULL)
            return ;
        if (!it.lhs.type.equals(it.rhs.type))
            throw new semanticError("the eq operation's left/right hand side value type do not match", it.pos);
    }
    public void visit(funcExprNode it) {
        funcInfo func = gScope.getFuncTypeFromName(it.caller, it.pos);
        it.exprs.forEach(ex -> ex.accept(this));
        if (func.para.size() != it.exprs.size())
            throw new semanticError("function call's parameter number do not match", it.pos);
        for (int i = 0, sz = func.para.size(); i < sz; ++i)
            if (!func.para.get(i).equals(it.exprs.get(i).type))
                throw new semanticError("function call's parameter type do not match", it.pos);
    }
    public void visit(lambdaExprNode it) {
        it.parameter.accept(this);
        it.stmts.accept(this);
        it.exprs.forEach(ex -> ex.accept(this));
        if (it.exprs.size() != it.parameter.singleVarDefs.size())
            throw new semanticError("lambda function's parameter number do not match", it.pos);
        for (int i = 0, sz = it.exprs.size(); i < sz; ++i) {
            if (!it.exprs.get(i).type.equals(it.parameter.singleVarDefs.get(i).type))
                throw new semanticError("lambda function's parameter type do not match", it.pos);
        }
    }
    public void visit(literalExprNode it) {
        ;
    }
    public void visit(memberExprNode it) {

    }
    public void visit(newExprNode it) {
        ;
    }
    public void visit(unaryExprNode it) {
        ;
    }
    public void visit(varExprNode it) {
        ;
    }

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
