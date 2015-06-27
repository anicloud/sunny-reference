package com.anicloud.sunny.application.dto.device;

import com.anicloud.sunny.application.dto.share.FunctionValueDto;
import com.anicloud.sunny.application.dto.user.UserDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureRunLogDto implements Serializable {
    private static final long serialVersionUID = 7756965458831824111L;

    public String deviceFeatureRunLogNum;
    public DeviceDto deviceDto;
    public DeviceFeatureDto deviceFeatureDto;

    public UserDto owner;
    public List<FunctionValueDto> functionValueDtoList;

    public DeviceFeatureRunLogDto() {
    }

    public DeviceFeatureRunLogDto(DeviceDto deviceDto, DeviceFeatureDto deviceFeatureDto,
                                  String deviceFeatureRunLogNum,
                                  List<FunctionValueDto> functionValueDtoList, UserDto owner) {
        this.deviceDto = deviceDto;
        this.deviceFeatureDto = deviceFeatureDto;
        this.deviceFeatureRunLogNum = deviceFeatureRunLogNum;
        this.functionValueDtoList = functionValueDtoList;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "DeviceFeatureRunLogDto{" +
                "deviceFeatureRunLogNum='" + deviceFeatureRunLogNum + '\'' +
                '}';
    }
}
