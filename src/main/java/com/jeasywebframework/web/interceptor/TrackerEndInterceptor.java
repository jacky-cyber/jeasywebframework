package com.jeasywebframework.web.interceptor;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dev.Tracker;
import com.jeasywebframework.service.dev.TrackerHolder;
import com.jeasywebframework.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
public class TrackerEndInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TrackerEndInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if (url.indexOf(".ajax") > -1 || url.indexOf(".html") > -1) {

            HostHolder hostHolder = (HostHolder) request.getAttribute(HostHolder.REQUEST_KEY_HOLDER);
            if (handler instanceof HandlerMethod) {

                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();

                Tracker inside = new Tracker();
                inside.setStartTime(System.currentTimeMillis());
                inside.setIp(IpUtil.getLocalIp());
                inside.setTag("Controller[" + handlerMethod.getBeanType().getName() + "#" + method.getName() + "]");
                inside.setThreadName(Thread.currentThread().getName());

                TrackerHolder.getInstance().setCurrent(inside);

                Tracker parent = TrackerHolder.getInstance().getRoot();
                parent.addChild(inside);
                request.setAttribute("$inside_TrackerEndInterceptor", inside);

//                logger.debug("$$$$$$$$$$$$$$add Tracker: " + inside.getTag());
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Tracker inside = (Tracker) request.getAttribute("$inside_TrackerEndInterceptor");
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
