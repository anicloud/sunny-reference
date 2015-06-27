package com.anicloud.sunny.application.dto.strategy;

import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.anicloud.sunny.application.dto.share.FunctionValueDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureInstanceDto implements Serializable {
    private static final long serialVersionUID = 6137814709577265953L;

    public String featureInstanceNum;
    public DeviceDto deviceDto;
    public DeviceFeatureDto deviceFeatureDto;
    public List<FunctionValueDto> functionValueDtoList;
    public List<FeatureTriggerDto> triggerDtoList;

    public DeviceFeatureInstanceDto() {
    }

    public DeviceFeatureInstanceDto(DeviceDto deviceDto, DeviceFeatureDto deviceFeatureDto,
                                    List<FunctionValueDto> functionValueDtoList,
                                    List<FeatureTriggerDto> triggerDtoList) {
        this.deviceDto = deviceDto;
        this.deviceFeatureDto = deviceFeatureDto;
        this.functionValueDtoList = functionValueDtoList;
        this.triggerDtoList = triggerDtoList;
    }

    public DeviceFeatureInstanceDto(String featureInstanceNum, DeviceDto deviceDto,
                                    DeviceFeatureDto deviceFeatureDto,
                                    List<FunctionValueDto> functionValueDtoList,
                                    List<FeatureTriggerDto> triggerDtoList) {
        this.featureInstanceNum = featureInstanceNum;
        this.deviceDto = deviceDto;
        this.deviceFeatureDto = deviceFeatureDto;
        this.functionValueDtoList = functionValueDtoList;
        this.triggerDtoList = triggerDtoList;
    }

    @Override
    public String toString() {
        return "DeviceFeatureInstanceDto{" +
                "featureInstanceNum='" + featureInstanceNum + '\'' +
                '}';
    }
}
