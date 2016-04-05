package com.anicloud.sunny.application.service.init;

import com.ani.bus.service.commons.dto.anidevice.DeviceSlaveObjInfoDto;
import org.springframework.stereotype.Component;

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */

@Component(value = "deviceTypeGenerator")
public class DeviceTypeGeneratorImpl extends DeviceTypeGenerator {

    @Override
    protected void initDeviceGeneratorRule() {
        // read rule from json file
    }

    @Override
    public String generatorDeviceType(DeviceSlaveObjInfoDto deviceSlaveObjInfoDto) {
        return null;
    }
}
