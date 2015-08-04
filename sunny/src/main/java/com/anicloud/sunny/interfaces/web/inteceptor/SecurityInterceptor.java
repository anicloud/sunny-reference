package com.anicloud.sunny.interfaces.web.inteceptor;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/8/1.
 */
public class SecurityInterceptor implements HandlerInterceptor{
    private List<String> excludeUrls;// 不需要拦截的url

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Cookie[] cookies = httpServletRequest.getCookies();
        String hashUserId = "";
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (Constants.SUNNY_COOKIE_USER_NAME.equals(cookies[i].getName())) {
                    String sunny_user = cookies[i].getValue();
                    ObjectMapper mapper = new ObjectMapper();
                    UserDto userDto = mapper.readValue(sunny_user, UserDto.class);
                    hashUserId = userDto.hashUserId;
                }
            }
        }
        String requestUri = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if(excludeUrls.contains(url)){
            return true;
        }else{
            if("".equals(hashUserId)){
                httpServletResponse.sendRedirect(contextPath+"/");
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
