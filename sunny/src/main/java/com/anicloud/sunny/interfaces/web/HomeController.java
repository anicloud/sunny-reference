package com.anicloud.sunny.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by zhaoyu on 15-5-27.
 */

@Controller
public class HomeController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String home(HttpSession httpSession, @RequestParam(defaultValue = "zh_CN") String locale) {
        LOGGER.info("welcome to sunny index action.");
        httpSession.setAttribute("locale", locale);
        return "index";
    }
}
