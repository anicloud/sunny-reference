package com.anicloud.sunny.domain.model.device;

import com.ani.cel.service.manager.agent.core.share.FunctionType;
import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureFunctionDao;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FunctionArgumentDao;
import com.anicloud.sunny.infrastructure.persistence.domain.share.ArgumentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyu on 15-6-12.
 */
public class FeatureFunction extends AbstractDomain {
    private static final long serialVersionUID = -3112990501367413230L;

    public String featureFunctionId;
    public String functionGroup;
    public String functionName;
    public FunctionType functionType;
    public List<FunctionArgument> inputArgList;
    public List<FunctionArgument> outputArgList;

    public FeatureFunction() {
    }

    public FeatureFunction(String featureFunctionId, String functionGroup,
                           String functionName, FunctionType functionType,
                           List<FunctionArgument> inputArgList,
                           List<FunctionArgument> outputArgList) {
        this.featureFunctionId = featureFunctionId;
        this.functionGroup = functionGroup;
        this.functionName = functionName;
        this.functionType = functionType;
        this.inputArgList = inputArgList;
        this.outputArgList = outputArgList;
    }

    public static FeatureFunction toFeatureFunction(FeatureFunctionDao functionDao) {
        if (functionDao == null) {
            return null;
        }

        Map<ArgumentType, List<FunctionArgument>> result = getFunctionArgListByArgType(functionDao);
        FeatureFunction featureFunction = new FeatureFunction(
                functionDao.featureFunctionId,
                functionDao.functionGroup,
                functionDao.functionName,
                functionDao.functionType,
                result.get(ArgumentType.INPUT_ARGUMENT),
                result.get(ArgumentType.OUTPUT_ARGUMENT)
        );
        return featureFunction;
    }

    public static FeatureFunctionDao toDao(FeatureFunction featureFunction) {
        if (featureFunction == null) {
            return null;
        }
        List<FeatureFunctionDao> featureFunctionDaoList = new ArrayList<>();

        FeatureFunctionDao functionDao = new FeatureFunctionDao(
                featureFunction.featureFunctionId,
                toFunctionArgDaoList(featureFunction),
                featureFunction.functionGroup,
                featureFunction.functionName,
                featureFunction.functionType
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

    public static List<FeatureFunctionDao> toDaoList(List<FeatureFunction> functionList) {
        if (functionList == null) {
            return null;
        }
        List<FeatureFunctionDao> functionDaoList = new ArrayList<>();
        for (FeatureFunction function : functionList) {
            functionDaoList.add(toDao(function));
        }
        return functionDaoList;
    }

    public static Map<ArgumentType, List<FunctionArgument>> getFunctionArgListByArgType(FeatureFunctionDao dao) {
        List<FunctionArgument> inputArgList = new ArrayList<>();
        List<FunctionArgument> outputArgList = new ArrayList<>();

        for (FunctionArgumentDao argumentDao : dao.argumentDaoList) {
            if (argumentDao.argumentType == ArgumentType.INPUT_ARGUMENT) {
                inputArgList.add(FunctionArgument.toFunctionArgument(argumentDao));
            }
            else if (argumentDao.argumentType == ArgumentType.OUTPUT_ARGUMENT) {
                outputArgList.add(FunctionArgument.toFunctionArgument(argumentDao));
            }
        }
        Map<ArgumentType, List<FunctionArgument>> returnMap = new HashMap<>();
        returnMap.put(ArgumentType.INPUT_ARGUMENT, inputArgList);
        returnMap.put(ArgumentType.OUTPUT_ARGUMENT, outputArgList);
        return returnMap;
    }

    public static List<FunctionArgumentDao> toFunctionArgDaoList(FeatureFunction featureFunction) {
        if (featureFunction == null) return null;
        List<FunctionArgumentDao> functionDaoList = new ArrayList<>();
        if (featureFunction.inputArgList != null)
            functionDaoList.addAll(FunctionArgument.toDaoList(featureFunction.inputArgList));
        if (featureFunction.outputArgList != null)
            functionDaoList.addAll(FunctionArgument.toDaoList(featureFunction.outputArgList));
        return functionDaoList;
    }

    @Override
    public String toString() {
        return "FeatureFunction{" +
                "functionGroup='" + functionGroup + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionType=" + functionType +
                '}';
    }
}
