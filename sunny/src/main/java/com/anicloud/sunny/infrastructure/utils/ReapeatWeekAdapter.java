package com.anicloud.sunny.infrastructure.utils;

import java.util.Arrays;

/**
 * Created by wyf on 16-12-29.
 */
public class ReapeatWeekAdapter {
    public static String repeatWeektoString(String[] weeks){
        String result = null;
        if(weeks != null && weeks.length >0 ) {
            result = Arrays.toString(weeks);
            result = result.substring(1,result.length()-1);
        }
        return result;
    }

    public static String[] repeatWeektoArray(String repeatWeek){
        if(repeatWeek != null)
            return repeatWeek.split(",");
        else
            return null;
    }
}
