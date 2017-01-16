package com.anicloud.sunny.schedule.domain.adapter;

import com.anicloud.sunny.schedule.domain.strategy.*;
import com.anicloud.sunny.schedule.dto.StrategyInstanceDto;
import com.anicloud.sunny.schedule.persistence.dao.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by huangbin on 7/19/15.
 */
public class DaoAdapter {

    public static ArgumentDao toArgumentDao(Argument argument) {
        return new ArgumentDao(argument.name, argument.value);
    }

    public static List<ArgumentDao> toArgumentDaoList(List<Argument> argumentList) {
        if (argumentList == null) return null;
        List<ArgumentDao> argumentDaoList = new ArrayList<>();
        if (argumentList != null) {
            for (Argument argument : argumentList) {
                argumentDaoList.add(toArgumentDao(argument));
            }
        }
        return argumentDaoList;
    }

    public static FunctionInstanceDao toFunctionInstanceDao(FunctionInstance functionInstance) {
        if (functionInstance == null) return null;
        FunctionInstanceDao functionInstanceDao = new FunctionInstanceDao(
                functionInstance.functionId,
                functionInstance.stubId,
                functionInstance.groupId,
                functionInstance.name,
                toArgumentDaoList(functionInstance.outputList));
        return functionInstanceDao;
    }

    public static List<FunctionInstanceDao> toFunctionInstanceDaoList(List<FunctionInstance> functionInstanceList) {
        if (functionInstanceList == null) return null;
        List<FunctionInstanceDao> functionInstanceDaoList = new ArrayList<>();
        if (functionInstanceList != null ) {
            for (FunctionInstance functionInstance : functionInstanceList) {
                functionInstanceDaoList.add(toFunctionInstanceDao(functionInstance));
            }
        }
        return functionInstanceDaoList;
    }

    public static TriggerInstanceDao toTriggerInstanceDao(TriggerInstance triggerInstance) {
        if (triggerInstance == null) return null;
        TriggerInstanceDao triggerInstanceDao = new TriggerInstanceDao(
                triggerInstance.startTime,
                triggerInstance.repeatInterval,
                triggerInstance.repeatCount);
        return triggerInstanceDao;
    }

    public static List<TriggerInstanceDao> toTriggerInstanceDaoList(List<TriggerInstance> triggerInstanceList) {
        if (triggerInstanceList == null) return null;
        List<TriggerInstanceDao> triggerInstanceDaoList = new ArrayList<>();
        if (triggerInstanceList != null) {
            for (TriggerInstance triggerInstance : triggerInstanceList) {
                triggerInstanceDaoList.add(toTriggerInstanceDao(triggerInstance));
            }
        }
        return triggerInstanceDaoList;
    }

    public static FeatureInstanceDao toFeatureInstanceDao(FeatureInstance featureInstance) {
        if (featureInstance == null) return null;
        FeatureInstanceDao featureInstanceDao = new FeatureInstanceDao(
                featureInstance.featureId,
                featureInstance.deviceId,
                featureInstance.state,
                featureInstance.stage,
                toFunctionInstanceDaoList(featureInstance.functionInstanceList),
                toTriggerInstanceDaoList(featureInstance.triggerInstanceList),
                featureInstance.isScheduleNow,
                featureInstance.intervalTime
        );
        return featureInstanceDao;
    }

    public static List<FeatureInstanceDao> toFeatureInstanceDaoList(List<FeatureInstance> featureInstanceList) {
        if (featureInstanceList == null) return null;
        List<FeatureInstanceDao> featureInstanceDaoList = new ArrayList<>();
        if (featureInstanceList != null) {
            for (FeatureInstance featureInstance : featureInstanceList) {
                featureInstanceDaoList.add(toFeatureInstanceDao(featureInstance));
            }
        }
        return featureInstanceDaoList;
    }

    public static StrategyInstanceDao toStrategyInstanceDao(StrategyInstance strategyInstance) {
        if (strategyInstance == null) return null;
        StrategyInstanceDao strategyInstanceDao = new StrategyInstanceDao(
                strategyInstance.strategyId,
                strategyInstance.state,
                strategyInstance.stage,
                toFeatureInstanceDaoList(strategyInstance.featureInstanceList),
                strategyInstance.action,
                strategyInstance.timeStamp,
                strategyInstance.startTime,
                StrategyInstanceDao.repeatWeektoString(strategyInstance.repeatWeek),
                strategyInstance.isRepeat,
                strategyInstance.isScheduleNow);
        return strategyInstanceDao;
    }

