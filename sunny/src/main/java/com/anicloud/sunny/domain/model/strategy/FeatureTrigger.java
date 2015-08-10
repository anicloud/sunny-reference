package com.anicloud.sunny.domain.model.strategy;

import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.FeatureTriggerDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FeatureTrigger extends AbstractDomain implements Cloneable {
    private static final long serialVersionUID = -1004335788561234425L;

    public TriggerType triggerType;
    // Json String
    public String value;

    public FeatureTrigger() {
    }

    public FeatureTrigger(String value, TriggerType triggerType) {
        this.value = value;
        this.triggerType = triggerType;
    }

    public static FeatureTrigger toFeatureTrigger(FeatureTriggerDao triggerDao) {
        FeatureTrigger trigger = new FeatureTrigger(
                triggerDao.value,
                triggerDao.triggerType
        );
        return trigger;
    }

    public static FeatureTriggerDao toDao(FeatureTrigger featureTrigger) {
        FeatureTriggerDao triggerDao = new FeatureTriggerDao(
                featureTrigger.triggerType,
                featureTrigger.value
        );
        return triggerDao;
    }

    public static List<FeatureTrigger> toFeatureTriggerList(List<FeatureTriggerDao> triggerDaoList) {
        List<FeatureTrigger> triggerList = new ArrayList<>();
        for (FeatureTriggerDao triggerDao : triggerDaoList) {
            triggerList.add(toFeatureTrigger(triggerDao));
        }
        return triggerList;
    }

    public static List<FeatureTriggerDao> toDaoList(List<FeatureTrigger> triggerList) {
        List<FeatureTriggerDao> daoList = new ArrayList<>();
        for (FeatureTrigger featureTrigger : triggerList) {
            daoList.add(toDao(featureTrigger));
        }
        return daoList;
    }

    @Override
    protected FeatureTrigger clone() {
        FeatureTrigger featureTrigger = null;
        try {
            featureTrigger = (FeatureTrigger) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return featureTrigger;
    }

    @Override
    public String toString() {
        return "FeatureTrigger{" +
                "triggerType=" + triggerType +
                ", value='" + value + '\'' +
                '}';
    }
}
