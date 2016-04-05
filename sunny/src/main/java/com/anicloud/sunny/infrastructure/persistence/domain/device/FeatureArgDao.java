package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.ani.bus.service.commons.dto.anidevice.stub.DataType;
import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.*;

/**
 * Created by zhaoyu on 15-7-10.
 */
@Entity
@Table(name = "t_feature_argument")
public class FeatureArgDao extends AbstractEntity {

    @Column(name = "screen_name", nullable = false, length = 100)
    public String screenName;
    @Column(name = "name", nullable = false, length = 100)
    public String name;
    @Column(name = "data_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public DataType dataType;

    public FeatureArgDao() {
    }

    public FeatureArgDao(DataType dataType, String name, String screenName) {
        this.dataType = dataType;
        this.name = name;
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "FeatureArgDao{" +
                "dataType=" + dataType +
                ", screenName='" + screenName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
