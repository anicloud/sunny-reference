package com.anicloud.sunny.application.service.init;

import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import com.anicloud.sunny.domain.model.device.StubIdentity;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
public abstract class DeviceTypeGenerator {
    protected Map<String, Set<StubIdentity>> deviceTypeGeneratorRule;

    @PostConstruct
    protected abstract void initDeviceGeneratorRule();
    public abstract String generatorDeviceType(DeviceSlaveObjInfoDto deviceSlaveObjInfoDto);
}
