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
            Tracker tracker = new Tracker();
            tracker.setStartTime(System.currentTimeMillis());
            tracker.setIp(IpUtil.getLocalIp());
            tracker.setTag("Interceptor[" + HostHolderInterceptor.class.getName() + "]");
            tracker.setThreadName(Thread.currentThread().getName());


            TrackerHolder.getInstance().setCurrent(tracker);
            Tracker parent = TrackerHolder.getInstance().getRoot();
            parent.addChild(tracker);


            String _username = CookieUtil.getCookie(request, HostHolder.COOKIE_KEY_USERNAME);
            String _pwd = CookieUtil.getCookie(request, HostHolder.COOKIE_KEY_PWD);

            HostHolder hostHolder = new HostHolder();

            if (StringUtils.isNotBlank(_username) && StringUtils.isNotBlank(_pwd)) {
                User user = userService.findByCookieUsernameAndPwd(_username, _pwd);

                if (user != null) {
                    hostHolder.setHost(user);
                }

            }


            if (tracker != null) {
                tracker.setEndTime(System.currentTimeMillis());
                TrackerHolder.getInstance().setCurrent(parent);
            }

            request.setAttribute(HostHolder.REQUEST_KEY_HOLDER, hostHolder);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
