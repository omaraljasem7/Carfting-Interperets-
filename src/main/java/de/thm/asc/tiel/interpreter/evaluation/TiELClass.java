package de.thm.asc.tiel.interpreter.evaluation;
import de.thm.asc.tiel.interpreter.lexical.Token;

import java.util.List;
import java.util.Map;
public class TiELClass implements  TiELCallable{
    public final String name;
    public final TiELFunction constructor;
    public final Map<String,TiELFunction> methods;

    public TiELClass(String name, TiELFunction constructor, Map<String, TiELFunction> methods) {
        this.name = name;
        this.constructor = constructor;
        this.methods = methods;
    }
    @Override
    public int arity() {
        return constructor != null ? constructor.arity() : 0;
    }

    @Override
    public TiELValue call(Evaluator evaluator, List<TiELValue> arguments, Token.Position errorPosition) {
        var instance = new TiELInstance(this);
        if (constructor != null) {
            constructor.bind(instance).call(evaluator, arguments, errorPosition);

        }
        return  instance;
    }

}
