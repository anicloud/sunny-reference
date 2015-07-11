package com.anicloud.sunny.domain.model.share;

import com.anicloud.sunny.domain.model.device.FeatureFunction;
import com.anicloud.sunny.infrastructure.persistence.domain.device.LogFunctionValueDao;
import com.anicloud.sunny.infrastructure.persistence.domain.strategy.FeatureInstanceFunctionValueDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class FunctionValue implements Serializable {
    private static final long serialVersionUID = 2743471870088769938L;

    public String argName;
    public String value;

    public FunctionValue() {
    }

    public FunctionValue(String argName, String value) {
        this.argName = argName;
        this.value = value;
    }

    public static FunctionValue toFunctionValue(LogFunctionValueDao logFunctionValueDao) {
        if (logFunctionValueDao == null) {
            return null;
        }
        return new FunctionValue(
                logFunctionValueDao.argName,
                logFunctionValueDao.value
        );
    }

    public static LogFunctionValueDao toLogFunctionValueDao(FunctionValue functionValue) {
        if (functionValue == null) {
            return null;
        }
        return new LogFunctionValueDao(
                functionValue.argName,
                functionValue.value
        );
    }

    public static List<FunctionValue> toLogFunctionValueList(List<LogFunctionValueDao> logFunctionValueDaoList) {
        List<FunctionValue> functionValueList = new ArrayList<>();
        for (LogFunctionValueDao logFunctionValueDao : logFunctionValueDaoList) {
            functionValueList.add(toFunctionValue(logFunctionValueDao));
        }
        return functionValueList;
    }

    public static List<LogFunctionValueDao> toLogFunctionValueDaoList(List<FunctionValue> functionValueList) {
        List<LogFunctionValueDao> logFunctionValueDaoList =
                new ArrayList<>();
        for (FunctionValue functionValue : functionValueList) {
            logFunctionValueDaoList.add(toLogFunctionValueDao(functionValue));
        }
        return logFunctionValueDaoList;
    }

    public static FunctionValue toFunctionValue(FeatureInstanceFunctionValueDao valueDao) {
        return new FunctionValue(
                valueDao.argName,
                valueDao.value
        );
    }

    public static FeatureInstanceFunctionValueDao toFeatureInstanceFuncValue(FunctionValue functionValue) {
        return new FeatureInstanceFunctionValueDao(
                functionValue.argName,
                functionValue.value
        );
    }

    public static List<FunctionValue> toFunctionValueList(List<FeatureInstanceFunctionValueDao> daoList) {
        List<FunctionValue> functionValueList = new ArrayList<>();
        for (FeatureInstanceFunctionValueDao valueDao : daoList) {
            functionValueList.add(toFunctionValue(valueDao));
        }
        return functionValueList;
    }

    public static List<FeatureInstanceFunctionValueDao> toFeatureInstanceFuncValueList(List<FunctionValue> valueList) {
        List<FeatureInstanceFunctionValueDao> instanceFunctionValueDaoList =
                new ArrayList<>();
        for (FunctionValue functionValue : valueList) {
            instanceFunctionValueDaoList.add(toFeatureInstanceFuncValue(functionValue));
        }
        return instanceFunctionValueDaoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunctionValue that = (FunctionValue) o;

        if (argName != null ? !argName.equals(that.argName) : that.argName != null) return false;
        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = argName != null ? argName.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FunctionValue{" +
                "argName='" + argName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
