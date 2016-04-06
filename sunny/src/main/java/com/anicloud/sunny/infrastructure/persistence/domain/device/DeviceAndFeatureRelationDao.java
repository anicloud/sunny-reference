package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-8.
 */
@Entity
@Table(name = "t_relation")
public class DeviceAndFeatureRelationDao extends AbstractEntity {
    private static final long serialVersionUID = -7155254617419368104L;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    public DeviceDao deviceDao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_device_and_feature_relation", inverseJoinColumns = @JoinColumn(name = "device_feature_id"), joinColumns = @JoinColumn(name = "relation_id"))
    public List<DeviceFeatureDao> deviceFeatureDaoList;
    public String featureIdSet = "1:2:4";
    public DeviceAndFeatureRelationDao() {
    }

    public DeviceAndFeatureRelationDao(DeviceDao deviceDao,
                                       List<DeviceFeatureDao> deviceFeatureDaoList) {
        this.deviceDao = deviceDao;
        this.deviceFeatureDaoList = deviceFeatureDaoList;
    }
}
