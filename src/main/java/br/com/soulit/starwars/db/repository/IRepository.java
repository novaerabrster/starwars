package br.com.soulit.starwars.db.repository;

import java.io.Serializable;
import java.util.List;

public interface IRepository<ENTITY> extends Serializable {
    
    public ENTITY save(ENTITY entity);
    
    public List<ENTITY> findAll();

    public ENTITY find(Serializable id);
}
