package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.domain.model.device.DeviceAndUserRelation;
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

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    public DeviceAndUserRelationDao deviceAndUserRelationDao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_device_and_feature_relation", inverseJoinColumns = @JoinColumn(name = "device_feature_id"), joinColumns = @JoinColumn(name = "relation_id"))
    public List<DeviceFeatureDao> deviceFeatureDaoList;

    public DeviceAndFeatureRelationDao() {
    }

    public DeviceAndFeatureRelationDao(DeviceAndUserRelationDao deviceAndUserRelationDao,
                                       List<DeviceFeatureDao> deviceFeatureDaoList) {
        this.deviceAndUserRelationDao = deviceAndUserRelationDao;
        this.deviceFeatureDaoList = deviceFeatureDaoList;
    }
}
