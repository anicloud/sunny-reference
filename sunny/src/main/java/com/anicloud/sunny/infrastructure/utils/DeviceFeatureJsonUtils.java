package com.anicloud.sunny.infrastructure.utils;

import com.ani.bus.service.commons.dto.anidevice.stub.DataType;
import com.anicloud.sunny.domain.model.device.DeviceFeature;
import com.anicloud.sunny.domain.model.device.FeatureArg;
import com.anicloud.sunny.domain.model.device.StubIdentity;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * @autor zhaoyu
 * @date 16-3-31
 * @since JDK 1.7
 */
public abstract class DeviceFeatureJsonUtils {

    private final static String SEPARATOR = ":";
    private static String FILE_PATH = "properties/DeviceFeatureConfig.json";
    private static Resource resource;
    private static ObjectMapper objectMapper;

    static {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        resource = resourceLoader.getResource(FILE_PATH);
        objectMapper = new ObjectMapper();
    }

    private DeviceFeatureJsonUtils() {
    }

    static List<FeatureJson> getFeatureSpecificationFromJsonFile() throws IOException {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructParametricType(List.class, FeatureJson.class);
        return objectMapper.readValue(resource.getFile(), javaType);
    }

    public static List<DeviceFeature> getDeviceFeatureList() throws IOException {
        List<FeatureJson> featureJsonList = getFeatureSpecificationFromJsonFile();
        List<DeviceFeature> deviceFeatureList = null;
        if (featureJsonList != null && featureJsonList.size() > 0) {
            deviceFeatureList = new ArrayList<>();
            for (FeatureJson featureJson : featureJsonList) {
                DeviceFeature deviceFeature = convert(featureJson);
                deviceFeatureList.add(deviceFeature);
            }
        }
        return deviceFeatureList;
    }

    private static DeviceFeature convert(FeatureJson featureJson) {
        if (featureJson == null) {
            return null;
        }
        DeviceFeature deviceFeature = new DeviceFeature(
                featureJson.id,
                featureJson.name,
                featureJson.desc,
                fetchFeatureArg(featureJson.inputArgs),
                featureJson.stubIdentityList,
                fetchArgMapping(featureJson.inputArgMapping)
        );
        return deviceFeature;
    }

    private static List<DeviceFeature> convertAll(List<FeatureJson> featureJsonList) {
        if (featureJsonList == null) {
            return null;
        }
        List<DeviceFeature> deviceFeatureList = new ArrayList<>();
        for (FeatureJson featureJson : featureJsonList) {
            deviceFeatureList.add(convert(featureJson));
        }
        return deviceFeatureList;
    }

    private static List<FeatureArg> fetchFeatureArg(List<Argument> argumentList) {
        if (argumentList == null || argumentList.size() == 0) {
            return null;
        }
        List<FeatureArg> featureArgList = new ArrayList<>();
        for (Argument argument : argumentList) {
            FeatureArg featureArg = new FeatureArg(
                    argument.name,
                    argument.dataType,
                    argument.screenName
            );
            featureArgList.add(featureArg);
        }
        return featureArgList;
    }

    private static Map<String, Map<StubIdentity, String>> fetchArgMapping(List<Map<String, String>> inputArgMapping) {
        if (inputArgMapping == null || inputArgMapping.size() == 0) {
            return null;
        }
        Map<String, Map<StubIdentity, String>> argMapping = new HashMap<>();
        for (Map<String, String> mapping : inputArgMapping) {
            Set<String> keySet = mapping.keySet();
            for (String key : keySet) {
                String value = mapping.get(key);
                String[] str = value.split(SEPARATOR);
                StubIdentity stubIdentity = new StubIdentity(
                        Long.parseLong(str[0]),
                        Integer.parseInt(str[1])
                );
                Map<StubIdentity, String> stubIdentityStringMap = new HashMap<>();
                stubIdentityStringMap.put(stubIdentity, str[2]);
                argMapping.put(key, stubIdentityStringMap);
            }
        }
        return argMapping;
    }
}

class FeatureJson implements Serializable {
    public Integer id;
    public String name;
    public String desc;
    public List<Argument> inputArgs;
    public List<StubIdentity> stubIdentityList;
    public List<Map<String, String>> inputArgMapping;

    public FeatureJson() {
    }

    public FeatureJson(Integer id, String name, String desc,
                       List<Argument> inputArgs,
                       List<StubIdentity> stubIdentityList, List<Map<String, String>> inputArgMapping) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.inputArgs = inputArgs;
        this.stubIdentityList = stubIdentityList;
        this.inputArgMapping = inputArgMapping;
    }

    @Override
    public String toString() {
        return "FeatureJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", inputArgs=" + inputArgs +
                ", stubIdentityList=" + stubIdentityList +
                ", inputArgMapping=" + inputArgMapping +
                '}';
    }
}

class Argument implements Serializable {
    public Integer id;
    public String name;
    public DataType dataType;
    public String screenName;

    public Argument() {
    }

    public Argument(Integer id, String name, DataType dataType, String screenName) {
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.screenName = screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Argument argument = (Argument) o;

        if (id != null ? !id.equals(argument.id) : argument.id != null) return false;
        if (name != null ? !name.equals(argument.name) : argument.name != null) return false;
        return dataType == argument.dataType;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Argument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dataType=" + dataType +
                ", secreenName='" + screenName + '\'' +
                '}';
    }
}
