package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.application.service.sunny.stub.SunnyStubMappings;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by lihui on 16-10-28.
 */
@Component("sunnyStubPersistServiceImpl")
public class SunnyStubPersistServiceImpl implements SunnyStubPersistService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SunnyStubPersistServiceImpl.class);
    private final static String FILE_PATH = "properties/SunnyStubMappings.json";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SunnyStubMappings fetchSunnyStubMappings() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(FILE_PATH);
        SunnyStubMappings stubMappings = null;
        if(resource.exists()) {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,SunnyStubMappings.class);
            stubMappings = objectMapper.readValue(resource.getURL(),javaType);
        }else {
            LOGGER.error("sunnystub information file not exists.");
            throw new IOException("sunnystub information file not exists.");
        }
        return stubMappings;
    }

    @Override
    public void update(SunnyStubMappings sunnyStubMappings) throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(FILE_PATH);
        File targetFile = resource.getFile();
        if(targetFile.exists()) {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            objectMapper.writeValue(targetFile,sunnyStubMappings);
        }else {
            LOGGER.error("sunnystub information file not exists.");
            throw new IOException("sunnystub information file not exists.");
        }
    }
}
