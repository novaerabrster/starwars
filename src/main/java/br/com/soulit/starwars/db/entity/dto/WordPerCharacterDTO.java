package br.com.soulit.starwars.db.entity.dto;

public class WordPerCharacterDTO {

    private final Long id;

    private String     word;

    private Long       count;

    public WordPerCharacterDTO(Long id, String word, Long count) {
        super();
        this.id = id;
        this.word = word;
        this.count = count;
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
    
    public Long getId() {
        return id;
    }
}
