package com.anicloud.sunny.schedule.persistence.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by zhaoyu on 15-6-8.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = -8151169333140380795L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public AbstractEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
