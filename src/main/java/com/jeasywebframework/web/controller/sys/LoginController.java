package com.jeasywebframework.web.controller.sys;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.LoginType;
import com.jeasywebframework.domain.dept.SysDeptUser;
import com.jeasywebframework.service.annotations.LoginRequired;
import com.jeasywebframework.service.dept.UserService;
import com.jeasywebframework.utils.AjaxUtil;
import com.jeasywebframework.utils.CookieUtil;
import com.jeasywebframework.utils.DESUtil;
import com.jeasywebframework.utils.MD5Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
@Controller
@RequestMapping("/sys/")
public class LoginController {


    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private UserService userService;


    @LoginRequired(type = LoginType.Public)
    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String login(String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);

        return "sys/dept/login";
    }


    @LoginRequired(type = LoginType.Public)
    @RequestMapping(value = "login.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject loginCheck(
            HttpServletResponse response,//
            String returnUrl,
            String username, String password, @RequestParam(value = "remember", defaultValue = "0") int remember) {

        if (StringUtils.isEmpty(username)) {
            return AjaxUtil.failure("用户名不能为空值，请重新输入！");
        }

        if (StringUtils.isEmpty(password)) {
            return AjaxUtil.failure("密码不能为空值，请重新输入！");
        }


        SysDeptUser sysDeptUser = userService.loginCheck(username, password);
        if (sysDeptUser == null) {
            return AjaxUtil.failure("用户名或者密码错误，请重新输入!");
        }
        String domain = "jeasywebframework.com";


        try {
            String salt = sysDeptUser.getSalt();
            String pwd = sysDeptUser.getPassword();
            String CP = MD5Util.getMD5Str(pwd) + salt;
            String CP1 = MD5Util.getMD5Str(CP);


            CookieUtil.setCookie(response, domain, HostHolder.COOKIE_KEY_USERNAME, DESUtil.encrypt(sysDeptUser.getUsername()), 30 * 60 * 60);
            CookieUtil.setCookie(response, domain, HostHolder.COOKIE_KEY_PWD, DESUtil.encrypt(CP1), 30 * 60 * 60);
        } catch (Exception e) {
            logger.error("setRoot cookie error.", e);
            e.printStackTrace();
        }

        return AjaxUtil.success();
    }

    @LoginRequired(type = LoginType.Public)
    @RequestMapping(value = "logout.html", method = RequestMethod.GET)
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        try {
            CookieUtil.removeCookie(request, response, HostHolder.COOKIE_KEY_USERNAME);
            CookieUtil.removeCookie(request, response, HostHolder.COOKIE_KEY_PWD);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("removeRoot cookie error.", ex);
        }
        return "sys/dept/login";
    }


    @LoginRequired(type = LoginType.Public)
    @RequestMapping(value = "forget-pwd.html", method = RequestMethod.GET)
    public String forgetPwd(String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);

        return "sys/dept/forget-pwd";
    }


    @LoginRequired(type = LoginType.Public)
    @RequestMapping(value = "register.html", method = RequestMethod.GET)
    public String register(String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);

        return "sys/dept/register";
    }


}
