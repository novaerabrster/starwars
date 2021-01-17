package br.com.soulit.starwars.commons;

public class StarWarsException extends Exception {
    
    private static final long serialVersionUID = -396629385400577462L;

    public StarWarsException(String message) {
        super(message);
    }
}
