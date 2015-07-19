package com.anicloud.sunny.schedule.domain.adapter;

import com.anicloud.sunny.schedule.domain.strategy.*;
import com.anicloud.sunny.schedule.dao.*;

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
        List<ArgumentDao> argumentDaoList = new ArrayList<>();
        if (argumentList != null) {
            for (Argument argument : argumentList) {
                argumentDaoList.add(toArgumentDao(argument));
            }
        }
        return argumentDaoList;
    }

    public static FunctionInstanceDao toFunctionInstanceDao(FunctionInstance functionInstance) {
        FunctionInstanceDao functionInstanceDao = new FunctionInstanceDao(
                functionInstance.functionId,
                functionInstance.name,
                functionInstance.group,
                toArgumentDaoList(functionInstance.inputList),
                toArgumentDaoList(functionInstance.outputList));
        return functionInstanceDao;
    }

    public static List<FunctionInstanceDao> toFunctionInstanceDaoList(List<FunctionInstance> functionInstanceList) {
        List<FunctionInstanceDao> functionInstanceDaoList = new ArrayList<>();
        if (functionInstanceList != null ) {
            for (FunctionInstance functionInstance : functionInstanceList) {
                functionInstanceDaoList.add(toFunctionInstanceDao(functionInstance));
            }
        }
        return functionInstanceDaoList;
    }

    public static TriggerInstanceDao toTriggerInstanceDao(TriggerInstance triggerInstance) {
        TriggerInstanceDao triggerInstanceDao = new TriggerInstanceDao(
                triggerInstance.startTime,
                triggerInstance.repeatInterval,
                triggerInstance.repeatCount);
        return triggerInstanceDao;
    }

    public static List<TriggerInstanceDao> toTriggerInstanceDaoList(List<TriggerInstance> triggerInstanceList) {
        List<TriggerInstanceDao> triggerInstanceDaoList = new ArrayList<>();
        if (triggerInstanceList != null) {
            for (TriggerInstance triggerInstance : triggerInstanceList) {
                triggerInstanceDaoList.add(toTriggerInstanceDao(triggerInstance));
            }
        }
        return triggerInstanceDaoList;
    }

    public static FeatureInstanceDao toFeatureInstanceDao(FeatureInstance featureInstance) {
        FeatureInstanceDao featureInstanceDao = new FeatureInstanceDao(
                featureInstance.featureId,
                featureInstance.state,
                featureInstance.stage,
                toFunctionInstanceDaoList(featureInstance.functionInstanceList),
                toTriggerInstanceDaoList(featureInstance.triggerInstanceList));
        return featureInstanceDao;
    }

    public static List<FeatureInstanceDao> toFeatureInstanceDaoList(List<FeatureInstance> featureInstanceList) {
        List<FeatureInstanceDao> featureInstanceDaoList = new ArrayList<>();
        if (featureInstanceList != null) {
            for (FeatureInstance featureInstance : featureInstanceList) {
                featureInstanceDaoList.add(toFeatureInstanceDao(featureInstance));
            }
        }
        return featureInstanceDaoList;
    }

    public static StrategyInstanceDao toStrategyInstanceDao(StrategyInstance strategyInstance) {
        StrategyInstanceDao strategyInstanceDao = new StrategyInstanceDao(
                strategyInstance.strategyId,
                strategyInstance.state,
                strategyInstance.stage,
                toFeatureInstanceDaoList(strategyInstance.featureInstanceList),
                strategyInstance.action);
        return strategyInstanceDao;
    }

    public static Argument fromArgumentDao(ArgumentDao argumentDao) {
        Argument argument = new Argument(argumentDao.name, argumentDao.value);
        return  argument;
    }

    public static List<Argument> fromArgumentDaoList(List<ArgumentDao> argumentDaoList) {
        List<Argument> argumentList = new LinkedList<>();
        if (argumentDaoList != null) {
            for (ArgumentDao argumentDao : argumentDaoList) {
                argumentList.add(fromArgumentDao(argumentDao));
            }
        }
        return argumentList;
    }

    public static FunctionInstance fromFunctionInstanceDao(FunctionInstanceDao functionInstanceDao) {
        FunctionInstance functionInstance = new FunctionInstance(
                functionInstanceDao.functionId,
                functionInstanceDao.name,
                functionInstanceDao.group,
                fromArgumentDaoList(functionInstanceDao.inputList),
                fromArgumentDaoList(functionInstanceDao.outputList));
        return functionInstance;
    }

    public static List<FunctionInstance> fromFunctionInstanceDaoList(List<FunctionInstanceDao> functionInstanceDaoList) {
        List<FunctionInstance> functionInstanceList = new ArrayList<>();
        if (functionInstanceDaoList != null) {
            for (FunctionInstanceDao functionInstanceDao : functionInstanceDaoList) {
                functionInstanceList.add(fromFunctionInstanceDao(functionInstanceDao));
            }
        }
        return functionInstanceList;
    }

    public static TriggerInstance fromTriggerInstanceDao(TriggerInstanceDao triggerInstanceDao) {
        TriggerInstance triggerInstance = new TriggerInstance(
                triggerInstanceDao.startTime,
                triggerInstanceDao.repeatInterval,
                triggerInstanceDao.repeatCount);
        return triggerInstance;
    }

    public static List<TriggerInstance> fromTriggerInstanceDaoList(List<TriggerInstanceDao> triggerInstanceDaoList) {
        List<TriggerInstance> triggerInstanceList = new ArrayList<>();
        if (triggerInstanceDaoList != null) {
            for (TriggerInstanceDao triggerInstanceDao : triggerInstanceDaoList) {
                triggerInstanceList.add(fromTriggerInstanceDao(triggerInstanceDao));
            }
        }
        return triggerInstanceList;
    }

    public static FeatureInstance fromFeatureInstanceDao(FeatureInstanceDao featureInstanceDao) {
        FeatureInstance featureInstance = new FeatureInstance(
                featureInstanceDao.featureId,
                featureInstanceDao.state,
                featureInstanceDao.stage,
                fromFunctionInstanceDaoList(featureInstanceDao.functionInstanceDaoList),
                fromTriggerInstanceDaoList(featureInstanceDao.triggerInstanceDaoList));
        return featureInstance;
    }

    public static List<FeatureInstance> fromFeatureInstanceDaoList(List<FeatureInstanceDao> featureInstanceDaoList) {
        List<FeatureInstance> featureInstanceList = new ArrayList<>();
        if (featureInstanceDaoList != null) {
            for (FeatureInstanceDao featureInstanceDao : featureInstanceDaoList) {
                featureInstanceList.add(fromFeatureInstanceDao(featureInstanceDao));
            }
        }
        return featureInstanceList;
    }

    public static StrategyInstance fromStrategyInstanceDao(StrategyInstanceDao strategyInstanceDao) {
        StrategyInstance strategyInstance = new StrategyInstance(
                strategyInstanceDao.strategyId,
                strategyInstanceDao.state,
                strategyInstanceDao.stage,
                fromFeatureInstanceDaoList(strategyInstanceDao.featureInstanceDaoList),
                strategyInstanceDao.action);
        return strategyInstance;
    }

}
