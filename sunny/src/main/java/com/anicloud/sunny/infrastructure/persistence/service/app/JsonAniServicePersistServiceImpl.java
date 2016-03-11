package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */
@Component
public class JsonAniServicePersistServiceImpl implements AniServicePersistService {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(JsonAniServicePersistServiceImpl.class);

    @javax.annotation.Resource
    private ObjectMapper objectMapper;

    public final static String FILE_PATH_AND_NAME = "properties/AniServiceInfo.json";

    @Override
    public AniServiceDao findAniServiceInfo() throws FileNotFoundException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        org.springframework.core.io.Resource resource=resourceLoader.getResource(FILE_PATH_AND_NAME);
        AniServiceDao aniServiceDao = null;
        try {
            aniServiceDao = objectMapper.readValue(resource.getURL(),AniServiceDao.class);
        } catch (IOException e) {
            throw new FileNotFoundException("json file not exists.");
        }
        return aniServiceDao;
    }

    @Override
    public AniServiceDao save(AniServiceDao aniServiceDao) throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        org.springframework.core.io.Resource resource=resourceLoader.getResource(FILE_PATH_AND_NAME);
        File targetFile=resource.getFile();
        if(targetFile.exists()){
            objectMapper.writeValue(targetFile,aniServiceDao);
        }
        return aniServiceDao;
    }

    @Override
    public AniServiceDao update(AniServiceDao aniServiceDao) throws IOException {
        return save(aniServiceDao);
    }
}
