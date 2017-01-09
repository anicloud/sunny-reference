package com.anicloud.sunny.application.dto;

import java.io.Serializable;

/**
 * Created by lihui on 17-1-9.
 */
public class JmsTypicalMessage implements Serializable {
    public Long hashUserId;
    public Object messageInstance;
    public String messageType;

    public JmsTypicalMessage(Object messageInstance, String messageType, Long hashUserIds) {
        this.messageInstance = messageInstance;
        this.messageType = messageType;
        this.hashUserId = hashUserIds;
    }
}
