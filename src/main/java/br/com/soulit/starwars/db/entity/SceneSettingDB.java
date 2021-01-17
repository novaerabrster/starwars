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

@Table
@Entity
public class SceneSettingDB implements Serializable {

    private static final long            serialVersionUID = 4231865569633845188L;
                                                          
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                         id;
                                         
    @Column
    private String                       name;
                                         
    @OneToMany
    @JoinColumn(name = "settingId")
    private List<SceneSettingCharWordDB> settingCharWordList;
                                         
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SceneSettingDB)) {
            return false;
        }
        final SceneSettingDB castOther = (SceneSettingDB) other;
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
    
    public List<SceneSettingCharWordDB> getSettingCharWordList() {
        return settingCharWordList;
    }
    
    public void setSettingCharWordList(List<SceneSettingCharWordDB> settingCharWordList) {
        this.settingCharWordList = settingCharWordList;
    }
}