    public static Argument fromArgumentDao(ArgumentDao argumentDao) {
        if (argumentDao == null) return null;
        Argument argument = new Argument(argumentDao.name, argumentDao.value);
        return  argument;
    }

    public static List<Argument> fromArgumentDaoList(List<ArgumentDao> argumentDaoList) {
        if (argumentDaoList == null) return null;
        List<Argument> argumentList = new LinkedList<>();
        if (argumentDaoList != null) {
            for (ArgumentDao argumentDao : argumentDaoList) {
                argumentList.add(fromArgumentDao(argumentDao));
            }
        }
        return argumentList;
    }

    public static FunctionInstance fromFunctionInstanceDao(FunctionInstanceDao functionInstanceDao) {
        if (functionInstanceDao == null) return null;
        FunctionInstance functionInstance = new FunctionInstance(
                functionInstanceDao.functionId,
                functionInstanceDao.stubId,
                functionInstanceDao.groupId,
                functionInstanceDao.name,
                null,
                fromArgumentDaoList(functionInstanceDao.outputList));
        return functionInstance;
    }

    public static List<FunctionInstance> fromFunctionInstanceDaoList(List<FunctionInstanceDao> functionInstanceDaoList) {
        if (functionInstanceDaoList == null) return null;
        List<FunctionInstance> functionInstanceList = new ArrayList<>();
        if (functionInstanceDaoList != null) {
            for (FunctionInstanceDao functionInstanceDao : functionInstanceDaoList) {
                functionInstanceList.add(fromFunctionInstanceDao(functionInstanceDao));
            }
        }
        return functionInstanceList;
    }

    public static TriggerInstance fromTriggerInstanceDao(TriggerInstanceDao triggerInstanceDao) {
        if (triggerInstanceDao == null) return null;
        TriggerInstance triggerInstance = new TriggerInstance(
                triggerInstanceDao.startTime,
                triggerInstanceDao.repeatInterval,
                triggerInstanceDao.repeatCount);
        return triggerInstance;
    }

    public static List<TriggerInstance> fromTriggerInstanceDaoList(List<TriggerInstanceDao> triggerInstanceDaoList) {
        if (triggerInstanceDaoList == null) return null;
        List<TriggerInstance> triggerInstanceList = new ArrayList<>();
        if (triggerInstanceDaoList != null) {
            for (TriggerInstanceDao triggerInstanceDao : triggerInstanceDaoList) {
                triggerInstanceList.add(fromTriggerInstanceDao(triggerInstanceDao));
            }
        }
        return triggerInstanceList;
    }

    public static FeatureInstance fromFeatureInstanceDao(FeatureInstanceDao featureInstanceDao) {
        if (featureInstanceDao == null) return null;
        FeatureInstance featureInstance = new FeatureInstance(
                featureInstanceDao.featureId,
                featureInstanceDao.deviceId,
                featureInstanceDao.state,
                featureInstanceDao.stage,
                fromFunctionInstanceDaoList(featureInstanceDao.functionInstanceDaoList),
                fromTriggerInstanceDaoList(featureInstanceDao.triggerInstanceDaoList),
                featureInstanceDao.isScheduleNow,
                featureInstanceDao.intervalTime
        );
        return featureInstance;
    }

    public static List<FeatureInstance> fromFeatureInstanceDaoList(List<FeatureInstanceDao> featureInstanceDaoList) {
        if (featureInstanceDaoList == null) return null;
        List<FeatureInstance> featureInstanceList = new ArrayList<>();
        if (featureInstanceDaoList != null) {
            for (FeatureInstanceDao featureInstanceDao : featureInstanceDaoList) {
                featureInstanceList.add(fromFeatureInstanceDao(featureInstanceDao));
            }
        }
        return featureInstanceList;
    }

    public static StrategyInstance fromStrategyInstanceDao(StrategyInstanceDao strategyInstanceDao) {
        if (strategyInstanceDao == null) return null;
        StrategyInstance strategyInstance = new StrategyInstance(
                strategyInstanceDao.strategyId,
                strategyInstanceDao.state,
                strategyInstanceDao.stage,
                fromFeatureInstanceDaoList(strategyInstanceDao.featureInstanceDaoList),
                strategyInstanceDao.action,
                strategyInstanceDao.timeStamp,
                false,
                strategyInstanceDao.startTime,
                strategyInstanceDao.isScheduleNow,
                strategyInstanceDao.isRepeat,
                StrategyInstanceDao.repeatWeektoArray(strategyInstanceDao.repeatWeek)
                );
        return strategyInstance;
    }

}
