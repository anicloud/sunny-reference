package com.anicloud.sunny.domain.model.strategy;

import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.DeviceFeatureInstanceAssembleDao;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceFeatureInstanceAssemblePersistenceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class DeviceFeatureInstanceAssemble extends AbstractDomain {
    private static final long serialVersionUID = -4012382042640700384L;

    public DeviceFeatureInstance fatherInstance;
    public DeviceFeatureInstance assembleInstance;

    public DeviceFeatureInstanceAssemble() {
    }

    public DeviceFeatureInstanceAssemble(DeviceFeatureInstance fatherInstance, DeviceFeatureInstance assembleInstance) {
        this.fatherInstance = fatherInstance;
        this.assembleInstance = assembleInstance;
    }

    public static DeviceFeatureInstanceAssemble save(DeviceFeatureInstanceAssemblePersistenceService persistenceService,
                                                     DeviceFeatureInstanceAssemble assemble) {
        DeviceFeatureInstanceAssembleDao assembleDao = persistenceService.save(toDao(assemble));
        return toAssemble(assembleDao);
    }

    public static List<DeviceFeatureInstanceAssemble> getFatherInstanceList(DeviceFeatureInstanceAssemblePersistenceService persistenceService,
                                                                            String featureInstanceNum) {
        List<DeviceFeatureInstanceAssembleDao> assembleList = persistenceService.getFatherInstanceList(featureInstanceNum);
        return toAssembleList(assembleList);
    }

    public static List<DeviceFeatureInstanceAssemble> getLeafInstanceList(DeviceFeatureInstanceAssemblePersistenceService persistenceService,
                                                                          String featureInstanceNum) {
        List<DeviceFeatureInstanceAssembleDao> assembleDaoList = persistenceService.getLeafInstanceList(featureInstanceNum);
        return toAssembleList(assembleDaoList);
    }

    public static DeviceFeatureInstanceAssemble toAssemble(DeviceFeatureInstanceAssembleDao assembleDao) {
        DeviceFeatureInstanceAssemble  instanceAssemble =
                new DeviceFeatureInstanceAssemble(
                        DeviceFeatureInstance.toInstance(assembleDao.fatherFeatureInstanceDao),
                        DeviceFeatureInstance.toInstance(assembleDao.assembleFeatureInstanceDao)
                );
        return instanceAssemble;
    }

    public static DeviceFeatureInstanceAssembleDao toDao(DeviceFeatureInstanceAssemble assemble) {
        DeviceFeatureInstanceAssembleDao assembleDao = new DeviceFeatureInstanceAssembleDao(
                DeviceFeatureInstance.toDao(assemble.fatherInstance),
                DeviceFeatureInstance.toDao(assemble.assembleInstance)

        );
        return assembleDao;
    }

    public static List<DeviceFeatureInstanceAssemble> toAssembleList(List<DeviceFeatureInstanceAssembleDao> assembleDaoList) {
        List<DeviceFeatureInstanceAssemble> assembleList = new ArrayList<>();
        for (DeviceFeatureInstanceAssembleDao assembleDao : assembleDaoList) {
            assembleList.add(toAssemble(assembleDao));
        }
        return assembleList;
    }

    public static List<DeviceFeatureInstanceAssembleDao> toDaoList(List<DeviceFeatureInstanceAssemble> assembleList) {
        List<DeviceFeatureInstanceAssembleDao> assembleDaoList = new ArrayList<>();
        for (DeviceFeatureInstanceAssemble assemble : assembleList) {
            assembleDaoList.add(toDao(assemble));
        }
        return assembleDaoList;
    }
}
