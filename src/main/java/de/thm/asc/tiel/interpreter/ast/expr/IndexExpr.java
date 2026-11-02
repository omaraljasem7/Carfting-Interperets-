package de.thm.asc.tiel.interpreter.ast.expr;

public final class IndexExpr extends Expr{
    public final Expr object;
    public final Expr index;

    public IndexExpr(Expr object,Expr index){
        this.object=object;
        this.index=index;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof IndexExpr o)) return false;
        return object.equals(o.object) && index.equals(o.index);
    }
}
