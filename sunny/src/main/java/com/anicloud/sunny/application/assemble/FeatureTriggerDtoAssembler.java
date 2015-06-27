package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.FeatureFunctionDto;
import com.anicloud.sunny.application.dto.strategy.FeatureTriggerDto;
import com.anicloud.sunny.domain.model.strategy.FeatureTrigger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
public class FeatureTriggerDtoAssembler {
    private FeatureTriggerDtoAssembler() {}

    public static FeatureTrigger toFeatureTrigger(FeatureTriggerDto featureTriggerDto) {
        if (featureTriggerDto == null) {
            return null;
        }

        FeatureTrigger featureTrigger = new FeatureTrigger(
                featureTriggerDto.value,
                featureTriggerDto.triggerType
        );
        return featureTrigger;
    }

    public static FeatureTriggerDto toDto(FeatureTrigger featureTrigger) {
        if (featureTrigger == null) {
            return null;
        }

        FeatureTriggerDto featureTriggerDto = new FeatureTriggerDto(
                featureTrigger.triggerType,
                featureTrigger.value
        );
        return featureTriggerDto;
    }

    public static List<FeatureTrigger> toFeatureTriggerList(List<FeatureTriggerDto> featureTriggerDtoList) {
        List<FeatureTrigger> triggerList = new ArrayList<FeatureTrigger>();
        for (FeatureTriggerDto featureTriggerDto : featureTriggerDtoList) {
            triggerList.add(toFeatureTrigger(featureTriggerDto));
        }
        return triggerList;
    }

    public static List<FeatureTriggerDto> toDtoList(List<FeatureTrigger> triggerList) {
        List<FeatureTriggerDto> triggerDtoList = new ArrayList<FeatureTriggerDto>();
        for (FeatureTrigger featureTrigger : triggerList) {
            triggerDtoList.add(toDto(featureTrigger));
        }
        return triggerDtoList;
    }
}
