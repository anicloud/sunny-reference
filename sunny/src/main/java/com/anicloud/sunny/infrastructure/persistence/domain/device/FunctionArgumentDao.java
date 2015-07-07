package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.ani.cel.service.manager.agent.core.share.DataType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.*;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_function_argument")
public class FunctionArgumentDao extends AbstractEntity{
    private static final long serialVersionUID = 5254836963082579939L;

    @Column(name = "name", nullable = false, length = 100)
    public String name;
    @Column(name = "data_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public DataType dataType;

    public FunctionArgumentDao() {
    }

    public FunctionArgumentDao(DataType dataType, String name) {
        this.dataType = dataType;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FunctionArgumentDao that = (FunctionArgumentDao) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FunctionArgumentDao{" +
                "dataType=" + dataType +
                ", name='" + name + '\'' +
                '}';
    }
}
