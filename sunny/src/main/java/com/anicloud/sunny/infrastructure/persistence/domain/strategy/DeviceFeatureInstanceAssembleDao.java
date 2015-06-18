package com.anicloud.sunny.infrastructure.persistence.domain.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-11.
 */
@Entity
@Table(name = "t_device_feature_instance_assemble")
public class DeviceFeatureInstanceAssembleDao extends AbstractEntity {
    private static final long serialVersionUID = 5490671342819491815L;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "father_feature_instance_id", referencedColumnName = "id")
    public DeviceFeatureInstanceDao fatherFeatureInstanceDao;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assemble_feature_instance_id", referencedColumnName = "id")
    public DeviceFeatureInstanceDao assembleFeatureInstanceDao;

    public DeviceFeatureInstanceAssembleDao() {
    }

    public DeviceFeatureInstanceAssembleDao(DeviceFeatureInstanceDao fatherFeatureInstanceDao,
                                            DeviceFeatureInstanceDao assembleFeatureInstanceDao) {
        this.assembleFeatureInstanceDao = assembleFeatureInstanceDao;
        this.fatherFeatureInstanceDao = fatherFeatureInstanceDao;
    }
}
