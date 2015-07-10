package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

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

    @Column(name = "feature_function_id", nullable = false, length = 100)
    public String featureFunctionId;
    @Column(name = "function_group", nullable = false)
    public String functionGroup;
    @Column(name = "function_name", nullable = false)
    public String functionName;
    @Column(name = "function_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public FunctionType functionType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "feature_function_id", referencedColumnName = "id")
    public Set<FunctionArgumentDao> functionArgumentDaoSet;

    public FeatureFunctionDao() {
    }

    public FeatureFunctionDao(String featureFunctionId,
                              Set<FunctionArgumentDao> functionArgumentDaoSet,
                              String functionGroup,
                              String functionName,
                              FunctionType functionType) {
        this.featureFunctionId = featureFunctionId;
        this.functionArgumentDaoSet = functionArgumentDaoSet;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FeatureFunctionDao that = (FeatureFunctionDao) o;

        if (featureFunctionId != null ? !featureFunctionId.equals(that.featureFunctionId) : that.featureFunctionId != null)
            return false;
        if (functionGroup != null ? !functionGroup.equals(that.functionGroup) : that.functionGroup != null)
            return false;
        return !(functionName != null ? !functionName.equals(that.functionName) : that.functionName != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (featureFunctionId != null ? featureFunctionId.hashCode() : 0);
        result = 31 * result + (functionGroup != null ? functionGroup.hashCode() : 0);
        result = 31 * result + (functionName != null ? functionName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FeatureFunctionDao{" +
                "functionGroup='" + functionGroup + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionType=" + functionType +
                '}';
    }
}
