package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.fasterxml.jackson.core.JsonGenerationException;
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

/**
 * @autor zhaoyu
 * @date 16-4-5
 * @since JDK 1.7
 */
@Component(value = "aniServicePersistService")
public class JsonAniServicePersistServiceImpl implements AniServicePersistService {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonAniServicePersistServiceImpl.class);
    private final static String FILE_PATH = "properties/AniServiceInfo.json";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AniServiceDao fetchAniServiceInfo() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(FILE_PATH);
        AniServiceDao aniServiceDao = null;
        if (resource.exists()) {
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            aniServiceDao = objectMapper.readValue(resource.getURL(),AniServiceDao.class);
        } else {
            LOGGER.error("service information file not exists.");
            throw new IOException("service information file not exists.");
        }
        return aniServiceDao;
    }

    @Override
    public void update(AniServiceDao aniServiceDao) throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(FILE_PATH);
        File targetFile = resource.getFile();
        if(targetFile.exists()){
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
            objectMapper.writeValue(targetFile,aniServiceDao);
        } else {
            LOGGER.error("service information file not exists.");
            throw new IOException("service information file not exists.");
        }
    }
}
