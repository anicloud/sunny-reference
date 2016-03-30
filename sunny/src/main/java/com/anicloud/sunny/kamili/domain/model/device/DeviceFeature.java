package com.anicloud.sunny.kamili.domain.model.device;

import org.springframework.beans.factory.annotation.Configurable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MRK on 2016/3/15.
 */
@Configurable
public class DeviceFeature implements Serializable {
    private static final long serialVersionUID = -1643214979776573103L;

    public Integer id;
    public String name;
    public String desc;
    public Set<FeatureArg> inputArgs;
    public List<StubIdentity> stubIdentityList;
    public Map<String, Map<StubIdentity, String>> inputArgMapping;

    public DeviceFeature() {
    }

    public DeviceFeature(Integer id, String name, String desc,
                         Set<FeatureArg> inputArgs,
                         List<StubIdentity> stubIdentityList,
                         Map<String, Map<StubIdentity, String>> inputArgMap) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.inputArgs = inputArgs;
        this.stubIdentityList = stubIdentityList;
        this.inputArgMapping = inputArgMap;
    }

    @Override
    public String toString() {
        return "DeviceFeature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", inputArgs=" + inputArgs +
                ", stubIdentityList=" + stubIdentityList +
                ", inputArgMap=" + inputArgMapping +
                '}';
    }
}
