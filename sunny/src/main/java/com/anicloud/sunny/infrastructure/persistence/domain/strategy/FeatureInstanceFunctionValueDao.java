package com.anicloud.sunny.infrastructure.persistence.domain.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractFunctionValue;

import javax.persistence.*;

/**
 * Created by zhaoyu on 15-6-11.
 */
@Entity
@Table(name = "t_feature_instance_function_value")
public class FeatureInstanceFunctionValueDao extends AbstractFunctionValue {
    private static final long serialVersionUID = 5962247280990020471L;

    public FeatureInstanceFunctionValueDao() {
        super();
    }

    public FeatureInstanceFunctionValueDao(String functionGroup, String functionName,
                                           String argName, String value) {
        super(functionGroup, functionName, argName, value);
    }

    @Override
    public String toString() {
        return "FeatureInstanceFunctionValueDao{" + super.id + "}";
    }
}
