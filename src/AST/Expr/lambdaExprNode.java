package AST.Expr;

import AST.ASTVisitor;
import AST.Def.varDefNode;
import AST.Stmt.StmtNode;
import Util.position;

import java.util.ArrayList;

public class lambdaExprNode extends ExprNode {
    public boolean lookUpon;
    public varDefNode parameter;
    public StmtNode stmts;
    public ArrayList<ExprNode> exprs;

    public lambdaExprNode(boolean lookUpon, varDefNode paramter, StmtNode stmts, position pos) {
        super(pos);
        this.lookUpon = lookUpon;
        this.parameter = paramter;
        this.stmts = stmts;
        exprs = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
