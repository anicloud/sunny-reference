package com.anicloud.sunny.utils;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by MRK on 2016/3/8.
 */
public class SetJoinTest {

    @Test
    public void testSetJoin() {
        Set<String> set = new HashSet<>();
        set.add("zhang");
        set.add("zhao");
        set.add("yu");

        String res = StringUtils.collectionToCommaDelimitedString(set);
        // String res = StringUtils.collectionToDelimitedString(set, "-");
        System.out.println(res);
        Set<String> stringSet = StringUtils.commaDelimitedListToSet(res);
        System.out.println(stringSet);
    }
}
