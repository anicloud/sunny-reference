package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.share.FunctionType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Set;

/**
 * Created by zhaoyu on 15-6-8.
 */
public class FeatureFunctionDao extends AbstractEntity {
    private static final long serialVersionUID = -3121941490726390682L;

    public String functionGroup;
    public String functionName;
    public FunctionType functionType;
    public Integer order;

    public Set<FunctionArgumentDao> functionArgumentDaoSet;

    public DeviceFeatureDao deviceFeatureDao;

    public FeatureFunctionDao() {
    }

    public FeatureFunctionDao(DeviceFeatureDao deviceFeatureDao, Set<FunctionArgumentDao> functionArgumentDaoSet, String functionGroup, String functionName, FunctionType functionType, Integer order) {
        this.deviceFeatureDao = deviceFeatureDao;
        this.functionArgumentDaoSet = functionArgumentDaoSet;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
        this.order = order;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
