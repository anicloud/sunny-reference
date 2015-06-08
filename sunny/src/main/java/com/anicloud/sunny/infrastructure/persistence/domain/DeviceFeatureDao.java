package com.anicloud.sunny.infrastructure.persistence.domain;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Set;

/**
 * Created by zhaoyu on 15-6-8.
 */
public class DeviceFeatureDao extends AbstractEntity {
    private static final long serialVersionUID = 8025939205798572709L;

    public String featureName;
    public String description;

    public Set<FeatureFunctionDao> featureFunctionDaoSet;

    public DeviceFeatureDao() {
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
