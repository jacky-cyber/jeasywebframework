package com.jeasywebframework.web.resolver;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.utils.AjaxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("Catch exception : " + ex.getMessage(), ex);


        String url = request.getRequestURI().toString();
        if (url.indexOf(".ajax") > -1) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            try {
                PrintWriter printWriter = response.getWriter();
                printWriter.print(AjaxUtil.failure(ex.getMessage()));
                printWriter.flush();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exception", ex);
        map.put("url", url);
        map.put("host", request.getAttribute(HostHolder.REQUEST_KEY_HOLDER));

        return new ModelAndView("sys/error", map);
    }


}
