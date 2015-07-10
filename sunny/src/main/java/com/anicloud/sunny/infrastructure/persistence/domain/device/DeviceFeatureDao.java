package com.anicloud.sunny.infrastructure.persistence.domain.device;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-8.
 */
@Entity
@Table(name = "t_device_feature")
public class DeviceFeatureDao extends AbstractEntity {
    private static final long serialVersionUID = 8025939205798572709L;

    @Column(name = "feature_Id", nullable = false, unique = true, length = 100)
    public String featureId;
    @Column(name = "feature_name", nullable = false, unique = true, length = 150)
    public String featureName;
    @Column(name = "description", length = 255)
    public String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "device_feature_id", referencedColumnName = "id")
    public List<FeatureArgDao> featureArgDaoList;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "device_feature_id", referencedColumnName = "id")
    public List<FeatureFunctionDao> featureFunctionDaoList;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "device_feature_id", referencedColumnName = "id")
    public List<FeatureArgFunctionArgRelationDao> argRelationDaoList;

    public DeviceFeatureDao() {
    }

    public DeviceFeatureDao(String description,
                            List<FeatureArgDao> featureArgDaoList,
                            List<FeatureArgFunctionArgRelationDao> argRelationDaoList,
                            List<FeatureFunctionDao> featureFunctionDaoList,
                            String featureId, String featureName) {
        this.description = description;
        this.featureArgDaoList = featureArgDaoList;
        this.argRelationDaoList = argRelationDaoList;
        this.featureFunctionDaoList = featureFunctionDaoList;
        this.featureId = featureId;
        this.featureName = featureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DeviceFeatureDao that = (DeviceFeatureDao) o;

        if (featureId != null ? !featureId.equals(that.featureId) : that.featureId != null) return false;
        return !(featureName != null ? !featureName.equals(that.featureName) : that.featureName != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (featureId != null ? featureId.hashCode() : 0);
        result = 31 * result + (featureName != null ? featureName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceFeatureDao{" +
                "description='" + description + '\'' +
                ", featureId='" + featureId + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
