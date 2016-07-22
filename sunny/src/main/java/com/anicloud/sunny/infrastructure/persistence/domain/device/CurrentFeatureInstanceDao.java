package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;


/**
 * Created by lihui on 16-7-19.
 */
@Entity
@Table(name="t_current_feature")
public class CurrentFeatureInstanceDao extends AbstractEntity {

    private static final long serialVersionUID = -4231107078768981471L;

    @Column(name="feature_id",nullable = false)
    public String featureId;

    @Column(name="device_id",nullable = false)
    public String deviceId;

    @Column(name="device_num",nullable = false)
    public Integer deviceNum;

    @Column(name = "hash_user_id",nullable = false)
    public Long hashUserId;

    public CurrentFeatureInstanceDao(String featureId, String deviceId, Integer deviceNum, Long hashUserId) {
        this.deviceId = deviceId;
        this.featureId = featureId;
        this.hashUserId = hashUserId;
        this.deviceNum = deviceNum;
    }
    public CurrentFeatureInstanceDao(){

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CurrentFeatureInstanceDao that = (CurrentFeatureInstanceDao) o;

        if (featureId != null ? !featureId.equals(that.featureId) : that.featureId != null) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (deviceNum != null ? !deviceNum.equals(that.deviceNum) : that.deviceNum != null) return false;
        return hashUserId != null ? hashUserId.equals(that.hashUserId) : that.hashUserId == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (featureId != null ? featureId.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (deviceNum != null ? deviceNum.hashCode() : 0);
        result = 31 * result + (hashUserId != null ? hashUserId.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "CurrentFeatureInstanceDao{" +
                "featureId='" + featureId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceNum=" + deviceNum +
                ", hashUserId=" + hashUserId +
                '}';
    }

}
