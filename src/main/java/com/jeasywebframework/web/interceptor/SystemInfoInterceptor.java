package com.jeasywebframework.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
public class SystemInfoInterceptor implements HandlerInterceptor {



    private static final Logger logger = LoggerFactory.getLogger(SystemInfoInterceptor.class);




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if(url.indexOf("/statics/")>-1){
            return true;
        }

        request.setAttribute("staticVersion", String.valueOf(System.currentTimeMillis()));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
