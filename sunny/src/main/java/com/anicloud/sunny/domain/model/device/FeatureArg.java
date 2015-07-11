package com.anicloud.sunny.domain.model.device;

import com.ani.cel.service.manager.agent.core.share.DataType;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureArgDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-10.
 */
public class FeatureArg implements Serializable {
    public String name;
    public DataType dataType;
    public String screenName;

    public FeatureArg() {
    }

    public FeatureArg(String screenName) {
        this.screenName = screenName;
    }

    public FeatureArg(DataType dataType, String name, String screenName) {
        this.dataType = dataType;
        this.name = name;
        this.screenName = screenName;
    }

    public static FeatureArgDao toDao(FeatureArg featureArg) {
        if (featureArg == null) return null;
        return new FeatureArgDao(
                featureArg.dataType,
                featureArg.name,
                featureArg.screenName
        );
    }

    public static FeatureArg toFeatureArg(FeatureArgDao featureArgDao) {
        if (featureArgDao == null) return null;
        return new FeatureArg(
                featureArgDao.dataType,
                featureArgDao.name,
                featureArgDao.screenName
        );
    }

    public static List<FeatureArgDao> toDaoList(List<FeatureArg> featureArgs) {
        if (featureArgs == null) return null;
        List<FeatureArgDao> featureArgDaoList = new ArrayList<>();
        for (FeatureArg featureArg : featureArgs) {
            featureArgDaoList.add(toDao(featureArg));
        }
        return featureArgDaoList;
    }

    public static List<FeatureArg> toFeatureArgList(List<FeatureArgDao> featureArgDaoList) {
        if (featureArgDaoList == null) return null;
        List<FeatureArg> featureArgList = new ArrayList<>();
        for (FeatureArgDao featureArgDao : featureArgDaoList) {
            featureArgList.add(toFeatureArg(featureArgDao));
        }
        return featureArgList;
    }

    @Override
    public String toString() {
        return super.toString() + " screenName : " + screenName;
    }
}
