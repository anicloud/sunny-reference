package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.share.FunctionType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_feature_function")
public class FeatureFunctionDao extends AbstractEntity {
    private static final long serialVersionUID = -3121941490726390682L;

    @Column(name = "function_group", nullable = false)
    public String functionGroup;
    @Column(name = "function_name", nullable = false)
    public String functionName;
    @Column(name = "function_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public FunctionType functionType;
    @Column(name = "sequence_num", nullable = false)
    public Integer sequenceNum;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "feature_function_id", referencedColumnName = "id")
    public Set<FunctionArgumentDao> functionArgumentDaoSet;

    public FeatureFunctionDao() {
    }

    public FeatureFunctionDao(Set<FunctionArgumentDao> functionArgumentDaoSet,
                              String functionGroup, String functionName,
                              FunctionType functionType, Integer sequenceNum) {
        this.functionArgumentDaoSet = functionArgumentDaoSet;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
        this.sequenceNum = sequenceNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FeatureFunctionDao that = (FeatureFunctionDao) o;
        return Objects.equals(functionGroup, that.functionGroup) &&
                Objects.equals(functionName, that.functionName) &&
                Objects.equals(sequenceNum, that.sequenceNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), functionGroup, functionName, sequenceNum);
    }

    @Override
    public String toString() {
        return "FeatureFunctionDao{" +
                "functionGroup='" + functionGroup + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionType=" + functionType +
                ", sequenceNum=" + sequenceNum +
                '}';
    }
}
