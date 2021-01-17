package br.com.soulit.starwars.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class SceneSettingCharWordPK implements Serializable {
    
    private static final long serialVersionUID = 4011600944236730881L;
                                               
    @Column
    private Long              settingId;
                              
    @Column
    private Long              characterId;
                              
    @Column
    private String            word;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("settingId", settingId).append("characterId", characterId)
                .append("word", word).toString();
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SceneSettingCharWordPK)) {
            return false;
        }
        final SceneSettingCharWordPK castOther = (SceneSettingCharWordPK) other;
        return new EqualsBuilder().append(settingId, castOther.settingId).append(characterId, castOther.characterId)
                .append(word, castOther.word).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(settingId).append(characterId).append(word).toHashCode();
    }

    public Long getSettingId() {
        return settingId;
    }
    
    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }
    
    public Long getCharacterId() {
        return characterId;
    }
    
    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }
    
    public String getWord() {
        return word;
    }
    
    public void setWord(String word) {
        this.word = word;
    }
}
