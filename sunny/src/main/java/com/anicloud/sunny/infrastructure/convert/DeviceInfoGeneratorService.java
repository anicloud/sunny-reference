package com.anicloud.sunny.infrastructure.convert;

import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoyu on 15-7-1.
 */
public abstract class DeviceInfoGeneratorService {
    protected Map<Set<String>, String> deviceTypeGeneratorRule;
    protected Map<Set<String>, String> deviceFeatureGeneratorRule;

    public DeviceInfoGeneratorService() {
        initDeviceTypeGeneratorRule();
        initDeviceFeatureGeneratorRule();
    }

    public abstract void initDeviceTypeGeneratorRule();
    public abstract void initDeviceFeatureGeneratorRule();
    public abstract String generatorDeviceType(DeviceSlaveObjInfoDto slaveInfoDto);

}
