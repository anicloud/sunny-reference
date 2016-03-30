package com.anicloud.sunny.kamili.interfaces.facade;

import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;
import com.anicloud.sunny.kamili.interfaces.facade.dto.DeviceFeatureDto;

import java.util.List;

/**
 * Created by MRK on 2016/3/18.
 */
public interface DeviceFeatureServiceFacade {

    List<DeviceFeatureDto> getAll();
    void delete(DeviceFeatureDto deviceFeatureDto);
    void clearAll();
    void save(DeviceFeatureDto deviceFeatureDto);
    void saveAll(List<DeviceFeatureDto> deviceFeatureDtos);
}
