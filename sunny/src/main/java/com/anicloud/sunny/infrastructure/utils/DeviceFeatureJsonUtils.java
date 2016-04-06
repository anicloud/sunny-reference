package com.anicloud.sunny.infrastructure.utils;

import com.anicloud.sunny.application.dto.device.DeviceFeatureDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * @autor zhaoyu
 * @date 16-4-6
 * @since JDK 1.7
 */
public abstract class DeviceFeatureJsonUtils {
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

    public static List<DeviceFeatureDto> getDeviceFeatureDtoListFromJsonFile() throws IOException {
        JavaType javaType = objectMapper.getTypeFactory()
                .constructParametricType(List.class, DeviceFeatureDto.class);
        return objectMapper.readValue(resource.getFile(), javaType);
    }

    public static void main(String[] args) throws IOException {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
        System.out.println(objectMapper.writeValueAsString(getDeviceFeatureDtoListFromJsonFile()));
    }
}
