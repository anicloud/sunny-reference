package com.anicloud.sunny.application.dto.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;

import java.io.Serializable;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FeatureTriggerDto implements Serializable {
    private static final long serialVersionUID = 690722831738860883L;
    public TriggerType triggerType;
    // Json String
    public String value;

    public FeatureTriggerDto() {
    }

    public FeatureTriggerDto(TriggerType triggerType, String value) {
        this.triggerType = triggerType;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FeatureTriggerDto{" +
                "triggerType=" + triggerType +
                ", value='" + value + '\'' +
                '}';
    }
}
