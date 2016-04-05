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
import com.anicloud.sunny.application.service.init.ApplicationInitService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.interfaces.facade.AppServiceFacade;
import com.anicloud.sunny.interfaces.web.dto.UserSessionInfo;
import com.anicloud.sunny.interfaces.web.listener.SessionListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private static final String authUrl = "http://dev.anicloud.cn:8222/rect-manager/oauth/authorize?client_id=sunny-client&redirect_uri=http://localhost:8080/sunny/redirect&response_type=code&scope=read write";

    @Resource
    private ApplicationInitService initService;
    @Resource(name = "appServiceFacade")
    private AppServiceFacade appServiceFacade;
    @Resource
    private UserService userService;

    private OAuth2ClientService auth2ClientService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            Constants.aniServiceDto = appServiceFacade.getAniServiceInfo();
        } catch (IOException e) {
            LOGGER.error("fetch AniService information error, {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public HomeController() {
        this.auth2ClientService = new OAuth2ClientServiceImpl(AnicelServiceConfig.getInstance());
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response,
                        @CookieValue(value = Constants.SUNNY_COOKIE_USER_NAME, required = false) String currentUser,
                        @RequestParam(value = "model", defaultValue = "dashboard") String model) throws IOException {
        LOGGER.info("welcome to sunny login action. cookie user : {}", currentUser);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.MODEL_NAME, model);
        if (currentUser != null) {
            UserInfoDto userInfoDto = objectMapper.readValue(currentUser, UserInfoDto.class);
            return userSession(request, response, userInfoDto);
        } else {
            System.out.println("redirect:loginPage");
            return "redirect:loginPage";
        }
    }

    @RequestMapping(value = {"/loginPage"}, method = RequestMethod.GET)
    public String loginPage() throws IOException {
        System.out.println("loginPage");
        return "loginPage";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response, @RequestParam String code) {
        LOGGER.info("code is {}", code);

        AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForAccessToken(Constants.aniServiceDto);
        OAuth2AccessToken oAuth2AccessToken = auth2ClientService.getOAuth2AccessToken(code, authorizationCodeParameter);

        UserDto userDto = initService.initApplication(oAuth2AccessToken);
        UserInfoDto userInfoDto = new UserInfoDto(userDto);
        return userSession(request, response, userInfoDto);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,
                        Model model, @RequestParam(value = "email") String email) {
        UserDto userDto = userService.getUserByEmail(email);
        if (userDto != null) {
            return userSession(request, response, new UserInfoDto(userDto));
        } else {
            model.addAttribute("errorMsg", "User's " + email + " was not authorized before!");
            return "authPage";
        }
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        LOGGER.info("welcome to sunny index action {}.");
        UserSessionInfo userSessionInfo = getCurrentSessionUserInfo(request);
        if (userSessionInfo != null) {
            try {
                UserDto userDto = userService.refreshUserToken(userSessionInfo.hashUserId);
            } catch (Exception e) {
                LOGGER.info("refresh user token error!!");
                model.addAttribute("errorMsg", "User's refresh token was expired! Please authorize again!");
                return "loginPage";
            }
        }
        return "index";
    }

    @RequestMapping(value = {"/logout"},method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> logout(HttpServletRequest request,@RequestParam("hashUserId")String hashUserId) {
        Map<String, String> message = new HashMap<>();

        if (removeUserFromSession(request, hashUserId)) {
            message.put("status", "success");
            message.put("message", "remove web socket session success.");
        } else {
            message.put("status", "error");
            message.put("message", "user not in the session list.");
        }
        return message;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage() {
        return "logout";
    }

    @RequestMapping(value = "switchUser", method = RequestMethod.GET)
    public String switchUser(HttpServletRequest request, Model model, @RequestParam("hashUserId") String hashUserId) {
        removeUserFromSession(request, hashUserId);
        UserDto userDto = userService.getUserByHashUserId(hashUserId);
        model.addAttribute("previousUser", new UserInfoDto(userDto));
        return "loginPage";
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
        if(sessionOld == null) {
            writeUserInfoToCookie(userInfoDto, response);
            session.setAttribute(Constants.SUNNY_SESSION_NAME, userSessionInfo);
        }
        return "redirect:home#/app/" + model;
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
