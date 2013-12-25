package com.jeasywebframework.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Controller
@RequestMapping("/")
public class IndexController {



    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);



    @RequestMapping(value = "index.html",method = RequestMethod.GET)
    public String index() {
        return "index";
    }




}
