package com.anicloud.sunny.application.service.init;

import com.ani.bus.service.commons.dto.anidevice.stub.StubMeta;
import com.ani.octopus.commons.stub.dto.StubDto;
import com.ani.octopus.commons.stub.dto.StubInfoDto;
import com.anicloud.sunny.domain.model.device.Device;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by zhaoyu on 15-7-1.
 */
public abstract class DeviceInfoGeneratorService {
    protected static Map<String, Set<StubIdentity>> deviceTypeRule;  // key is type, value set is stub set
    protected static Map<String, String> deviceLogoUrls; //key is type, value is logo url

    public abstract void initDeviceTypeGeneratorRule();
    public abstract String generatorDeviceType(List<StubInfoDto> stubs);
    public abstract void initDeviceLogoUrl();
    public abstract String getDeviceLogoUrl(String deviceType);
}

class StubIdentity implements Serializable {
    public Integer stubId;
    public Long groupId;

    public StubIdentity() {
    }

    public StubIdentity(Integer stubId, Long groupId) {
        this.stubId = stubId;
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StubIdentity that = (StubIdentity) o;
        return Objects.equals(stubId, that.stubId) &&
                Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stubId, groupId);
    }

    @Override
    public String toString() {
        return "StubIdentity{" +
                "stubId=" + stubId +
                ", groupId=" + groupId +
                '}';
    }
}
