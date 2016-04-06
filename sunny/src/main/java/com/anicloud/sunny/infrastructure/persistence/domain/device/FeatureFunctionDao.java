package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_feature_function")
public class FeatureFunctionDao extends AbstractEntity {
    private static final long serialVersionUID = -3121941490726390682L;

    @Column(name = "feature_function_id", nullable = false, length = 100)
    public String featureFunctionId;
    @Column(name = "stub_id", nullable = false)
    public Integer stubId;
    @Column(name = "group_id", nullable = false)
    public Long groupId;
    @Column(name = "function_name", nullable = false)
    public String functionName;
    @Column(name = "function_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public FunctionType functionType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "feature_function_id", referencedColumnName = "id")
    public List<FunctionArgumentDao> argumentDaoList;

    public FeatureFunctionDao() {
    }

    public FeatureFunctionDao(String featureFunctionId,
                              Integer stubId, Long groupId,
                              String functionName,
                              FunctionType functionType,
                              List<FunctionArgumentDao> argumentDaoList) {
        this.featureFunctionId = featureFunctionId;
        this.stubId = stubId;
        this.groupId = groupId;
        this.functionName = functionName;
        this.functionType = functionType;
        this.argumentDaoList = argumentDaoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FeatureFunctionDao that = (FeatureFunctionDao) o;
        return Objects.equals(featureFunctionId, that.featureFunctionId) &&
                Objects.equals(stubId, that.stubId) &&
                Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), featureFunctionId, stubId, groupId);
    }

    @Override
    public String toString() {
        return "FeatureFunctionDao{" +
                "featureFunctionId='" + featureFunctionId + '\'' +
                ", stubId=" + stubId +
                ", groupId=" + groupId +
                ", functionName='" + functionName + '\'' +
                ", functionType=" + functionType +
                "} " + super.toString();
    }
}
