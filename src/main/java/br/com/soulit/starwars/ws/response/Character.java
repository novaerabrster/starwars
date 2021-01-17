package br.com.soulit.starwars.ws.response;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Character implements Serializable {

    private static final long serialVersionUID = -743066543917130361L;

    private Long              id;

    private String            name;

    private List<WordCount>   wordCounts;

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Character)) {
            return false;
        }
        final Character castOther = (Character) other;
        return new EqualsBuilder().append(id, castOther.id).isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
    
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
    
    public List<WordCount> getWordCounts() {
        return wordCounts;
    }
    
    public void setWordCounts(List<WordCount> wordCounts) {
        this.wordCounts = wordCounts;
    }
}
