package br.com.soulit.starwars.ws.response;

import java.io.Serializable;
import org.apache.commons.lang3.builder.CompareToBuilder;

public class WordCount implements Comparable<WordCount>, Serializable {
    
    private static final long serialVersionUID = 6141310271582980246L;
                                               
    private String            word;
                              
    private Long              count;
                              
    @Override
    public int compareTo(final WordCount other) {
        return new CompareToBuilder().append(other.count, count).toComparison();
    }
    
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
