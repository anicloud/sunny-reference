package com.anicloud.sunny.kamili.interfaces.facade.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MRK on 2016/3/18.
 */
public class DeviceFeatureDto implements Serializable {
    private static final long serialVersionUID = -1180825124528529489L;
    public Integer id;
    public String name;
    public String desc;
    public Set<FeatureArgDto> inputArgs;
    public List<StubIdentityDto> stubIdentityList;
    public Map<String, Map<StubIdentityDto, String>> inputArgMapping;

    public DeviceFeatureDto() {
    }

    public DeviceFeatureDto(Integer id, String name,
                            String desc, Set<FeatureArgDto> inputArgs,
                            List<StubIdentityDto> stubIdentityList,
                            Map<String, Map<StubIdentityDto, String>> inputArgMapping) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.inputArgs = inputArgs;
        this.stubIdentityList = stubIdentityList;
        this.inputArgMapping = inputArgMapping;
    }

    @Override
    public String toString() {
        return "DeviceFeatureDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", inputArgs=" + inputArgs +
                ", stubIdentityList=" + stubIdentityList +
                ", inputArgMapping=" + inputArgMapping +
                '}';
    }
}
