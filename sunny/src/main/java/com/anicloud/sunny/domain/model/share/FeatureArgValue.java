package com.anicloud.sunny.domain.model.share;

import com.anicloud.sunny.infrastructure.persistence.domain.strategy.FeatureInstanceFunctionValueDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class FeatureArgValue implements Serializable, Cloneable {
    private static final long serialVersionUID = 2743471870088769938L;

    public String argName;
    public String value;

    public FeatureArgValue() {
    }

    public FeatureArgValue(String argName, String value) {
        this.argName = argName;
        this.value = value;
    }

    public static FeatureArgValue toFunctionValue(FeatureInstanceFunctionValueDao valueDao) {
        return new FeatureArgValue(
                valueDao.argName,
                valueDao.value
        );
    }

    public static FeatureInstanceFunctionValueDao toFeatureInstanceFuncValue(FeatureArgValue featureArgValue) {
        return new FeatureInstanceFunctionValueDao(
                featureArgValue.argName,
                featureArgValue.value
        );
    }

    public static List<FeatureArgValue> toFunctionValueList(List<FeatureInstanceFunctionValueDao> daoList) {
        List<FeatureArgValue> featureArgValueList = new ArrayList<>();
        for (FeatureInstanceFunctionValueDao valueDao : daoList) {
            featureArgValueList.add(toFunctionValue(valueDao));
        }
        return featureArgValueList;
    }

    public static List<FeatureInstanceFunctionValueDao> toFeatureInstanceFuncValueList(List<FeatureArgValue> valueList) {
        List<FeatureInstanceFunctionValueDao> instanceFunctionValueDaoList =
                new ArrayList<>();
        for (FeatureArgValue featureArgValue : valueList) {
            instanceFunctionValueDaoList.add(toFeatureInstanceFuncValue(featureArgValue));
        }
        return instanceFunctionValueDaoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureArgValue that = (FeatureArgValue) o;

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
    protected FeatureArgValue clone() {
        FeatureArgValue featureArgValue = null;
        try {
            featureArgValue = (FeatureArgValue) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return featureArgValue;
    }

    @Override
    public String toString() {
        return "FeatureArgValue{" +
                "argName='" + argName + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
