package de.thm.asc.tiel.interpreter.ast.expr;

//for example for this if a is an instance variable of a class
// then a.name -> a is the object , name is
public final class GetExpr  extends Expr{
    public final Expr object;
    public final String name;

    public GetExpr(Expr object,  String name){
        this.object = object;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof GetExpr o)) return false;

        return object.equals(o.object) && name.equals(o.name);
    }
}
