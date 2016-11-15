package com.anicloud.sunny.interfaces.web.session;

import com.ani.agent.service.service.websocket.AccountInvoker;
import com.ani.agent.service.service.websocket.AccountNotify;
import com.ani.agent.service.service.websocket.AniInvokerImpl;
import com.ani.bus.service.commons.dto.accountobject.AccountObject;
import com.ani.bus.service.commons.message.SocketMessage;
import com.anicloud.sunny.application.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaoyu on 15-9-29.
 */
public class SessionManager implements AccountNotify{
    private final static Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    /**
     * key - hashUserId:sessionId
     */
    private static Map<String, Vector<WebSocketSession>> userSessionMaps = new ConcurrentHashMap<>();

    public static void removeSession(String hashUserId, String sessionId) throws IOException, EncodeException {
        if (StringUtils.isNotEmpty(hashUserId)) {
            Vector<WebSocketSession> sessionVector = userSessionMaps.get(hashUserId);
            if (sessionVector != null) {
                int index = -1;

                for (int i=0;  i<sessionVector.size(); i++) {
                    WebSocketSession socketSession = sessionVector.get(i);
                    if (socketSession.getId().equals(sessionId)) {
                        index = i;
                        break;
                    }
                }
                sessionVector.removeElementAt(index);
                if(sessionVector.size() <= 0) {
                    //通知service-bus用户掉线
                    AccountInvoker accountInvoker = new AniInvokerImpl(Constants.aniServiceSession);
                    AccountObject accountObj = new AccountObject(Long.valueOf(hashUserId));
                    SocketMessage socketMessage = accountInvoker.logout(accountObj);
                }
            }
        }
    }

    public static void addSession(String hashUserId, WebSocketSession session) throws IOException, EncodeException {
        Vector<WebSocketSession> sessionVector = null;
        sessionVector = userSessionMaps.get(hashUserId);

        if (sessionVector != null) {
            sessionVector.add(session);
        } else {
            sessionVector = new Vector<>();
            sessionVector.add(session);

            //通知服务器客户端状态
            AccountInvoker accountInvoker = new AniInvokerImpl(Constants.aniServiceSession);
            Map<Long, List<Integer>> map = new HashMap<Long, List<Integer>>();
            List<Integer> list = new ArrayList<>();
            list.add(1);
            map.put(10L,list);
            AccountObject accountObj = new AccountObject(Long.valueOf(hashUserId),map);
            SocketMessage socketMessage = accountInvoker.registerAndLogin(accountObj);
        }
        userSessionMaps.put(hashUserId, sessionVector);
    }

    public static WebSocketSession getWebSocketSession(String hashUserId, String sessionId) {
        Vector<WebSocketSession> sessionVector = userSessionMaps.get(hashUserId);
        if (sessionVector != null) {
            for (WebSocketSession socketSession : sessionVector) {
                if (socketSession.getId().equals(sessionId)) {
                    return socketSession;
                }
            }
        }
        return null;
    }

    public static Vector<WebSocketSession> getWebSocketSession(String hashUserId) {
        Vector<WebSocketSession> sessionVector = userSessionMaps.get(hashUserId);
        return sessionVector;
    }

    @Override
    public void accountReconnectNotify() {
        AccountInvoker accountInvoker = new AniInvokerImpl(Constants.aniServiceSession);
        try {
            for (String hashUserId:userSessionMaps.keySet()) {
                AccountObject accountObj = new AccountObject(Long.valueOf(hashUserId));
                SocketMessage socketMessage = accountInvoker.registerAndLogin(accountObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }

    }
}
