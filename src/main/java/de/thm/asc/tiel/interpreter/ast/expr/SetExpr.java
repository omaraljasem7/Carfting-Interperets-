package de.thm.asc.tiel.interpreter.ast.expr;

/*
 * if a is an instance of some class and name is a field, then a.name ="X"
 * in this case a is the object from Type Expr
 * name is  of Type String ,
 * value of type Expr
 */
public final class SetExpr extends Expr{
    public final Expr object;
    public final String name;
    public final Expr value;

    public SetExpr(Expr object, String name,Expr value ){
        this.object = object ;
        this.name = name;
        this.value = value;
    }
}
