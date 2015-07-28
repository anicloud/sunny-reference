package com.anicloud.sunny.interfaces.web.websocket;

import com.anicloud.sunny.schedule.domain.strategy.ScheduleState;
import com.anicloud.sunny.schedule.domain.strategy.StrategyAction;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        StrategyInfo strategyInfo = new StrategyInfo();
        strategyInfo.strategyId = "1";
        strategyInfo.strategyName = "推送测试";
        strategyInfo.action = StrategyAction.START;
        strategyInfo.stage = 2;
        strategyInfo.state = ScheduleState.RUNNING;
        sendMessageToUser(hashUserId, strategyInfo);
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
    }

    /**
     * send message to the only user
     * @param hashUserId
     * @param strategyInfo
     */
    public static void sendMessageToUser(String hashUserId, StrategyInfo strategyInfo) {
        WebSocketSession session = sessionMaps.get(hashUserId);
        if (session != null && session.isOpen()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String strategyInfoJson = mapper.writeValueAsString(strategyInfo);
                TextMessage message = new TextMessage(strategyInfoJson);
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
