package com.anicloud.sunny.domain.model.strategy;

import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyPersistenceService;
import com.anicloud.sunny.schedule.domain.strategy.StrategyInstance;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class Strategy extends AbstractDomain implements Cloneable {
    private static final long serialVersionUID = -1540568663807896074L;

    public String strategyId;
    public String strategyName;
    public String description;
    public Date startTime;
    public boolean isScheduleNow;
    public boolean isRepeat;
    public String[] repeatWeek;
    public User owner;
    public List<DeviceFeatureInstance> deviceFeatureInstanceList;

    public StrategyInstance strategyInstance;

    public Strategy() {
    }

    public Strategy(String strategyId, String strategyName,
                    String description, User owner,
                    List<DeviceFeatureInstance> deviceFeatureInstanceList) {
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.description = description;
        this.owner = owner;
        this.deviceFeatureInstanceList = deviceFeatureInstanceList;
    }

    public Strategy(String strategyId, String strategyName,
                    String description, User owner,
                    Date startTime, String[] repeatWeek,
                    boolean isRepeat, boolean isScheduleNow,
                    List<DeviceFeatureInstance> deviceFeatureInstanceList) {
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.description = description;
        this.owner = owner;
        this.startTime = startTime;
        this.repeatWeek = repeatWeek;
        this.isRepeat = isRepeat;
        this.isScheduleNow = isScheduleNow;
        this.deviceFeatureInstanceList = deviceFeatureInstanceList;
    }

    public Strategy(String strategyId, String strategyName,
                    String description, User owner,
                    Date startTime, String[] repeatWeek,
                    boolean isRepeat, boolean isScheduleNow,
                    List<DeviceFeatureInstance> deviceFeatureInstanceList,
                    StrategyInstance strategyInstance) {
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.description = description;
        this.owner = owner;
        this.startTime = startTime;
        this.repeatWeek = repeatWeek;
        this.isRepeat = isRepeat;
        this.isScheduleNow = isScheduleNow;
        this.deviceFeatureInstanceList = deviceFeatureInstanceList;
        this.strategyInstance = strategyInstance;
    }

    public static Strategy save(StrategyPersistenceService persistenceService, Strategy strategy) {
        StrategyDao strategyDao = persistenceService.save(toDao(strategy));
        return toStrategy(strategyDao);
    }

    public static Strategy modify(StrategyPersistenceService persistenceService, Strategy strategy) {
        StrategyDao strategyDao = persistenceService.modify(toDao(strategy));
        return toStrategy(strategyDao);
    }

    public static void remove(StrategyPersistenceService persistenceService, String strategyId) {
        persistenceService.removeById(strategyId);
    }

    public static Strategy getStrategyById(StrategyPersistenceService persistenceService, String strategyId) {
        StrategyDao strategyDao = persistenceService.getStrategyById(strategyId);
        return toStrategy(strategyDao);
    }

    public static List<Strategy> getStrategyListByUser(StrategyPersistenceService persistenceService, Long hashUserId) {
        List<StrategyDao> strategyDaoList = persistenceService.getStrategyListByUser(hashUserId);
        return toStrategyList(strategyDaoList);
    }

    public static Strategy toStrategy(StrategyDao strategyDao) {
        if (strategyDao == null) {
            return null;
        }

        Strategy strategy = new Strategy(
                strategyDao.strategyId,
                strategyDao.strategyName,
                strategyDao.description,
                User.toUser(strategyDao.owner),
                DeviceFeatureInstance.toInstanceList(strategyDao.deviceFeatureInstanceDaoList)
        );
        return strategy;
    }

    public static StrategyDao toDao(Strategy strategy) {
        if (strategy == null) {
            return null;
        }

        StrategyDao strategyDao = new StrategyDao(
                strategy.strategyId,
                strategy.strategyName,
                strategy.description,
                User.toDao(strategy.owner),
                DeviceFeatureInstance.toDaoList(strategy.deviceFeatureInstanceList)
        );
        return strategyDao;
    }

    public static List<Strategy> toStrategyList(List<StrategyDao> daoList) {
        if (daoList == null) return null;
        List<Strategy> strategyList = new ArrayList<>();
        for (StrategyDao strategyDao : daoList) {
            strategyList.add(toStrategy(strategyDao));
        }
        return strategyList;
    }

    public static List<StrategyDao> toDaoList(List<Strategy> strategyList) {
        if (strategyList == null) return null;
        List<StrategyDao> strategyDaoList = new ArrayList<>();
        for (Strategy strategy : strategyList) {
            strategyDaoList.add(toDao(strategy));
        }
        return strategyDaoList;
    }

    public static StrategyInstanceDto strategyInstanceDto(Strategy strategy) {
        return null;
    }

    @Override
    public Strategy clone()  {
        Strategy strategy = null;
        try {
            strategy = (Strategy) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable.");
        }
        Collections.copy(strategy.deviceFeatureInstanceList, this.deviceFeatureInstanceList);
        return strategy;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "description='" + description + '\'' +
                ", strategyName='" + strategyName + '\'' +
                '}';
    }
}
