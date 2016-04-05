package com.anicloud.sunny.interfaces.web.listener;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.interfaces.web.dto.UserSessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sirhuoshan on 2015/8/10.
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    private static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

    public static Map<Long, HttpSession> userSessionMaps = new HashMap<Long, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOG.info(httpSessionEvent.getSession().getId() + "created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LOG.info(httpSessionEvent.getSession().getId() + "destroyed");
        UserSessionInfo userSessionInfoOld = (UserSessionInfo)httpSessionEvent.getSession().getAttribute(Constants.SUNNY_SESSION_NAME);
        Long hashUserId = userSessionInfoOld.hashUserId;
        userSessionMaps.remove(hashUserId);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        UserSessionInfo userSessionInfo = (UserSessionInfo)session.getAttribute(Constants.SUNNY_SESSION_NAME);
        userSessionMaps.put(userSessionInfo.hashUserId,session);
        LOG.info(userSessionInfo.hashUserId.toString());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
