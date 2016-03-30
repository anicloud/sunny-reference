package com.anicloud.sunny.kamili.application.utils;

import com.ani.bus.service.commons.dto.anidevice.stub.DataType;
import com.anicloud.sunny.kamili.domain.model.device.DeviceFeature;
import com.anicloud.sunny.kamili.domain.model.device.FeatureArg;
import com.anicloud.sunny.kamili.domain.model.device.StubIdentity;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by MRK on 2016/3/17.
 */
public abstract class DeviceFeatureJsonUtils implements Serializable {

    private static String FILE_PATH = "properties/DeviceFeatureInfo.json";
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

    public static DeviceFeature convert(FeatureJson featureJson) {
        if (featureJson == null) {
            return null;
        }
        DeviceFeature deviceFeature = new DeviceFeature();
        if (featureJson.inputArgs == null || featureJson.inputArgMapping == null) {
            deviceFeature.id = featureJson.id;
            deviceFeature.name = featureJson.name;
            deviceFeature.desc = featureJson.desc;
            deviceFeature.stubIdentityList = null;
            deviceFeature.inputArgMapping = null;
        } else {
            deviceFeature.id = featureJson.id;
            deviceFeature.name = featureJson.name;
            deviceFeature.desc = featureJson.desc;
            Set<FeatureArg> featureArgs = new HashSet<>();
            for (Argument arg : featureJson.inputArgs) {
                FeatureArg featureArg = new FeatureArg(arg.id, arg.name, arg.dataType, arg.screenName);
                featureArgs.add(featureArg);
            }
            deviceFeature.stubIdentityList = featureJson.stubIdentityList;
            deviceFeature.inputArgs = featureArgs;
            List<Map<String, String>> inputMap = featureJson.inputArgMapping;

            for (Map<String, String> inMap : inputMap) {
                Iterator<Map.Entry<String, String>> iterator = inMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    Map<String, Map<StubIdentity, String>> inputArgMapping = new HashMap<>();
                    String[] inputArg = entry.getValue().split(":");
                    StubIdentity stubIdentity = new StubIdentity();
                    stubIdentity.groupId = new Long(inputArg[0]);
                    stubIdentity.stubId = Integer.parseInt(inputArg[1]);
                    Map<StubIdentity, String> stubIdentityMap = new HashMap<>();
                    stubIdentityMap.put(stubIdentity, inputArg[2]);
                    inputArgMapping.put(entry.getKey(), stubIdentityMap);
                    deviceFeature.inputArgMapping = inputArgMapping;
                }
            }
        }
        return deviceFeature;
    }

    public static List<DeviceFeature> convertAll(List<FeatureJson> featureJsonList) {
        if (featureJsonList == null) {
            return null;
        }
        List<DeviceFeature> deviceFeatureList = new ArrayList<>();
        for (FeatureJson featureJson : featureJsonList) {
            deviceFeatureList.add(convert(featureJson));
        }
        return deviceFeatureList;
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

    public static void main(String[] args) throws IOException {
        List<FeatureJson> featureJsonList = DeviceFeatureJsonUtils.getFeatureSpecificationFromJsonFile();
        List<DeviceFeature> deviceFeature = DeviceFeatureJsonUtils.convertAll(featureJsonList);
        for (DeviceFeature feature : deviceFeature) {
            System.out.println(feature);
        }
    }
}

