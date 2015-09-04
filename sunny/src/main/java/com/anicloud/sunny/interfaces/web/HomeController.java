package com.anicloud.sunny.interfaces.web;

import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.ani.cel.service.manager.agent.oauth2.model.AuthorizationCodeParameter;
import com.ani.cel.service.manager.agent.oauth2.model.OAuth2AccessToken;
import com.ani.cel.service.manager.agent.oauth2.service.OAuth2ClientService;
import com.ani.cel.service.manager.agent.oauth2.service.OAuth2ClientServiceImpl;
import com.anicloud.sunny.application.builder.OAuth2ParameterBuilder;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.dto.user.UserInfoDto;
import com.anicloud.sunny.application.service.app.AppService;
import com.anicloud.sunny.application.service.init.ApplicationInitService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.interfaces.web.dto.UserSessionInfo;
import com.anicloud.sunny.interfaces.web.listener.SessionListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.CookieGenerator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhaoyu on 15-5-27.
 */

@Controller
public class HomeController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    @Resource
    private ApplicationInitService initService;
    @Resource
    private AppService appService;
    @Resource
    private UserService userService;

    private OAuth2ClientService auth2ClientService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        Constants.appClientDto = appService.findByClientName(Constants.SUNNY_APP_REGISTER_NAME);
    }

    public HomeController() {
        this.auth2ClientService = new OAuth2ClientServiceImpl(AnicelServiceConfig.getInstance());
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String login(HttpServletResponse response,HttpServletRequest request, @CookieValue(value = Constants.SUNNY_COOKIE_USER_NAME, required = false) String currentUser) throws IOException {
        LOGGER.info("welcome to sunny login action. cookie user : {}", currentUser);
        if (currentUser != null) {
            UserInfoDto userInfoDto = objectMapper.readValue(currentUser, UserInfoDto.class);
            userService.refreshUserToken(userInfoDto.hashUserId);
            return userSession(request,userInfoDto);
        }
        return "login";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletResponse response,HttpServletRequest request, @RequestParam String code) throws JsonProcessingException {
        LOGGER.info("code is {}", code);

        AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForAccessToken(Constants.appClientDto);
        OAuth2AccessToken oAuth2AccessToken = auth2ClientService.getOAuth2AccessToken(code, authorizationCodeParameter);

        UserDto userDto = initService.initApplication(oAuth2AccessToken);
        writeUserInfoToCookie(userDto, response);
        return userSession(request,new UserInfoDto(userDto));
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home() throws IOException {
        LOGGER.info("welcome to sunny index action {}.");
        return "index";
    }

    @RequestMapping(value = {"/logout"},method = RequestMethod.GET)
    public String logout(HttpServletRequest request,@RequestParam("hashUserId")String hashUserId){
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SUNNY_SESSION_NAME);
        SessionListener.userSessionMaps.remove(hashUserId);
        return "login";
    }

    private void writeUserInfoToCookie(UserDto userDto, HttpServletResponse response) throws JsonProcessingException {
        UserInfoDto userInfoDto = new UserInfoDto(userDto);
        String currentUser = objectMapper.writeValueAsString(userInfoDto);
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookiePath(Constants.SUNNY_COOKIE_PATH);
        cookieGenerator.setCookieName(Constants.SUNNY_COOKIE_USER_NAME);
        cookieGenerator.setCookieMaxAge(Constants.SUNNY_COOKIE_MAX_AGE);
        cookieGenerator.addCookie(response, currentUser);
        //cookieGenerator.removeCookie(response);
    }

    private String userSession(HttpServletRequest request,UserInfoDto userInfoDto){
        HttpSession session = request.getSession();
        UserSessionInfo userSessionInfo = new UserSessionInfo();
        userSessionInfo.hashUserId = userInfoDto.hashUserId;
        userSessionInfo.ipAddr = getIpAddr(request);
        HttpSession sessionOld = SessionListener.userSessionMaps.get(userSessionInfo.hashUserId);
        if(sessionOld != null){
            UserSessionInfo userSessionInfoOld = (UserSessionInfo)sessionOld.getAttribute(Constants.SUNNY_SESSION_NAME);
            if(!userSessionInfoOld.ipAddr.equals(userSessionInfo.ipAddr)){
                return "multiLogin";
            }else{
                session.setAttribute(Constants.SUNNY_SESSION_NAME, userSessionInfo);
                return "redirect:home";
            }
        }else{
            session.setAttribute(Constants.SUNNY_SESSION_NAME, userSessionInfo);
            return "redirect:home";
        }
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
