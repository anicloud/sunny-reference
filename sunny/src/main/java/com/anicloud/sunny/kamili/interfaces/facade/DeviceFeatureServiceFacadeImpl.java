package com.anicloud.sunny.kamili.interfaces.facade;

import com.anicloud.sunny.kamili.application.service.device.DeviceFeatureService;
import com.anicloud.sunny.kamili.interfaces.facade.adapter.DeviceFeatureDtoAdapter;
import com.anicloud.sunny.kamili.interfaces.facade.dto.DeviceFeatureDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MRK on 2016/3/18.
 */
@Component
public class DeviceFeatureServiceFacadeImpl implements DeviceFeatureServiceFacade {

    @Resource
    private DeviceFeatureService deviceFeatureService;

    @Override
    public List<DeviceFeatureDto> getAll() {
        return DeviceFeatureDtoAdapter.listToDto(deviceFeatureService.getAll());
    }

    @Override
    public void delete(DeviceFeatureDto deviceFeatureDto) {
        if (deviceFeatureDto != null) {
            deviceFeatureService.delete(DeviceFeatureDtoAdapter.toDomain(deviceFeatureDto));
        }
    }

    @Override
    public void clearAll() {
        deviceFeatureService.clearAll();
    }

    @Override
    public void save(DeviceFeatureDto deviceFeatureDto) {
        if (deviceFeatureDto != null) {
            deviceFeatureService.save(DeviceFeatureDtoAdapter.toDomain(deviceFeatureDto));
        }
    }

    @Override
    public void saveAll(List<DeviceFeatureDto> deviceFeatureDtos) {
        if (deviceFeatureDtos != null && deviceFeatureDtos.size() > 0) {
            deviceFeatureService.saveAll(DeviceFeatureDtoAdapter.listToDomain(deviceFeatureDtos));
        }
    }
}
