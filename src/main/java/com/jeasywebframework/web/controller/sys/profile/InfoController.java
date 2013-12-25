package com.jeasywebframework.web.controller.sys.profile;

import com.jeasywebframework.domain.dept.LoginType;
import com.jeasywebframework.service.annotations.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Controller
@RequestMapping("/sys/profile/")
public class InfoController {

    @LoginRequired(type = LoginType.Login)
    @RequestMapping(value = "info.html", method = RequestMethod.GET)
    public String base() {
        return "sys/profile/info";
    }




}
