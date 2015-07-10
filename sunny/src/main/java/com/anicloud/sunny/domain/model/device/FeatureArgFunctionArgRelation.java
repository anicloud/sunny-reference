package com.anicloud.sunny.domain.model.device;

import com.anicloud.sunny.domain.share.AbstractDomain;
import com.anicloud.sunny.infrastructure.persistence.domain.device.FeatureArgFunctionArgRelationDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-7-10.
 */

public class FeatureArgFunctionArgRelation extends AbstractDomain {
    public String featureArgName;
    public String featureFunctionId;
    public String functionArgName;

    public FeatureArgFunctionArgRelation() {
    }

    public FeatureArgFunctionArgRelation(String featureArgName,
                                         String featureFunctionId,
                                         String functionArgName) {
        this.featureArgName = featureArgName;
        this.featureFunctionId = featureFunctionId;
        this.functionArgName = functionArgName;
    }

    public static FeatureArgFunctionArgRelationDao toDao(FeatureArgFunctionArgRelation relation) {
        if (relation == null) return null;
        return new FeatureArgFunctionArgRelationDao(
                relation.featureArgName,
                relation.featureFunctionId,
                relation.functionArgName
        );
    }

    public static FeatureArgFunctionArgRelation toRelation(FeatureArgFunctionArgRelationDao dao) {
        if (dao == null) return null;
        return new FeatureArgFunctionArgRelation(
                dao.featureArgName,
                dao.featureFunctionId,
                dao.functionArgName
        );
    }

    public static List<FeatureArgFunctionArgRelationDao> toDaoList(List<FeatureArgFunctionArgRelation> relationList) {
        if (relationList == null) return null;
        List<FeatureArgFunctionArgRelationDao> relationDaoList = new ArrayList<>();
        for (FeatureArgFunctionArgRelation relation : relationList) {
            relationDaoList.add(toDao(relation));
        }
        return relationDaoList;
    }

    public static List<FeatureArgFunctionArgRelation> toRelationList(List<FeatureArgFunctionArgRelationDao> daoList) {
        if (daoList == null) return null;
        List<FeatureArgFunctionArgRelation> relationList = new ArrayList<>();
        for (FeatureArgFunctionArgRelationDao dao : daoList) {
            relationList.add(toRelation(dao));
        }
        return relationList;
    }
}
