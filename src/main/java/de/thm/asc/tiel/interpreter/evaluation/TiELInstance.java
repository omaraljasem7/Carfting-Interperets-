package de.thm.asc.tiel.interpreter.evaluation;

import java.util.HashMap;
import java.util.Map;
import  de.thm.asc.tiel.interpreter.lexical.Token;

public final class TiELInstance implements  TiELValue {
    public final TiELClass klass;
    private final Map<String,TiELValue> fields=new HashMap<>();

    public TiELInstance(TiELClass klass) {
        this.klass = klass;
    }

    public TiELValue get(String name, Token.Position position){
        if(fields.containsKey(name)){
            return fields.get(name);
        }
        var method=klass.methods.get(name);
        if(method != null)
            return method.bind(this);
        throw new RuntimeException("Undefined property " + name + " . " + position);
    }
    public void set(String name, TiELValue value){
        fields.put(name,value);
    }

}
