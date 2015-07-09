package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_device_feature")
public class DeviceFeatureDao extends AbstractEntity {
    private static final long serialVersionUID = 8025939205798572709L;

    @Column(name = "feature_num", nullable = false, unique = true, length = 100)
    public String featureNum;
    @Column(name = "feature_name", nullable = false, unique = true, length = 150)
    public String featureName;
    @Column(name = "description", length = 255)
    public String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "device_feature_id", referencedColumnName = "id")
    public List<FeatureFunctionDao> featureFunctionDaoList;

    public DeviceFeatureDao() {
    }

    public DeviceFeatureDao(String description, List<FeatureFunctionDao> featureFunctionDaoList,
                            String featureName, String featureNum) {
        this.description = description;
        this.featureFunctionDaoList = featureFunctionDaoList;
        this.featureName = featureName;
        this.featureNum = featureNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceFeatureDao that = (DeviceFeatureDao) o;
        return Objects.equals(featureNum, that.featureNum) &&
                Objects.equals(featureName, that.featureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), featureNum, featureName);
    }

    @Override
    public String toString() {
        return "DeviceFeatureDao{" +
                "description='" + description + '\'' +
                ", featureNum='" + featureNum + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
