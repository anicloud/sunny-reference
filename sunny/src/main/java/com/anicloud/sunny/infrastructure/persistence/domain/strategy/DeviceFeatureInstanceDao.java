package com.anicloud.sunny.infrastructure.persistence.domain.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceFeatureDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-11.
 */
@Entity
@Table(name = "t_device_feature_instance")
public class DeviceFeatureInstanceDao extends AbstractEntity {
    private static final long serialVersionUID = -3591470389041916846L;

    @Column(name = "feature_instance_num", nullable = false, unique = true)
    public String featureInstanceNum;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    public DeviceDao deviceDao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_feature_id", referencedColumnName = "id")
    public DeviceFeatureDao deviceFeatureDao;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "device_feature_instance_id", referencedColumnName = "id")
    public List<FeatureInstanceFunctionValueDao> instanceFunctionValueDaoList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "device_feature_instance_id", referencedColumnName = "id")
    public List<FeatureTriggerDao> triggerDaoList;

    @Column(name = "is_schedule_now")
    public boolean isScheduleNow;

    public DeviceFeatureInstanceDao() {
    }

    public DeviceFeatureInstanceDao(String featureInstanceNum, DeviceDao deviceDao, DeviceFeatureDao deviceFeatureDao,
                                    List<FeatureInstanceFunctionValueDao> instanceFunctionValueDaoList,
                                    List<FeatureTriggerDao> triggerDaoList, boolean isScheduleNow) {
        this.featureInstanceNum = featureInstanceNum;
        this.deviceDao = deviceDao;
        this.deviceFeatureDao = deviceFeatureDao;
        this.instanceFunctionValueDaoList = instanceFunctionValueDaoList;
        this.triggerDaoList = triggerDaoList;
        this.isScheduleNow = isScheduleNow;
    }
}
