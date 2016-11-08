package com.anicloud.sunny.infrastructure.persistence.service.app;

import com.ani.bus.service.commons.dto.anistub.AniStub;
import com.anicloud.sunny.application.service.holder.SpringContextHolder;
import com.anicloud.sunny.application.service.sunny.stub.SunnyStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lihui on 16-10-28.
 */
@Component("sunnyStubPersistService")
public class SunnyStubPersistServiceImpl implements SunnyStubPersistService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SunnyStubPersistServiceImpl.class);
    private final static String FILE_PATH = "properties/StubMappings";

    @Override
    public Map<Integer,SunnyStub> fetchSunnyStubMappings() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        Map<Integer,SunnyStub> resultMap = new HashMap<>();
        try {
            String path = getClass().getResource("/").getPath()+FILE_PATH;

            fis = new FileInputStream(new File(path));
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String temp = null;
            while(!StringUtils.isEmpty(temp = br.readLine())) {
                String regex = "\\s+";
                temp = temp.trim();
                String[] strs = temp.split(regex);
                if(strs.length >= 3) {
                    int hashCode = Objects.hash(Long.valueOf(strs[0]), Integer.valueOf(strs[1]));
                    SunnyStub stubInvoker = (SunnyStub) SpringContextHolder.getBean(Class.forName(strs[2]));
                    resultMap.put(hashCode,stubInvoker);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null)
                    br.close();
                if(isr != null)
                    isr.close();
                if(fis != null)
                    fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultMap;
    }

    @Override
    public void update(AniStub aniStub,SunnyStub sunnyStub) throws Exception {
        String path = getClass().getResource("/").getPath()+FILE_PATH;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            fos = new FileOutputStream(new File(path),true);
            pw = new PrintWriter(fos,true);
            if(aniStub != null && sunnyStub != null) {
                String str = aniStub.getStubId() + "\t"+aniStub.getGroupId() + "\t" + sunnyStub.getClass().getName();
                pw.println();
                pw.print(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(pw != null)
                    pw.close();
                if(fos != null)
                    fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
