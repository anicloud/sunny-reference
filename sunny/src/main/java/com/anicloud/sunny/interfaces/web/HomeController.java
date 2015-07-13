package com.anicloud.sunny.interfaces.web;

import com.ani.cel.service.manager.agent.core.AnicelServiceConfig;
import com.ani.cel.service.manager.agent.oauth2.model.AuthorizationCodeParameter;
import com.ani.cel.service.manager.agent.oauth2.model.GrantType;
import com.ani.cel.service.manager.agent.oauth2.model.OAuth2AccessToken;
import com.ani.cel.service.manager.agent.oauth2.service.OAuth2ClientService;
import com.ani.cel.service.manager.agent.oauth2.service.OAuth2ClientServiceImpl;
import com.ani.cel.service.manager.agent.user.model.SysUserDto;
import com.ani.cel.service.manager.agent.user.service.UserService;
import com.ani.cel.service.manager.agent.user.service.UserServiceImpl;
import com.anicloud.sunny.application.builder.OAuth2ParameterBuilder;
import com.anicloud.sunny.application.constant.Constants;
import com.anicloud.sunny.application.dto.app.AppClientDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.application.service.app.AppService;
import com.anicloud.sunny.application.service.init.ApplicationInitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

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

    private OAuth2ClientService auth2ClientService;
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public HomeController() {
        this.auth2ClientService = new OAuth2ClientServiceImpl(AnicelServiceConfig.getInstance());
        this.userService = new UserServiceImpl(AnicelServiceConfig.getInstance());
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String login() {
        LOGGER.info("welcome to sunny login action.");
        return "login";
    }

    @RequestMapping(value = "/redirect")
    public String redirect(HttpServletResponse response, @RequestParam String code) throws JsonProcessingException {
        LOGGER.info("code is {}", code);

        AppClientDto clientDto = appService.findByClientName(Constants.SUNNY_APP_REGISTER_NAME);
        AuthorizationCodeParameter authorizationCodeParameter = OAuth2ParameterBuilder.build(clientDto);
        OAuth2AccessToken oAuth2AccessToken = auth2ClientService.getOAuth2AccessToken(code, authorizationCodeParameter);

        UserDto userDto = initService.initApplication(oAuth2AccessToken);
        String currentUser = objectMapper.writeValueAsString(userDto);

        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookiePath(Constants.SUNNY_COOKIE_PATH);
        cookieGenerator.setCookieName(Constants.SUNNY_COOKIE_USER_NAME);
        cookieGenerator.setCookieMaxAge(Constants.SUNNY_COOKIE_MAX_AGE);
        cookieGenerator.addCookie(response, currentUser);
        //cookieGenerator.removeCookie(response);

        return "redirect:home";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home() {
        LOGGER.info("welcome to sunny index action {}.");
        return "index";
    }
}
