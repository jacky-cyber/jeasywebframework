package com.jeasywebframework.web.interceptor;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.User;
import com.jeasywebframework.domain.dev.Tracker;
import com.jeasywebframework.service.dept.UserService;
import com.jeasywebframework.service.dev.TrackerHolder;
import com.jeasywebframework.utils.CookieUtil;
import com.jeasywebframework.utils.IpUtil;
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
        String url = request.getRequestURL().toString();

        if (url.indexOf(".ajax") > -1 || url.indexOf(".html") > -1) {
            Tracker inside = new Tracker();
            inside.setStartTime(System.currentTimeMillis());
            inside.setIp(IpUtil.getLocalIp());
            inside.setTag("Interceptor[" + HostHolderInterceptor.class.getName() + "]");
            inside.setThreadName(Thread.currentThread().getName());

            request.setAttribute("$inside_HostHolderInterceptor", inside);

            TrackerHolder.getInstance().setCurrent(inside);
            Tracker parent = TrackerHolder.getInstance().getRoot();
            parent.addChild(inside);


            String _username = CookieUtil.getCookie(request, HostHolder.COOKIE_KEY_USERNAME);
            String _pwd = CookieUtil.getCookie(request, HostHolder.COOKIE_KEY_PWD);

            HostHolder hostHolder = new HostHolder();

            if (StringUtils.isNotBlank(_username) && StringUtils.isNotBlank(_pwd)) {
                User user = userService.findByCookieUsernameAndPwd(_username, _pwd);

                if (user != null) {
                    hostHolder.setHost(user);
                }

            }



            request.setAttribute(HostHolder.REQUEST_KEY_HOLDER, hostHolder);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Tracker inside = (Tracker) request.getAttribute("$inside_HostHolderInterceptor");
        if (inside != null) {
            inside.setEndTime(System.currentTimeMillis());

            Tracker parent = TrackerHolder.getInstance().getRoot();
            TrackerHolder.getInstance().setCurrent(parent);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
