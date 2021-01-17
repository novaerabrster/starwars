package br.com.soulit.starwars.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Table
@Entity
public class ScriptDB implements Serializable {
    
    private static final long serialVersionUID = -1024053760769425329L;
                                               
    @Id
    @Column
    private Integer           id;

    // @Column
    // private String text;
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof ScriptDB)) {
            return false;
        }
        final ScriptDB castOther = (ScriptDB) other;
        return new org.apache.commons.lang3.builder.EqualsBuilder().append(id, castOther.id).isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    /*public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }*/
}
