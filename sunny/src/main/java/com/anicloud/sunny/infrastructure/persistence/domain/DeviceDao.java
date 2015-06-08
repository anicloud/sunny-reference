package com.anicloud.sunny.infrastructure.persistence.domain;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceState;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_device")
public class DeviceDao extends AbstractEntity {
    private static final long serialVersionUID = 7730145057050493786L;

    public String identificationCode;       // id of device, consist of masterDeviceId and slaveDeviceId
    public String name;
    public DeviceState deviceState;
    public String deviceType;
    public String groupName;

    public UserDao owner;

    public Set<DeviceFeatureDao> featureDaoSet;

    public DeviceDao() {
    }

    public DeviceDao(DeviceState deviceState, String deviceType, String identificationCode,
                     String name, UserDao owner) {
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.identificationCode = identificationCode;
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
