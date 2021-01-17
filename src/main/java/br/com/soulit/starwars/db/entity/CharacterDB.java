package br.com.soulit.starwars.db.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table
public class CharacterDB implements Serializable {

    private static final long            serialVersionUID = 2007536237522588817L;
                                                          
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long                         id;
                                         
    @Column
    private String                       name;
                                         
    @OneToMany
    @JoinColumn(name = "characterId")
    private List<SceneSettingCharWordDB> settingCharWordList;
                                         
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof CharacterDB)) {
            return false;
        }
        final CharacterDB castOther = (CharacterDB) other;
        return new EqualsBuilder().append(name, castOther.name).isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
