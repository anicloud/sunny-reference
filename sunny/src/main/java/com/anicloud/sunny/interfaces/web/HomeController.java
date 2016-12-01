package com.anicloud.sunny.interfaces.web;

import com.ani.agent.service.commons.oauth.dto.AniOAuthAccessToken;
import com.ani.agent.service.commons.oauth.dto.AuthorizationCodeParameter;
import com.ani.agent.service.core.config.AnicelMeta;
import com.ani.agent.service.core.websocket.WebSocketClient;
import com.ani.agent.service.core.websocket.WebSocketSessionFactory;
import com.ani.agent.service.service.aniservice.AniServiceManager;
import com.ani.agent.service.service.websocket.*;
import com.ani.agent.service.service.websocket.observer.AniObjectCallMessageObserver;
import com.ani.bus.service.commons.dto.accountobject.AccountObject;
import com.ani.bus.service.commons.dto.aniservice.AniServiceDto;
import com.ani.bus.service.commons.dto.aniservice.AniServiceRegisterDto;
import com.ani.bus.service.commons.dto.aniservice.LanguageEnum;
import com.ani.bus.service.commons.message.SocketMessage;
import com.ani.agent.service.service.AgentTemplate;
import com.ani.bus.service.commons.observer.MessageObserver;
import com.anicloud.sunny.application.builder.OAuth2ParameterBuilder;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.dto.user.UserInfoDto;
import com.anicloud.sunny.application.service.agent.ClientInvokerImpl;
import com.anicloud.sunny.application.service.init.ApplicationInitService;
import com.anicloud.sunny.application.service.user.UserService;
import com.anicloud.sunny.domain.adapter.AniServiceDaoAdapter;
import com.anicloud.sunny.interfaces.facade.AppServiceFacade;
import com.anicloud.sunny.interfaces.web.dto.UserSessionInfo;
import com.anicloud.sunny.interfaces.web.listener.SessionListener;
import com.anicloud.sunny.interfaces.web.session.SessionManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import javassist.bytecode.stackmap.BasicBlock;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.CookieGenerator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.ani.bus.service.commons.dto.aniservice.AniServiceInfoDto;
/**
 * Created by zhaoyu on 15-5-27.
 */

