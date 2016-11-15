package com.anicloud.sunny.interfaces.web.websocket;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by lihui on 2016/7/18.
 */
public class DeviceStrategyInfoHandshake extends HttpSessionHandshakeInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceStrategyInfoHandshake.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        LOG.info("before hand shake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            Cookie[] cookies = serverHttpRequest.getServletRequest().getCookies();
            Long hashUserId = null;
            for(int i=0;i<cookies.length;i++){
                if(Constants.SUNNY_COOKIE_USER_NAME.equals(cookies[i].getName())){
                    String sunny_user = cookies[i].getValue();
                    ObjectMapper mapper = new ObjectMapper();
                    UserDto userDto = mapper.readValue(sunny_user, UserDto.class);
                    hashUserId = userDto.hashUserId;
                }
            }
            HttpSession session = serverHttpRequest.getServletRequest().getSession(false);
            LOG.info("hashUserId:"+hashUserId+"session:"+session.getId());
            if (session != null&&!"".equals(hashUserId)) {
                attributes.put("hashUserId", hashUserId);
            }
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        LOG.info("after hands shake");
        super.afterHandshake(request, response, wsHandler, ex);
    }


}
