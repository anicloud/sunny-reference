package com.anicloud.sunny.kamili.application.service.init;

import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;

import java.io.IOException;
import java.util.List;

/**
 * Created by MRK on 2016/3/17.
 */
public interface DeviceFeatureInitService {

    List<DeviceFeature> initDeviceFeature() throws IOException;
    List<DeviceFeature> getAll() throws IOException;
}
