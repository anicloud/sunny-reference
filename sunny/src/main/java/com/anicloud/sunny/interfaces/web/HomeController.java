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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.CookieGenerator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public String login(HttpServletResponse response,HttpServletRequest request,
                        @CookieValue(value = Constants.SUNNY_COOKIE_USER_NAME, required = false) String currentUser,
                        @RequestParam(value = "model", defaultValue = "dashboard") String model) throws IOException {
        LOGGER.info("welcome to sunny login action. cookie user : {}", currentUser);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.MODEL_NAME, model);
        if (currentUser != null) {
            UserInfoDto userInfoDto = objectMapper.readValue(currentUser, UserInfoDto.class);
            UserDto userDto = userService.refreshUserToken(userInfoDto.hashUserId);
            // update user info
            userInfoDto = new UserInfoDto(userDto);
            writeUserInfoToCookie(userInfoDto, response);
            return userSession(request, response, userInfoDto);
        }
        return "login";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response, @RequestParam String code) {
        LOGGER.info("code is {}", code);

        AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForAccessToken(Constants.appClientDto);
        OAuth2AccessToken oAuth2AccessToken = auth2ClientService.getOAuth2AccessToken(code, authorizationCodeParameter);

        UserDto userDto = initService.initApplication(oAuth2AccessToken);
        UserInfoDto userInfoDto = new UserInfoDto(userDto);
        return userSession(request, response, userInfoDto);
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home() {
        LOGGER.info("welcome to sunny index action {}.");
        return "index";
    }

    @RequestMapping(value = {"/logout"},method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> logout(HttpServletRequest request,@RequestParam("hashUserId")String hashUserId) {
        Map<String, String> message = new HashMap<>();
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SUNNY_SESSION_NAME);
        SessionListener.userSessionMaps.remove(hashUserId);

        message.put("status", "success");
        message.put("message", "remove websocket session success.");
        return message;
    }

    private void writeUserInfoToCookie(UserInfoDto userInfoDto, HttpServletResponse response) {
        String currentUser = null;
        try {
            currentUser = objectMapper.writeValueAsString(userInfoDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        CookieGenerator cookieGenerator = new CookieGenerator();
        //cookieGenerator.setCookiePath(Constants.SUNNY_COOKIE_PATH);
        cookieGenerator.setCookieName(Constants.SUNNY_COOKIE_USER_NAME);
        cookieGenerator.setCookieMaxAge(Constants.SUNNY_COOKIE_MAX_AGE);
        cookieGenerator.addCookie(response, currentUser);
        //cookieGenerator.removeCookie(response);
    }

    private String userSession(HttpServletRequest request,HttpServletResponse response, UserInfoDto userInfoDto) {
        HttpSession session = request.getSession();
        String model = (String) session.getAttribute(Constants.MODEL_NAME);

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
                return "redirect:home#/app/" + model;
            }
        }else{
            writeUserInfoToCookie(userInfoDto, response);
            session.setAttribute(Constants.SUNNY_SESSION_NAME, userSessionInfo);
            return "redirect:home#/app/" + model;
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
