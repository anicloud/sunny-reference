package com.anicloud.sunny.web.controller;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.web.dto.UserSessionInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhaoyu on 15-5-27.
 */
public class BaseController {

    /**
     * remove user from session
     * @param request
     * @param hashUserId
     */
    public boolean removeUserFromSession(HttpServletRequest request, Long hashUserId) {
        HttpSession session = request.getSession();
        UserSessionInfo userSessionInfo = (UserSessionInfo) session.getAttribute(Constants.SUNNY_SESSION_NAME);
        if (userSessionInfo != null) {
            session.removeAttribute(Constants.SUNNY_SESSION_NAME);
            //SessionListener.userSessionMaps.remove(hashUserId);
            return true;
        } else {
            return false;
        }
    }

    public UserSessionInfo getCurrentSessionUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object object = session.getAttribute(Constants.SUNNY_SESSION_NAME);
        return (UserSessionInfo) object;
    }
}
