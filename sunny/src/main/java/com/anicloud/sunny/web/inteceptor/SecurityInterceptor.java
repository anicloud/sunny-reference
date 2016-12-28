package com.anicloud.sunny.web.inteceptor;

import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.web.dto.UserSessionInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by sirhuoshan on 2015/8/1.
 */
public class SecurityInterceptor implements HandlerInterceptor {
    private List<String> excludeUrls;// no need handle url

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        UserSessionInfo userSessionInfo = (UserSessionInfo)session.getAttribute(Constants.SUNNY_SESSION_NAME);


        String requestUri = extractUrl(request.getRequestURI());
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if(excludeUrls.contains(url)){
            return true;
        } else{
            if(userSessionInfo == null){
                response.sendRedirect(contextPath + "/");
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

    private String extractUrl(String url) {
        if (StringUtils.isNotEmpty(url) && url.contains("jsessionid")) {
            int position = url.indexOf("jsessionid");
            return url.substring(0, position-1);
        } {
            return url;
        }
    }
}
