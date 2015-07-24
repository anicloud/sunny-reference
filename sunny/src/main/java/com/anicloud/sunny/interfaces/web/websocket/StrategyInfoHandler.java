package com.anicloud.sunny.interfaces.web.websocket;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sirhuoshan on 2015/7/15.
 */
public class StrategyInfoHandler extends TextWebSocketHandler {
    private static final Logger LOG = LoggerFactory.getLogger(StrategyInfoHandler.class);

    private static Map<String, WebSocketSession> sessionMaps = new HashMap<String, WebSocketSession>();

    public StrategyInfoHandler() {}

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String hashUserId = (String) session.getAttributes().get("hashUserId");
        sessionMaps.put(hashUserId, session);
        LOG.info("afterConnectionEstablished" + hashUserId);
        String message = "****** Welcome "+hashUserId+"!  ******";
        TextMessage textMessage = new TextMessage(message);
        sendMessageToUser(hashUserId,textMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String hashUserId = (String) session.getAttributes().get("hashUserId");
        LOG.info("afterConnectionClosed" + hashUserId);
        sessionMaps.remove(hashUserId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        String hashUserId = (String) session.getAttributes().get("hashUserId");
        LOG.info("current user hashUserId : {}", hashUserId);
        String messageStr = "background recevie front message:"+message.getPayload();
        message = new TextMessage(messageStr);
        sendMessageToUser(hashUserId,message);
    }

    /**
     * send message to the only user
     * @param hashUserId
     * @param message
     */
    public void sendMessageToUser(String hashUserId, TextMessage message) {
        WebSocketSession session = sessionMaps.get(hashUserId);
        if (session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
