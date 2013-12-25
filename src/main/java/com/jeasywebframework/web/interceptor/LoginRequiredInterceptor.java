package com.jeasywebframework.web.interceptor;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.LoginType;
import com.jeasywebframework.service.dept.UserService;
import com.jeasywebframework.service.annotations.LoginRequired;
import com.jeasywebframework.utils.AjaxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public class LoginRequiredInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginRequiredInterceptor.class);

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();

        if (url.indexOf("/statics/") > -1) {
            return true;
        }

        HostHolder hostHolder = (HostHolder) request.getAttribute(HostHolder.REQUEST_KEY_HOLDER);
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);

            LoginType loginType = LoginType.Login;
            if (loginRequired != null) {
                loginType = loginRequired.type();
            }

            logger.debug("Url [" + url + "] login type is [" + loginType + "].");

            if (loginType == LoginType.Public) {
                return true;
            }

            if (loginType == LoginType.Dev) {
                // TODO
                if (hostHolder.isLogin()) {
                    return true;
                } else {
                    return goNeedDev(request, response, url);
                }
            }


            if (loginType == LoginType.Login) {
                if (hostHolder.isLogin()) {
                    return true;
                } else {
                    return goNeedLogin(request, response, url);
                }
            }

            if (loginType == LoginType.Accredit) {
                if (!hostHolder.isLogin()) {
                    return goNeedLogin(request, response, url);
                }

                Long hostId = hostHolder.getHostId();
                boolean hasAccess = userService.hasAccess(hostId, url);
                if (hasAccess) {
                    return true;
                } else {
                    return goNeedAuth(request, response, url);
                }
            }
        }

        return true;
    }


    private boolean goNeedAuth(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (url.indexOf(".ajax") > -1) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(AjaxUtil.failure("您需要授权才能使用该功能，请联系管理员授权！"));
            printWriter.flush();
            printWriter.close();
        } else {
            String authUrl = "/sys/need-auth.html?returnUrl=" + HtmlUtils.htmlEscape(url);
            response.sendRedirect(authUrl);
        }
        return false;
    }

    private boolean goNeedLogin(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (url.indexOf(".ajax") > -1) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(AjaxUtil.failure("您需要登录才能使用该功能，请联系管理员授权！"));
            printWriter.flush();
            printWriter.close();
        } else {
            String authUrl = "/sys/login.html?returnUrl=" + HtmlUtils.htmlEscape(url);
            response.sendRedirect(authUrl);
        }
        return false;
    }

    private boolean goNeedDev(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (url.indexOf(".ajax") > -1) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(AjaxUtil.failure("该功能只有在DEV模式下才能使用，请联系管理员授权！"));
            printWriter.flush();
            printWriter.close();
        } else {
            String authUrl = "/sys/need-auth.html?returnUrl=" + HtmlUtils.htmlEscape(url);
            response.sendRedirect(authUrl);
        }
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
