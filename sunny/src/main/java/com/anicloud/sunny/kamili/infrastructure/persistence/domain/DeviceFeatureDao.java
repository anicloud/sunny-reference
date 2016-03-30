package com.anicloud.sunny.kamili.infrastructure.persistence.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MRK on 2016/3/23.
 */
public class DeviceFeatureDao implements Serializable{
    private static final long serialVersionUID = -2605834315350791947L;

    public Integer id;
    public String name;
    public String desc;
    public Set<FeatureArgDao> inputArgs;
    public List<StubIdentityDao> stubIdentityList;
    public Map<String, Map<StubIdentityDao, String>> inputArgMapping;

    public DeviceFeatureDao() {
    }

    public DeviceFeatureDao(Integer id, String name, String desc,
                            Set<FeatureArgDao> inputArgs,
                            List<StubIdentityDao> stubIdentityList,
                            Map<String, Map<StubIdentityDao, String>> inputArgMapping) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.inputArgs = inputArgs;
        this.stubIdentityList = stubIdentityList;
        this.inputArgMapping = inputArgMapping;
    }

    @Override
    public String toString() {
        return "DeviceFeatureDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", inputArgs=" + inputArgs +
                ", stubIdentityList=" + stubIdentityList +
                ", inputArgMapping=" + inputArgMapping +
                '}';
    }
}
