package com.jeasywebframework.web.controller.sys;

import com.jeasywebframework.domain.dept.LoginType;
import com.jeasywebframework.service.annotations.LoginRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Controller
@RequestMapping("/sys/")
public class IndexController {


    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @LoginRequired(type = LoginType.Login)
    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String index() {
        return "sys/index";
    }

    @LoginRequired(type = LoginType.Login)
    @RequestMapping(value = "menu.html", method = RequestMethod.GET)
    public String menu() {
        return "sys/menu";
    }




}