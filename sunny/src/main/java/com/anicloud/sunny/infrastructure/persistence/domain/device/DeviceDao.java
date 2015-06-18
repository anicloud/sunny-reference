package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceState;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_device")
public class DeviceDao extends AbstractEntity {
    private static final long serialVersionUID = 7730145057050493786L;

    @Column(name = "identification_code", nullable = false, unique = true, length = 150)
    public String identificationCode;       // id of device, consist of masterDeviceId and slaveDeviceId
    @Column(name = "name", nullable = false, length = 100)
    public String name;
    @Column(name = "device_state", nullable = false)
    @Enumerated(EnumType.STRING)
    public DeviceState deviceState;
    @Column(name = "device_type", length = 50)
    public String deviceType;
    @Column(name = "device_group", length = 50)
    public String deviceGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    public UserDao owner;

    public DeviceDao() {
    }

    public DeviceDao(String deviceGroup, DeviceState deviceState, String deviceType,
                     String identificationCode, String name, UserDao owner) {
        this.deviceGroup = deviceGroup;
        this.deviceState = deviceState;
        this.deviceType = deviceType;
        this.identificationCode = identificationCode;
        this.name = name;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceDao deviceDao = (DeviceDao) o;
        return Objects.equals(identificationCode, deviceDao.identificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identificationCode);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
