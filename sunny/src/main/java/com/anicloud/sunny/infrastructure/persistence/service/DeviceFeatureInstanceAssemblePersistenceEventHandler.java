package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.domain.model.strategy.DeviceFeatureInstanceAssemble;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceAssembleDao;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceFeatureRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.strategy.DeviceFeatureInstanceAssembleRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.strategy.DeviceFeatureInstanceRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
@Component
public class DeviceFeatureInstanceAssemblePersistenceEventHandler implements
        DeviceFeatureInstanceAssemblePersistenceService {

    @Resource
    private DeviceFeatureInstanceAssembleRepository deviceFeatureInstanceAssembleRepository;
    @Resource
    private DeviceFeatureInstanceRepository deviceFeatureInstanceRepository;

    @Override
    public DeviceFeatureInstanceAssembleDao save(DeviceFeatureInstanceAssembleDao dao) {
        DeviceFeatureInstanceDao fatherInstance = deviceFeatureInstanceRepository
                .findByFeatureInstanceNum(dao.fatherFeatureInstanceDao.featureInstanceNum);
        DeviceFeatureInstanceDao leafInstance = deviceFeatureInstanceRepository
                .findByFeatureInstanceNum(dao.assembleFeatureInstanceDao.featureInstanceNum);

        dao.fatherFeatureInstanceDao = fatherInstance;
        dao.assembleFeatureInstanceDao = leafInstance;
        return deviceFeatureInstanceAssembleRepository.save(dao);
    }

    @Override
    public List<DeviceFeatureInstanceAssembleDao> getFatherInstanceList(String featherInstanceNum) {
        return deviceFeatureInstanceAssembleRepository.getFatherInstanceList(featherInstanceNum);
    }

    @Override
    public List<DeviceFeatureInstanceAssembleDao> getLeafInstanceList(String featherInstanceNum) {
        return deviceFeatureInstanceAssembleRepository.getLeafInstanceList(featherInstanceNum);
    }
}
