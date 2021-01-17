package br.com.soulit.starwars.ws.response;

import java.io.Serializable;
import java.util.List;

public class SceneSettings implements Serializable {

    private static final long serialVersionUID = -2932371907292205007L;
                                               
    private Long              id;
                              
    private String            name;
                              
    private List<Character>   characters;
                              
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
