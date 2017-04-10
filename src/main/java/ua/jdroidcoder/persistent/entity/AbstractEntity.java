package ua.jdroidcoder.persistent.entity;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@MappedSuperclass
public class AbstractEntity<PK extends Serializable> implements Persistable<PK> {
    private static long serialVersionUID = 4646464645484896L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private PK id;

    public PK getId() {
        return id;
    }

    protected void setId(final PK id) {

        this.id = id;
    }

    @Transient
    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity<?> that = (AbstractEntity<?>) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}