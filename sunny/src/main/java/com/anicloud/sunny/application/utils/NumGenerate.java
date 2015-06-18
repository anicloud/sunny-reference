package com.anicloud.sunny.application.utils;

import java.util.UUID;

/**
 * Created by zhaoyu on 15-6-15.
 */
public class NumGenerate {

    private NumGenerate() {}

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
