package com.anicloud.sunny.domain.model.strategy;

import com.anicloud.sunny.domain.model.user.User;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.share.StrategyState;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.StrategyDao;
import com.anicloud.sunny.infrastructure.persistence.service.StrategyPersistenceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class Strategy extends AbstractDomain {
    private static final long serialVersionUID = -1540568663807896074L;

    public String strategyId;
    public String strategyName;
    public StrategyState state;
    public String description;

    public User owner;
    public List<DeviceFeatureInstance> deviceFeatureInstanceList;

    public Strategy() {
    }

    public Strategy(String strategyId, String strategyName, StrategyState state,
                    String description, User owner,
                    List<DeviceFeatureInstance> deviceFeatureInstanceList) {
        this.strategyId = strategyId;
        this.strategyName = strategyName;
        this.state = state;
        this.description = description;
        this.owner = owner;
        this.deviceFeatureInstanceList = deviceFeatureInstanceList;
    }

    public static Strategy save(StrategyPersistenceService persistenceService, Strategy strategy) {
        StrategyDao strategyDao = persistenceService.save(toDao(strategy));
        return toStrategy(strategyDao);
    }

    public static Strategy modify(StrategyPersistenceService persistenceService, Strategy strategy) {
        StrategyDao strategyDao = persistenceService.modify(toDao(strategy));
        return toStrategy(strategyDao);
    }

    public static void modifyStrategyState(StrategyPersistenceService persistenceService,
                                           String strategyId, StrategyState state) {
        persistenceService.modifyStrategyState(strategyId, state);
    }

    public static void remove(StrategyPersistenceService persistenceService, String strategyId) {
        persistenceService.removeById(strategyId);
    }

    public static Strategy getStrategyById(StrategyPersistenceService persistenceService, String strategyId) {
        StrategyDao strategyDao = persistenceService.getStrategyById(strategyId);
        return toStrategy(strategyDao);
    }

    public static List<Strategy> getStrategyListByUser(StrategyPersistenceService persistenceService, String hashUserId) {
        List<StrategyDao> strategyDaoList = persistenceService.getStrategyListByUser(hashUserId);
        return toStrategyList(strategyDaoList);
    }

    public static List<Strategy> getStrategyByUserAndState(
            StrategyPersistenceService persistenceService, String hashUserId, StrategyState strategyState) {
        List<StrategyDao> strategyDaoList = persistenceService.getStrategyByUserAndState(hashUserId, strategyState);
        return toStrategyList(strategyDaoList);
    }

    public static Strategy toStrategy(StrategyDao strategyDao) {
        if (strategyDao == null) {
            return null;
        }

        Strategy strategy = new Strategy(
                strategyDao.strategyId,
                strategyDao.strategyName,
                strategyDao.state,
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
                strategy.state,
                strategy.description,
                User.toDao(strategy.owner),
                DeviceFeatureInstance.toDaoList(strategy.deviceFeatureInstanceList)
        );
        return strategyDao;
    }

    public static List<Strategy> toStrategyList(List<StrategyDao> daoList) {
        List<Strategy> strategyList = new ArrayList<>();
        for (StrategyDao strategyDao : daoList) {
            strategyList.add(toStrategy(strategyDao));
        }
        return strategyList;
    }

    public static List<StrategyDao> toDaoList(List<Strategy> strategyList) {
        List<StrategyDao> strategyDaoList = new ArrayList<>();
        for (Strategy strategy : strategyList) {
            strategyDaoList.add(toDao(strategy));
        }
        return strategyDaoList;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "description='" + description + '\'' +
                ", state=" + state +
                ", strategyName='" + strategyName + '\'' +
                '}';
    }
}
