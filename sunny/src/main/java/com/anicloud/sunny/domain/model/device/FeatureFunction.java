package com.anicloud.sunny.domain.model.device;

import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FeatureFunction extends AbstractDomain {
    private static final long serialVersionUID = -3112990501367413230L;

    public String functionGroup;
    public String functionName;
    public FunctionType functionType;
    public Integer sequenceNum;
    public Set<FunctionArgument> functionArgumentSet;

    public FeatureFunction() {
    }

    public FeatureFunction(Set<FunctionArgument> functionArgumentSet,
                           String functionGroup, String functionName,
                           FunctionType functionType, Integer sequenceNum) {
        this.functionArgumentSet = functionArgumentSet;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
        this.sequenceNum = sequenceNum;
    }

    public static FeatureFunction toFeatureFunction(FeatureFunctionDao functionDao) {
        if (functionDao == null) {
            return null;
        }
        FeatureFunction featureFunction = new FeatureFunction(
                FunctionArgument.toFunctionArgumentSet(functionDao.functionArgumentDaoSet),
                functionDao.functionGroup,
                functionDao.functionName,
                functionDao.functionType,
                functionDao.sequenceNum
        );
        return featureFunction;
    }

    public static FeatureFunctionDao toDao(FeatureFunction featureFunction) {
        if (featureFunction == null) {
            return null;
        }
        FeatureFunctionDao functionDao = new FeatureFunctionDao(
                FunctionArgument.toDaoSet(featureFunction.functionArgumentSet),
                featureFunction.functionGroup,
                featureFunction.functionName,
                featureFunction.functionType,
                featureFunction.sequenceNum
        );
        return functionDao;
    }

    public static List<FeatureFunction> toFeatureFunctionList(List<FeatureFunctionDao> functionDaoList) {
        if (functionDaoList == null) {
            return null;
        }
        List<FeatureFunction> functionList = new ArrayList<>();
        for (FeatureFunctionDao functionDao : functionDaoList) {
            functionList.add(toFeatureFunction(functionDao));
        }
        return functionList;
    }

    public static List<FeatureFunctionDao> toDaoSet(List<FeatureFunction> functionList) {
        if (functionList == null) {
            return null;
        }
        List<FeatureFunctionDao> functionDaoList = new ArrayList<>();
        for (FeatureFunction function : functionList) {
            functionDaoList.add(toDao(function));
        }
        return functionDaoList;
    }

    @Override
    public String toString() {
        return "FeatureFunction{" +
                "functionGroup='" + functionGroup + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionType=" + functionType +
                ", sequenceNum=" + sequenceNum +
                '}';
    }
}
