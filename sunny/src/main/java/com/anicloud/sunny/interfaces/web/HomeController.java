package com.anicloud.sunny.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhaoyu on 15-5-27.
 */

@Controller
public class HomeController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String home() {
        LOG.info("welcome to sunny index action.");
        return "index";
    }
}
