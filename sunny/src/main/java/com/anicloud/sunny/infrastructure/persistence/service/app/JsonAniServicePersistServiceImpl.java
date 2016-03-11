package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.anicloud.sunny.application.utils.JsonFileUtils;
import com.anicloud.sunny.infrastructure.persistence.domain.app.AniServiceDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @autor zhaoyu
 * @date 16-3-7
 * @since JDK 1.7
 */

public class JsonAniServicePersistServiceImpl implements AniServicePersistService {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(JsonAniServicePersistServiceImpl.class);

    @Resource
    private ObjectMapper objectMapper;


    @Override
    public AniServiceDao findAniServiceInfo() {
        String jsonStr = JsonFileUtils.readJsonFile();
        AniServiceDao aniServiceDao = null;
        try {
            aniServiceDao = objectMapper.readValue(jsonStr, AniServiceDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aniServiceDao;
    }

    @Override
    public AniServiceDao save(AniServiceDao aniServiceDao) {
        try {
            String jsonStr = objectMapper.writeValueAsString(aniServiceDao);
            JsonFileUtils.writeJsonFile(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return aniServiceDao;
    }

    @Override
    public AniServiceDao update(AniServiceDao aniServiceDao) {
        return save(aniServiceDao);
    }
}
