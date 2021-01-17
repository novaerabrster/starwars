package br.com.soulit.starwars.commons;

public class DuplicateResultException extends StarWarsException {
    
    public DuplicateResultException(String message) {
        super(message);
    }
    
    private static final long serialVersionUID = 3793296853007252588L;
}
