package br.com.soulit.starwars.db.repository;

import org.springframework.stereotype.Repository;
import br.com.soulit.starwars.db.entity.ScriptDB;

@Repository
public class ScriptRepository extends AbstractRepository<ScriptDB> implements IScriptRepository {

    private static final long serialVersionUID = -3029465042857920686L;
    
    public ScriptRepository() {
        super(ScriptDB.class);
    }
}
