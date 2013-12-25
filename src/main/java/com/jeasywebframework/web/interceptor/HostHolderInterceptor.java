package com.jeasywebframework.web.interceptor;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.SysDeptUser;
import com.jeasywebframework.service.dept.UserService;
import com.jeasywebframework.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 * 解析cookie中的host
 */

public class HostHolderInterceptor implements HandlerInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(HostHolderInterceptor.class);


    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String _username = CookieUtil.getCookie(request, HostHolder.COOKIE_KEY_USERNAME);
        String _pwd = CookieUtil.getCookie(request, HostHolder.COOKIE_KEY_PWD);

//        logger.debug("cookie username========>" + _username);
//        logger.debug("cookie password========>" + _pwd);
        HostHolder hostHolder = new HostHolder();


        if (StringUtils.isNotBlank(_username) && StringUtils.isNotBlank(_pwd)) {
            SysDeptUser sysDeptUser = userService.findByCookieUsernameAndPwd(_username, _pwd);

            if (sysDeptUser != null) {
                hostHolder.setHost(sysDeptUser);
            }

        }

        request.setAttribute(HostHolder.REQUEST_KEY_HOLDER, hostHolder);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
