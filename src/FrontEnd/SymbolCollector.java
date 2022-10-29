package FrontEnd;

import AST.Stmt.*;
import AST.Expr.*;
import AST.Def.*;
import AST.ASTVisitor;
import AST.RootNode;
import Util.Scope.globalScope;
import Util.Type.classType;
import Util.Type.funcType;
import Util.error.semanticError;
import Util.error.syntaxError;

public class SymbolCollector implements ASTVisitor {
    private globalScope gScope;
    public SymbolCollector(globalScope gScope) {
        classType intType = new classType("int");
        classType boolType = new classType("bool");
        classType stringType = new classType("string");
        classType voidType = new classType("void");
        funcType ftype;

        ftype = new funcType(intType);
        stringType.addFunc("length", ftype, null);

        ftype = new funcType(stringType);
        ftype.para.add(intType);
        ftype.para.add(intType);
        stringType.addFunc("substring", ftype, null);

        ftype = new funcType(intType);
        stringType.addFunc("parseInt", ftype, null);

        ftype = new funcType(intType);
        ftype.para.add(intType);
        stringType.addFunc("ord", ftype, null);

        gScope.addClass("int", intType, null);
        gScope.addClass("bool", boolType, null);
        gScope.addClass("string", stringType, null);
        gScope.addClass("void", voidType, null);

        ftype = new funcType(voidType);
        ftype.para.add(stringType);
        gScope.addFunc("print", ftype, null);
        gScope.addFunc("println", ftype, null);

        ftype = new funcType(voidType);
        ftype.para.add(intType);
        gScope.addFunc("printInt", ftype, null);
        gScope.addFunc("printlnInt", ftype, null);

        ftype = new funcType(stringType);
        gScope.addFunc("getString", ftype, null);

        ftype = new funcType(intType);
        gScope.addFunc("getInt", ftype, null);

        ftype = new funcType(stringType);
        ftype.para.add(intType);
        gScope.addFunc("toString", ftype, null);

        this.gScope = gScope;
    }
    @Override
    public void visit(RootNode it) {
        for (DefNode def : it.defs) {
            if (def instanceof  classDefNode) {
                classType ci = new classType(((classDefNode) def).identifier);
                gScope.addClass(((classDefNode) def).identifier, ci, def.pos);
            }
        }
        for (DefNode def : it.defs)
            if (def instanceof classDefNode)
                def.accept(this);

        for (DefNode def : it.defs)
            if (def instanceof funcDefNode)
                def.accept(this);

        funcType mainFn = gScope.getFuncType("main");
        if (mainFn == null || mainFn.para.size() > 0 || !mainFn.ret.isType("int"))
            throw new semanticError("main function invalid", it.pos);
    }

    public void visit(DefNode it) {}
    public void visit(classDefNode it) {
        classType ci = gScope.getClassType(it.identifier);
        for (varDefNode vd : it.varDecs) {
            for (singleVarDefNode svd : vd.singleVarDefs) {
                classType vtype = new classType(svd.typename, gScope);
                ci.addVar(svd.identifier, vtype, svd.pos);
            }
        }
        for (funcDefNode fd : it.funcDefs) {
            if (fd.identifier.equals(it.identifier))
                throw new syntaxError("[class definition] class constructor type wrong", fd.pos);
            funcType fi = new funcType();
            fi.ret = new classType(fd.retype, gScope);
            fd.parameter.singleVarDefs.forEach(svd -> fi.para.add(new classType(svd.typename, gScope)));
            ci.addFunc(fd.identifier, fi, fd.pos);
        }
    }
    public void visit(funcDefNode it) {
        funcType fi = new funcType();
        if (it.retype != null)
            fi.ret = new classType(it.retype, gScope);
        else fi.ret = new classType("void");
        it.parameter.singleVarDefs.forEach(svd -> fi.para.add(new classType(svd.typename, gScope)));
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
