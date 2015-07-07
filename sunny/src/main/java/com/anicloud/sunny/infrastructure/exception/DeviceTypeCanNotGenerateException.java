package com.anicloud.sunny.infrastructure.exception;

/**
 * Created by zhaoyu on 15-7-5.
 */
public class DeviceTypeCanNotGenerateException extends Exception {

    public DeviceTypeCanNotGenerateException() {
        super();
    }

    public DeviceTypeCanNotGenerateException(String message) {
        super(message);
    }

    public DeviceTypeCanNotGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceTypeCanNotGenerateException(Throwable cause) {
        super(cause);
    }
}
