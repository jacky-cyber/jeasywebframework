package com.jeasywebframework.web.controller.sys.config;

import com.jeasywebframework.service.config.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Controller
@RequestMapping("/sys/config/dict/")
public class DictController {


    @Autowired
    private DictService dictService;

    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/config/dict/list";
    }



}
