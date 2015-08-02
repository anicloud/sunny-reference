package com.anicloud.sunny.infrastructure.persistence.domain.strategy;

import com.anicloud.sunny.infrastructure.persistence.domain.share.AbstractEntity;
import com.anicloud.sunny.infrastructure.persistence.domain.user.UserDao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-11.
 */
@Entity
@Table(name = "t_strategy")
public class StrategyDao extends AbstractEntity {
    private static final long serialVersionUID = 6150221948952546939L;

    @Column(name = "strategy_id", nullable = false, unique = true)
    public String strategyId;
    @Column(name = "strategy_name", nullable = false, length = 150)
    public String strategyName;
    @Column(name = "description", length = 255)
    public String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id")
    public UserDao owner;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "strategy_id", referencedColumnName = "id")
    public List<DeviceFeatureInstanceDao> deviceFeatureInstanceDaoList;

    public StrategyDao() {
    }

    public StrategyDao(String strategyId, String strategyName,
                       String description, UserDao owner,
                       List<DeviceFeatureInstanceDao> deviceFeatureInstanceDaoList) {
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.description = description;
        this.owner = owner;
        this.deviceFeatureInstanceDaoList = deviceFeatureInstanceDaoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StrategyDao that = (StrategyDao) o;

        if (strategyId != null ? !strategyId.equals(that.strategyId) : that.strategyId != null) return false;
        return !(strategyName != null ? !strategyName.equals(that.strategyName) : that.strategyName != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (strategyId != null ? strategyId.hashCode() : 0);
        result = 31 * result + (strategyName != null ? strategyName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StrategyDao{" +
                "strategyId='" + strategyId + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
