package com.anicloud.sunny.application.exception;

/**
 * Created by MRK on 2016/3/15.
 */
public class RedisDataException extends RuntimeException {

    public RedisDataException(String message) {
        super(message);
    }

    public RedisDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisDataException(Throwable cause) {
        super(cause);
    }

    protected RedisDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RedisDataException() {
        super();
    }
}
