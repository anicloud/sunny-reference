package com.anicloud.sunny.application.constant;

import com.ani.bus.service.commons.session.AniServiceSession;
import com.anicloud.sunny.application.service.sunny.stub.SunnyStub;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;
import com.anicloud.sunny.application.dto.app.AniServiceDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class Constants {
    private Constants() {}

    public static Map<Integer,SunnyStub> SUNNY_STUB_MAPPINGS;
    public final static String CURRENT_USER = "@current_user";
    public final static String SUNNY_APP_REGISTER_NAME = "sunny-client";
    public final static String SUNNY_COOKIE_USER_NAME = "sunny_user";
    public final static int SUNNY_COOKIE_MAX_AGE = 7 * 24 * 3600;
    public final static String SUNNY_COOKIE_PATH = "sunny";

    public final static String SUNNY_SESSION_NAME = "sunny_session";

    public final static String SUNNY_DEVICE_DEFAULT_GROUP = "default";
    public final static DeviceLogicState DEVICE_DEFAULT_LOGIC_STATE = DeviceLogicState.CLOSED;
    /**
     *  the device feature run as a strategy, use the strategy name to identify
     */
    public final static String STRATEGY_AS_DEVICE_FEATURE_RUN_NAME = "_PHONY_STRATEGY_";

    /**
     * the pattern type of the type trigger
     */
    public final static String TIME_TRIGGER_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static Long TOKEN_REFRESH_TIME_INTERVAL_IN_SECONDS = 1 * 60 * 60L;

    public static AniServiceDto aniServiceDto = null;
    /**
     * the different enter of sunny
     */
    public static String MODEL_NAME = "modelName";
    /**
     * the web socket session
     */
    public static AniServiceSession aniServiceSession;
    public static final ConcurrentHashMap<Long, List<Integer>> DEVICE_ID_RELATION_MAP = new ConcurrentHashMap<>();
}
