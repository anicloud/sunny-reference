package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zhaoyu on 15-7-10.
 */
@Entity
@Table(name = "t_featureArg_functionArg_relation")
public class FeatureArgFunctionArgRelationDao extends AbstractEntity {
    private static final long serialVersionUID = -2253872745778013846L;

    @Column(name = "feature_arg_name", nullable = false, length = 100)
    public String featureArgName;
    @Column(name = "feature_function_id", nullable = false, length = 100)
    public String featureFunctionId;
    @Column(name = "function_arg_name", nullable = false, length = 100)
    public String functionArgName;

    public FeatureArgFunctionArgRelationDao() {
    }

    public FeatureArgFunctionArgRelationDao(String featureArgName, String featureFunctionId, String functionArgName) {
        this.featureArgName = featureArgName;
        this.featureFunctionId = featureFunctionId;
        this.functionArgName = functionArgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FeatureArgFunctionArgRelationDao that = (FeatureArgFunctionArgRelationDao) o;

        if (featureArgName != null ? !featureArgName.equals(that.featureArgName) : that.featureArgName != null)
            return false;
        if (featureFunctionId != null ? !featureFunctionId.equals(that.featureFunctionId) : that.featureFunctionId != null)
            return false;
        return !(functionArgName != null ? !functionArgName.equals(that.functionArgName) : that.functionArgName != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (featureArgName != null ? featureArgName.hashCode() : 0);
        result = 31 * result + (featureFunctionId != null ? featureFunctionId.hashCode() : 0);
        result = 31 * result + (functionArgName != null ? functionArgName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FeatureArgFunctionArgRelationDao{" +
                "featureArgName='" + featureArgName + '\'' +
                ", featureFunctionId='" + featureFunctionId + '\'' +
                ", functionArgName='" + functionArgName + '\'' +
                '}';
    }
}
