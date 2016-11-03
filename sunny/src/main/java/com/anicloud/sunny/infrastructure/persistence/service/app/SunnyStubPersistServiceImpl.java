package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.anicloud.sunny.application.service.sunny.stub.SunnyStub;
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
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 16-10-28.
 */
@Component("sunnyStubPersistService")
public class SunnyStubPersistServiceImpl implements SunnyStubPersistService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SunnyStubPersistServiceImpl.class);
    private final static String FILE_PATH = "properties/StubMappings";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Map<Integer,SunnyStub> fetchSunnyStubMappings() throws Exception {
        String path = getClass().getResource("/").getPath()+FILE_PATH;
        Map<Integer,SunnyStub> resultMap = new HashMap<>();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)));
        BufferedReader br = new BufferedReader(isr);
        String temp = null;
        while(!StringUtils.isEmpty(temp = br.readLine())) {
            String regex = "\\s+";
            temp = temp.trim();
            String[] strs = temp.split(regex);
            if(strs.length >= 3) {
                int hashCode = (strs[0] + strs[1]).hashCode();
                SunnyStub stub = (SunnyStub) Class.forName(strs[2]).newInstance();
                resultMap.put(hashCode,stub);
            }
        }
        return resultMap;
    }

    @Override
    public void update(AniStub aniStub,SunnyStub sunnyStub) throws Exception {
//        OutputStreamWriter osw = new OutputStreamWriter();
    }
}
