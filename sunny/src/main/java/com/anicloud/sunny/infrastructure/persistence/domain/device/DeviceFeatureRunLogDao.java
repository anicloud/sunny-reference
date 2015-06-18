package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhaoyu on 15-6-10.
 */
@Entity
@Table(name = "t_device_feature_run_log")
public class DeviceFeatureRunLogDao extends AbstractEntity {
    private static final long serialVersionUID = -369838151785732290L;

    @Column(name = "feature_run_log_num", nullable = false, unique = true)
    public String deviceFeatureRunLogNum;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    public DeviceDao deviceDao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_feature_id", referencedColumnName = "id")
    public DeviceFeatureDao deviceFeatureDao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id")
    public UserDao owner;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_run_log_id", referencedColumnName = "id")
    public List<LogFunctionValueDao> functionValueDaoList;

    public DeviceFeatureRunLogDao() {
    }

    public DeviceFeatureRunLogDao(DeviceDao deviceDao, DeviceFeatureDao deviceFeatureDao,
                                  String deviceFeatureRunLogNum, List<LogFunctionValueDao> functionValueDaoList, UserDao owner) {
        this.deviceDao = deviceDao;
        this.deviceFeatureDao = deviceFeatureDao;
        this.deviceFeatureRunLogNum = deviceFeatureRunLogNum;
        this.functionValueDaoList = functionValueDaoList;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "DeviceFeatureRunLogDao{id :" + super.id + "}";
    }
}
