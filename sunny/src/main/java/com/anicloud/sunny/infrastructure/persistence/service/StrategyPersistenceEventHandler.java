package com.anicloud.sunny.infrastructure.persistence.service;

import com.anicloud.sunny.application.utils.NumGenerate;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceFeatureRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.device.DeviceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.strategy.DeviceFeatureInstanceRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.strategy.StrategyRepository;
import com.anicloud.sunny.infrastructure.persistence.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-17.
 */
@Component
public class StrategyPersistenceEventHandler implements StrategyPersistenceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(StrategyPersistenceEventHandler.class);

    @Resource
    private UserRepository userRepository;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private DeviceFeatureRepository deviceFeatureRepository;
    @Resource
    private StrategyRepository strategyRepository;
    @Resource
    private DeviceFeatureInstanceRepository deviceFeatureInstanceRepository;

    @Override
    public StrategyDao save(StrategyDao strategyDao) {
        UserDao userDao = userRepository.findByHashUserId(strategyDao.owner.hashUserId);
        List<DeviceFeatureInstanceDao> list = batchInsertInstance(strategyDao.deviceFeatureInstanceDaoList);

        strategyDao.deviceFeatureInstanceDaoList = list;
        strategyDao.owner = userDao;
        return strategyRepository.save(strategyDao);
    }

    @Override
    public StrategyDao modify(StrategyDao strategyDao) {
        StrategyDao orgStrategyDao = strategyRepository.findByStrategyNum(strategyDao.strategyId);
        orgStrategyDao.strategyName = strategyDao.strategyName;
        orgStrategyDao.description = strategyDao.description;
        return orgStrategyDao;
    }

    @Override
    public void removeById(String strategyId) {
        StrategyDao strategyDao = strategyRepository.findByStrategyNum(strategyId);
        strategyRepository.delete(strategyDao);
    }

    @Override
    public StrategyDao getStrategyById(String strategyId) {
        return strategyRepository.findByStrategyNum(strategyId);
    }

    @Override
    public List<StrategyDao> getStrategyListByUser(String hashUserId) {
        return strategyRepository.findByUserHashId(hashUserId);
    }

    private List<DeviceFeatureInstanceDao> batchInsertInstance(List<DeviceFeatureInstanceDao> instanceDaoList) {
        List<DeviceFeatureInstanceDao> daoList = new ArrayList<>();
        for (DeviceFeatureInstanceDao instanceDao : instanceDaoList) {
            DeviceFeatureInstanceDao featureInstanceDao = saveFeatureInstance(instanceDao);
            daoList.add(featureInstanceDao);
        }
        return daoList;
    }

    private DeviceFeatureInstanceDao saveFeatureInstance(DeviceFeatureInstanceDao instanceDao) {
        DeviceDao deviceDao = deviceRepository.findByIdentificationCode(instanceDao.deviceDao.identificationCode);
        DeviceFeatureDao deviceFeatureDao = deviceFeatureRepository.findByFeatureId(instanceDao.deviceFeatureDao.featureId);

        instanceDao.deviceDao = deviceDao;
        instanceDao.deviceFeatureDao = deviceFeatureDao;

        // generate the feature instance number
        instanceDao.featureInstanceNum = NumGenerate.generate();
        LOGGER.debug("generate the device feature instance num {}.", instanceDao.featureInstanceNum);

        return deviceFeatureInstanceRepository.save(instanceDao);
    }
}
