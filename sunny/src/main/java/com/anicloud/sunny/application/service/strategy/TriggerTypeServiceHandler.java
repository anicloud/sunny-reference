package com.anicloud.sunny.application.service.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.share.TriggerType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
@Service
public class TriggerTypeServiceHandler implements TriggerTypeService {
    @Override
    public List<String> getAllTriggerType() {
        List<String> stringList = new ArrayList<>();
        EnumSet<TriggerType> triggerTypeEnumSet = EnumSet.allOf(TriggerType.class);
        for (TriggerType triggerType : triggerTypeEnumSet) {
            stringList.add(triggerType.name());
        }
        return stringList;
    }
}
