package com.anicloud.sunny.web.websocket;

import com.ani.agent.service.service.websocket.AccountInvoker;
import com.ani.agent.service.service.websocket.AniInvokerImpl;
import com.ani.bus.service.commons.dto.accountobject.AccountObject;
import com.ani.bus.service.commons.message.SocketMessage;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.JmsTypicalMessage;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.device.DeviceDto;
import com.anicloud.sunny.application.dto.device.DeviceStrategyInfoDto;
import com.anicloud.sunny.application.dto.strategy.StrategyDto;
import com.anicloud.sunny.web.dto.DeviceFormDto;
import com.anicloud.sunny.web.dto.StrategyFormDto;
import com.anicloud.sunny.web.session.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lihui on 2016/7/18.
 */
public class DeviceStrategyInfoHandler extends TextWebSocketHandler {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceStrategyInfoHandler.class);

    public DeviceStrategyInfoHandler() {}

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String hashUserId = String.valueOf(session.getAttributes().get("hashUserId"));
        SessionManager.addSession(hashUserId, session);
        int sessionMapSize = SessionManager.getWebSocketSession(hashUserId).size();
        //向平台汇报账户状态：通知上线
        if(sessionMapSize == 1) {
            AccountInvoker accountInvoker = new AniInvokerImpl(Constants.aniServiceSession);
            Map<Long, List<Integer>> map = new HashMap<Long, List<Integer>>();
            List<Integer> list = new ArrayList<>();
            list.add(1);
            map.put(10L,list);
            AccountObject accountObj = new AccountObject(Long.parseLong(hashUserId),map);
            SocketMessage socketMessage = accountInvoker.registerAndLogin(accountObj);
        }
        LOG.info("afterConnectionEstablished" + hashUserId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String hashUserId = session.getAttributes().get("hashUserId").toString();
        LOG.info("afterConnectionClosed" + hashUserId);
        SessionManager.removeSession(hashUserId, session.getId());
        int sessionMapSize = SessionManager.getWebSocketSession(hashUserId).size();
        //向平台汇报账户状态：通知下线
        if(sessionMapSize == 0) {
            AccountInvoker accountInvoker = new AniInvokerImpl(Constants.aniServiceSession);
            AccountObject accountObj = new AccountObject(Long.parseLong(hashUserId));
            SocketMessage socketMessage = accountInvoker.logout(accountObj);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    /**
     * send message to the only user
     * @param hashUserId
     * @param strategyDto
     */
    public static void sendMessageToUser(Long hashUserId, StrategyDto strategyDto) {
        //WebSocketSession session = sessionMaps.get(hashUserId);
        Vector<WebSocketSession> sessionVector = SessionManager.getWebSocketSession(String.valueOf(hashUserId));
        if(sessionVector == null) return;
        Enumeration<WebSocketSession> sessionEnumeration = sessionVector.elements();
        while (sessionEnumeration.hasMoreElements()) {
            WebSocketSession session = sessionEnumeration.nextElement();
            if (session != null && session.isOpen()) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    StrategyFormDto strategyFormDto = StrategyFormDto.convertToStrategyForm(strategyDto);
                    DeviceStrategyInfoDto infoDto = new DeviceStrategyInfoDto(1,strategyFormDto);
                    String strategyInfoJson = mapper.writeValueAsString(infoDto);
                    TextMessage message = new TextMessage(strategyInfoJson);
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendDeviceMessageToUser(Long hashUserId,Object deviceDto) {
        Vector<WebSocketSession> sessionVector = SessionManager.getWebSocketSession(String.valueOf(hashUserId));
        if(sessionVector == null) return;
        Enumeration<WebSocketSession> sessionEnumeration = sessionVector.elements();
        while (sessionEnumeration.hasMoreElements()) {
            WebSocketSession session = sessionEnumeration.nextElement();
            if (session != null && session.isOpen()) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    DeviceFormDto deviceFormDto = null;
                    if(deviceDto instanceof DeviceDto)
                        deviceFormDto = DeviceFormDto.convertToDeviceForm((DeviceDto) deviceDto);
                    else if(deviceDto instanceof DeviceAndUserRelationDto)
                        deviceFormDto = DeviceFormDto.convertToDeviceForm((DeviceAndUserRelationDto) deviceDto);
                    DeviceStrategyInfoDto infoDto = new DeviceStrategyInfoDto(0,deviceFormDto);
                    String jsonData = mapper.writeValueAsString(infoDto);
                    TextMessage message = new TextMessage(jsonData);
                    session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendDeviceStateToUser(JmsTypicalMessage message) {
        try {
                Vector<WebSocketSession> sessionVector = SessionManager.getWebSocketSession(String.valueOf(message.hashUserId));
                if(sessionVector == null) return;
                Enumeration<WebSocketSession> sessionEnumeration = sessionVector.elements();
                ObjectMapper mapper = new ObjectMapper();
                while (sessionEnumeration.hasMoreElements()) {
                    WebSocketSession session = sessionEnumeration.nextElement();
                    if (session != null && session.isOpen()) {
                        DeviceStrategyInfoDto infoDto = null;
                        if (message.messageType.equals(Constants.DEVICE_BOUND_MESSAGE)) {
                            List<DeviceAndUserRelationDto> deviceAndUserRelationDtos = (List<DeviceAndUserRelationDto>) message.messageInstance;
                            List<DeviceFormDto> deviceFormDtos = deviceAndUserRelationDtos.stream()
                                    .map(DeviceFormDto::convertToDeviceForm).collect(Collectors.toList());
                            infoDto = new DeviceStrategyInfoDto(2, deviceFormDtos);
                        } else if(message.messageType.equals(Constants.DEVICE_UNBOUND_MESSAGE)){
                            infoDto = new DeviceStrategyInfoDto(3,message.messageInstance);
                        }else if (message.messageType.equals(Constants.DEVICE_SHARE_MESSAGE)) {
                            List<DeviceAndUserRelationDto> deviceAndUserRelationDtos = (List<DeviceAndUserRelationDto>) message.messageInstance;
                            List<DeviceFormDto> deviceFormDtos = deviceAndUserRelationDtos.stream()
                                    .map(DeviceFormDto::convertToDeviceForm).collect(Collectors.toList());
                            infoDto = new DeviceStrategyInfoDto(4, deviceFormDtos);
                        } else if (message.messageType.equals(Constants.DEVICE_UNSHARE_MESSAGE)) {
                            infoDto = new DeviceStrategyInfoDto(5, message.messageInstance);
                        }
                        if (infoDto != null) {
                            String jsonData = mapper.writeValueAsString(infoDto);
                            TextMessage socketMessage = new TextMessage(jsonData);
                            session.sendMessage(socketMessage);
                        }
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
