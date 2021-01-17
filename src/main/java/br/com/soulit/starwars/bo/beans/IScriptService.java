package br.com.soulit.starwars.bo.beans;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import br.com.soulit.starwars.db.entity.ScriptDB;

public interface IScriptService extends Serializable {
    
    ScriptDB find(String text);

    void parse(String script) throws ExecutionException;
    
    void save(String text);
}
