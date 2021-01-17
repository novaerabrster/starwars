package br.com.soulit.starwars.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Table
@Entity
public class SceneSettingCharWordDB implements Serializable {

    private static final long      INTERVAL         = 1L;
                                                    
    private static final long      serialVersionUID = -1627673069197635647L;

    @EmbeddedId
    private SceneSettingCharWordPK id;

    @Column
    private Long                   count;
                                   
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("count", count).toString();
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SceneSettingCharWordDB)) {
            return false;
        }
        final SceneSettingCharWordDB castOther = (SceneSettingCharWordDB) other;
        return new EqualsBuilder().append(id, castOther.id).isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
    
    public SceneSettingCharWordPK getId() {
        return id;
    }

    public void setId(SceneSettingCharWordPK id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void initCounter() {
        count = 1L;
    }
    
    public void addCounter() {
        count = count + INTERVAL;
    }
}
