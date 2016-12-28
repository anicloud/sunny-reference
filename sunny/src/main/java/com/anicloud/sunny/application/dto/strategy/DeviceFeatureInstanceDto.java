package com.anicloud.sunny.application.dto.strategy;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.device.FeatureArgValueDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureInstanceDto implements Serializable {
    private static final long serialVersionUID = 6137814709577265953L;

    public String featureInstanceId;
    public DeviceDto deviceDto;
    public DeviceFeatureDto deviceFeatureDto;
    public List<FeatureArgValueDto> featureArgValueDtoList;
    public List<FeatureTriggerDto> triggerDtoList;

    public boolean isScheduleNow;
    public Long intervalTime;

    public DeviceFeatureInstanceDto() {
    }

    public DeviceFeatureInstanceDto(DeviceDto deviceDto, DeviceFeatureDto deviceFeatureDto,
                                    List<FeatureArgValueDto> featureArgValueDtoList,
                                    List<FeatureTriggerDto> triggerDtoList) {
        this.deviceDto = deviceDto;
        this.deviceFeatureDto = deviceFeatureDto;
        this.featureArgValueDtoList = featureArgValueDtoList;
        this.triggerDtoList = triggerDtoList;
    }

//    public DeviceFeatureInstanceDto(String featureInstanceId, DeviceDto deviceDto,
//                                    DeviceFeatureDto deviceFeatureDto,
//                                    List<FeatureArgValueDto> featureArgValueDtoList,
//                                    List<FeatureTriggerDto> triggerDtoList,
//                                    boolean isScheduleNow) {
//        this.featureInstanceId = featureInstanceId;
//        this.deviceDto = deviceDto;
//        this.deviceFeatureDto = deviceFeatureDto;
//        this.featureArgValueDtoList = featureArgValueDtoList;
//        this.triggerDtoList = triggerDtoList;
//        this.isScheduleNow = isScheduleNow;
//    }

    public DeviceFeatureInstanceDto(String featureInstanceId, DeviceDto deviceDto,
                                    DeviceFeatureDto deviceFeatureDto,
                                    List<FeatureArgValueDto> featureArgValueDtoList,
                                    List<FeatureTriggerDto> triggerDtoList,
                                    boolean isScheduleNow, Long intervalTime) {
        this.featureInstanceId = featureInstanceId;
        this.deviceDto = deviceDto;
        this.deviceFeatureDto = deviceFeatureDto;
        this.featureArgValueDtoList = featureArgValueDtoList;
        this.triggerDtoList = triggerDtoList;
        this.isScheduleNow = isScheduleNow;
        this.intervalTime = intervalTime;
    }

    @Override
    public String toString() {
        return "DeviceFeatureInstanceDto{" +
                "featureInstanceId='" + featureInstanceId + '\'' +
                '}';
    }
}
