package com.anicloud.sunny.application.service.strategy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhaoyu on 15-6-17.
 */

public class TriggerTypeServiceHandlerTest {

    @Test
    public void testGetAllTriggerType() throws Exception {
        TriggerTypeService typeService = new TriggerTypeServiceHandler();
        System.out.println(typeService.getAllTriggerType());
    }
}