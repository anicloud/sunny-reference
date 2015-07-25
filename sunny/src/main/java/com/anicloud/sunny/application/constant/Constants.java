package com.anicloud.sunny.application.constant;

import com.anicloud.sunny.application.dto.app.AppClientDto;
import com.anicloud.sunny.infrastructure.persistence.domain.share.DeviceLogicState;

/**
 * Created by zhaoyu on 15-6-27.
 */
public class Constants {
    private Constants() {}

    public final static String CURRENT_USER = "@current_user";
    public final static String SUNNY_APP_REGISTER_NAME = "sunny-client";
    public final static String SUNNY_COOKIE_USER_NAME = "sunny_user";
    public final static int SUNNY_COOKIE_MAX_AGE = 7 * 24 * 3600;
    public final static String SUNNY_COOKIE_PATH = "sunny";

    public final static String SUNNY_DEVICE_DEFAULT_GROUP = "default";
    public final static DeviceLogicState DEVICE_DEFAULT_LOGIC_STATE = DeviceLogicState.CLOSED;

    public static AppClientDto appClientDto = null;
}
