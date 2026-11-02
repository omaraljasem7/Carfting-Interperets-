package de.thm.asc.tiel.interpreter.ast.stmt;
import java.util.List;
import java.util.Objects;

public final class ClassDeclStmt extends Stmt{
    // name of the class
    public final String name;
    // constructor is nothing rather than a FunctionDeclStmt
    public final FunctionDeclStmt constructor;
    // the methods are a list of FunctionDeclStmt

    public final List<FunctionDeclStmt> methods;



    public ClassDeclStmt(String name , FunctionDeclStmt constructor, List<FunctionDeclStmt> methods) {
        this.name=name;
        this.constructor=constructor;
        this.methods=methods;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ClassDeclStmt o)) return false;
        return Objects.equals(name, o.name)
                && Objects.equals(constructor, o.constructor)
                && Objects.equals(methods, o.methods);
    }


}