@Controller
public class HomeController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Value("${servicebus.oauth.url}")
    private  String OAUTH_URL;
    @Value("${servicebus.logout.url}")
    private  String LOGOUT_URL;
    @Resource
    private ApplicationInitService initService;
    @Resource
    private AppServiceFacade appServiceFacade;
    @Resource
    private UserService userService;
    @Resource
    private AgentTemplate agentTemplate;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private AnicelMeta anicelMeta;
    @Resource(name = "objectNotify")
    private ObjectNotify objectNotify;

    @PostConstruct
    public void init() {
        try {
            Constants.aniServiceDto = appServiceFacade.getAniServiceInfo();
            AniServiceManager aniServiceManager = agentTemplate.getAniServiceManager();
            String aniServiceID = Constants.aniServiceDto.aniServiceId;
            String clientSecret = Constants.aniServiceDto.clientSecret;

            AniServiceRegisterDto aniServiceRegisterDto = CreateRegisterDto();
            AniServiceDto aniServiceDto;
            if(aniServiceID.equals("")||clientSecret.equals("")){
                aniServiceDto = null;
            }else{
                aniServiceDto = aniServiceManager.getByAniService(aniServiceID,clientSecret);
            }
//            if(aniServiceDto== null){
//                 aniServiceDto = aniServiceManager.register(aniServiceRegisterDto);
//                 Constants.aniServiceDto.clientSecret = aniServiceDto.clientSecret;
//                 Constants.aniServiceDto.aniServiceId = aniServiceDto.aniServiceId;
//                 appServiceFacade.update(Constants.aniServiceDto);
//                LOGGER.debug("registing the new application");
//                return ;
//            }else{
//                LOGGER.debug("init AniService information.");
//            }
        } catch (IOException e) {
            LOGGER.error("read sunny basic info error. msg {}.", e.getMessage());
            e.printStackTrace();
        }catch (java.lang.Exception e2){
            LOGGER.error("get sunny aniServiceDto error. msg {}.", e2.getMessage());
            e2.printStackTrace();
        }
        // you need to implement the Invokable interface and register on
        // WebSocketClient for anicloud platform to callback
        ClientInvokable invokable = new ClientInvokerImpl();
        AccountNotify accountNotify = new SessionManager();
        WebSocketClient socketClient = new WebSocketClient(invokable,objectNotify,accountNotify);

        // you need to implement your own observer and register on socketClient
        // to receive the message from anicloud platform
        Vector<MessageObserver> messageObservers = new Vector<>();
        messageObservers.add(new AniObjectCallMessageObserver());
        socketClient.setObs(messageObservers);

        // inject your WebSocketClient instance and anicloud socket destination url to factory
        // and use factory to get the session, than you can use the session to communicate
        // with anicloud platform
        WebSocketSessionFactory sessionFactory = new WebSocketSessionFactory(
                socketClient,
                anicelMeta,
                Constants.aniServiceDto.aniServiceId,
                Constants.aniServiceDto.clientSecret
        );
        Constants.aniServiceSession = sessionFactory.getAniServiceSession();
        LOGGER.info("build ani service session success.");
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
            LOGGER.info("redirect:"+OAUTH_URL);
            return "redirect:"+OAUTH_URL;
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
        AniOAuthAccessToken oAuth2AccessToken = agentTemplate.getAniOAuthService().getOAuth2AccessToken(code, authorizationCodeParameter);

        UserDto userDto = null;
        try {
            userDto = initService.initApplication(oAuth2AccessToken);
        } catch (Exception e) {
            // TODO
            // do something with the error
            e.printStackTrace();
        }
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

//    @RequestMapping(value = {"/logout"},method = RequestMethod.POST)
//    public Map<String, String> logout(HttpServletRequest request,HttpServletResponse response,@RequestParam("hashUserId")Long hashUserId,
//                                      @CookieValue(value = Constants.SUNNY_COOKIE_USER_NAME, required = false) String currentUser) {
//        Map<String, String> message = new HashMap<>();
//
//        if (removeUserFromSession(request, hashUserId)) {
//            message.put("status", "success");
//            message.put("message", "remove web socket session success.");
//        } else {
//            message.put("status", "error");
//            message.put("message", "user not in the session list.");
//        }
//        return message;
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response,
                             @CookieValue(value = Constants.SUNNY_COOKIE_USER_NAME, required = false) String currentUser) {
        try {
            if(currentUser != null){
                UserInfoDto userInfoDto = objectMapper.readValue(currentUser, UserInfoDto.class);
                removeUserInfoFromCookie(userInfoDto,response);
                removeUserFromSession(request, Long.parseLong(userInfoDto.hashUserId));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        LOGGER.info("redirect:"+LOGOUT_URL);
//        return "redirect:http://bj-yatsen.anicel.cn:8080/service-bus/logout";
        return "redirect:"+LOGOUT_URL;
    }

//    @RequestMapping(value = "switchUser", method = RequestMethod.GET)
//    public String switchUser(HttpServletRequest request, Model model, @RequestParam("hashUserId") Long hashUserId) {
//        removeUserFromSession(request, hashUserId);
//        UserDto userDto = userService.getUserByHashUserId(hashUserId);
//        model.addAttribute("previousUser", new UserInfoDto(userDto));
//        return "loginPage";
//    }

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
        cookieGenerator.setCookieMaxAge(-1);
        cookieGenerator.addCookie(response, currentUser);
        //cookieGenerator.removeCookie(response);
    }

    private void removeUserInfoFromCookie(UserInfoDto userInfoDto,HttpServletResponse response){
        String currentUser = null;
        try {
            currentUser = objectMapper.writeValueAsString(userInfoDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName(Constants.SUNNY_COOKIE_USER_NAME);
        cookieGenerator.setCookieMaxAge(0);
        cookieGenerator.addCookie(response, currentUser);
    }
    private String userSession(HttpServletRequest request,HttpServletResponse response, UserInfoDto userInfoDto) {
        HttpSession session = request.getSession();
        String model = (String) session.getAttribute(Constants.MODEL_NAME);

        UserSessionInfo userSessionInfo = new UserSessionInfo();
        userSessionInfo.hashUserId = Long.parseLong(userInfoDto.hashUserId);
//        userSessionInfo.ipAddr = getIpAddr(request);
        //HttpSession sessionOld = SessionListener.userSessionMaps.get(userSessionInfo.hashUserId);
        writeUserInfoToCookie(userInfoDto, response);
//        if(sessionOld == null) {
//            writeUserInfoToCookie(userInfoDto, response);
            session.setAttribute(Constants.SUNNY_SESSION_NAME, userSessionInfo);
//        }
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

    private AniServiceRegisterDto CreateRegisterDto()
    {
        Set<LanguageEnum> languageEnumSet  =new HashSet<>();
        for(String lan :Constants.aniServiceDto.languageSet){
            LanguageEnum languageEnum = (LanguageEnum)Enum.valueOf(LanguageEnum.class,lan.trim());
            languageEnumSet.add(languageEnum);
        }
        AniServiceInfoDto aniserviceinfo = new AniServiceInfoDto(
                null,
                Constants.aniServiceDto.serviceServerUrl,
                Constants.aniServiceDto.logoPath,
                languageEnumSet,
                Constants.aniServiceDto.tagSet,
                Constants.aniServiceDto.price,
                Constants.aniServiceDto.onShelf,
                Constants.aniServiceDto.description
        );
        List<com.ani.bus.service.commons.dto.aniservice.AniServiceEntranceDto> aniServiceEntranceDto =
                AniServiceDaoAdapter.fromCommonsToLocal(Constants.aniServiceDto.entranceList);

        AniServiceRegisterDto aniServiceRegisterDto = new AniServiceRegisterDto(
                Constants.aniServiceDto.aniServiceId,
                Constants.aniServiceDto.serviceName,
                Constants.aniServiceDto.version,
                Constants.aniServiceDto.webServerRedirectUri,
                Constants.aniServiceDto.accountId,
                aniServiceEntranceDto,
                aniserviceinfo,
                null
        );
        aniServiceRegisterDto.addStub(1L, 1);

        return aniServiceRegisterDto;

    }
}
