package com.anicloud.sunny.application.utils;

import org.springframework.core.io.*;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * Created by MRK on 2016/3/7.
 */
public class JsonFileUtils {
    private JsonFileUtils() {}

    public final static String FILE_PATH_AND_NAME = "properties/AniServiceInfo.json";

    public static String readJsonFile() {
        Resource resource = new ClassPathResource(FILE_PATH_AND_NAME);
        BufferedReader bufferedReader = null;
        String content = "";
        String line  = "";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            while ((line  = bufferedReader.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public static void writeJsonFile(String jsonFormatAniServiceInfo) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = ResourceUtils.getFile("classpath:" + FILE_PATH_AND_NAME);
            if (file.exists()) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                bufferedWriter.write(jsonFormatAniServiceInfo);
                bufferedWriter.flush();
            } else {
                System.out.println(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
