package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by wyf on 16-10-11.
 */
@Entity
@Table(
        name = "t_device_user_relation",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"device_id","user_id"})}
)
public class DeviceAndUserRelationDao extends AbstractEntity {
    private static final long serialVersionUID = 8907951743662222918L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    public DeviceDao device;       // id of device, consist of masterDeviceId and slaveDeviceId
    @Column(name = "init_param")
    public String initParam;

    @Column(name = "screen_name", nullable = false, length = 100)
    public String screenName;

    @Column(name = "device_group", length = 50)
    public String deviceGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserDao user;

    public DeviceAndUserRelationDao(DeviceDao device, UserDao user, String initParam, String screenName, String deviceGroup){
        this.device = device;
        this.initParam = initParam;
        this.user = user;
        this.screenName = screenName;
        this.deviceGroup = deviceGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceAndUserRelationDao that = (DeviceAndUserRelationDao) o;
        return Objects.equals(device, that.device) && user.hashUserId.equals(that.user.hashUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), device, user.hashUserId);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
