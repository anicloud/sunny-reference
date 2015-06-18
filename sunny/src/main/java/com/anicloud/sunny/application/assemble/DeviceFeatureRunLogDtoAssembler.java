package com.anicloud.sunny.application.assemble;

import com.anicloud.sunny.application.dto.device.DeviceFeatureRunLogDto;
import com.anicloud.sunny.application.dto.share.FunctionValueDto;
import com.anicloud.sunny.domain.model.device.Device;
import com.anicloud.sunny.domain.model.device.DeviceFeatureRunLog;
import com.anicloud.sunny.domain.model.share.FunctionValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-16.
 */
public class DeviceFeatureRunLogDtoAssembler {
    private DeviceFeatureRunLogDtoAssembler() {}

    public static DeviceFeatureRunLog toFeatureRunLog(DeviceFeatureRunLogDto logDto) {
        if (logDto == null) {
            return null;
        }

        DeviceFeatureRunLog featureRunLog = new DeviceFeatureRunLog(
                DeviceDtoAssembler.toDevice(logDto.deviceDto),
                DeviceFeatureDtoAssembler.toDeviceFeature(logDto.deviceFeatureDto),
                logDto.deviceFeatureRunLogNum,
                FunctionValueDtoAssembler.toFunctionValueList(logDto.functionValueDtoList),
                UserDtoAssembler.toUser(logDto.owner)
        );
        return featureRunLog;
    }

    public static DeviceFeatureRunLogDto toDto(DeviceFeatureRunLog runLog) {
        if (runLog == null) {
            return null;
        }

        DeviceFeatureRunLogDto runLogDto = new DeviceFeatureRunLogDto(
                DeviceDtoAssembler.fromDevice(runLog.device),
                DeviceFeatureDtoAssembler.toDto(runLog.deviceFeature),
                runLog.deviceFeatureRunLogNum,
                FunctionValueDtoAssembler.toFunctionValueDtoList(runLog.functionValueList),
                UserDtoAssembler.fromUser(runLog.owner)
        );
        return runLogDto;
    }

    public static List<DeviceFeatureRunLogDto> toDtoList(List<DeviceFeatureRunLog> runLogList) {
        List<DeviceFeatureRunLogDto> logDtoList = new ArrayList<>();
        for (DeviceFeatureRunLog featureRunLog : runLogList) {
            logDtoList.add(toDto(featureRunLog));
        }
        return logDtoList;
    }
}
