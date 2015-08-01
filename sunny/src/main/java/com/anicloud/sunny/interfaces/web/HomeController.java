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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    public String login(HttpServletResponse response, @CookieValue(value = Constants.SUNNY_COOKIE_USER_NAME, required = false) String currentUser) throws IOException {
        LOGGER.info("welcome to sunny login action. cookie user : {}", currentUser);
        if (currentUser != null) {
            UserInfoDto userInfoDto = objectMapper.readValue(currentUser, UserInfoDto.class);
            userService.refreshUserToken(userInfoDto.hashUserId);
            return "redirect:home";
        }
        return "login";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletResponse response, @RequestParam String code) throws JsonProcessingException {
        LOGGER.info("code is {}", code);

        AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.buildForAccessToken(Constants.appClientDto);
        OAuth2AccessToken oAuth2AccessToken = auth2ClientService.getOAuth2AccessToken(code, authorizationCodeParameter);

        UserDto userDto = initService.initApplication(oAuth2AccessToken);
        writeUserInfoToCookie(userDto, response);
        return "redirect:home";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home() {
        LOGGER.info("welcome to sunny index action {}.");
        return "index";
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
}
