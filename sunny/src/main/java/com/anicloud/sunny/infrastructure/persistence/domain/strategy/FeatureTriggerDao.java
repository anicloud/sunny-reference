package com.anicloud.sunny.infrastructure.persistence.domain.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import org.springframework.scheduling.Trigger;

import javax.persistence.*;

/**
 * Created by zhaoyu on 15-6-11.
 */
@Entity
@Table(name = "t_feature_trigger")
public class FeatureTriggerDao extends AbstractEntity {
    private static final long serialVersionUID = 6712275754896842612L;

    @Column(name = "trigger_type")
    @Enumerated(EnumType.STRING)
    public TriggerType triggerType;

    // json string
    @Column(name = "value")
    public String value;

    public FeatureTriggerDao() {
    }

    public FeatureTriggerDao(TriggerType triggerType, String value) {
        this.triggerType = triggerType;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FeatureTriggerDao{" +
                "triggerType=" + triggerType +
                ", value='" + value + '\'' +
                '}';
    }
}
