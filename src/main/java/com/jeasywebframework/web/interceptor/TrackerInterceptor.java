package com.jeasywebframework.web.interceptor;

import com.jeasywebframework.dao.dev.TrackerDao;
import com.jeasywebframework.domain.dev.Tracker;
import com.jeasywebframework.service.dev.TrackerHolder;
import com.jeasywebframework.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
public class TrackerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TrackerInterceptor.class);


    @Autowired
    private TrackerDao trackerDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURL().toString();
        if (url.indexOf(".ajax") > -1 || url.indexOf(".html") > -1) {
            // TODO
            Tracker parent = new Tracker();
            parent.setStartTime(System.currentTimeMillis());
            parent.setIp(IpUtil.getLocalIp());
            parent.setTag("URL[" + url + "]");
            parent.setThreadName(Thread.currentThread().getName());


            httpServletRequest.setAttribute("$inside_InsideInterceptor", parent);
            TrackerHolder.getInstance().setCurrent(parent);
            TrackerHolder.getInstance().setRoot(parent);
        }

        if (url.indexOf(".html") > -1) {
            httpServletRequest.setAttribute("staticVersion", String.valueOf(System.currentTimeMillis()));
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        Tracker parent = (Tracker) httpServletRequest.getAttribute("$inside_InsideInterceptor");

        if (parent != null) {
            parent.setEndTime(System.currentTimeMillis());

            trackerDao.save(parent);
            parent.setPath("/" + parent.getId());
            parent.setChildrenNum(Long.valueOf(parent.getChildren().size()));
            parent.setLevel(1);
            parent.setParentId(0L);
            trackerDao.update(parent);

            saveTracker(parent);
        }

        TrackerHolder.getInstance().removeRoot();
        TrackerHolder.getInstance().removeCurrent();
    }

    private void saveTracker(Tracker parent) {

        List<Tracker> children = parent.getChildren();
        for (Tracker inside1 : children) {
            trackerDao.save(inside1);

            inside1.setParentId(parent.getId());
            inside1.setPath(parent.getPath() + "/" + inside1.getId());
            inside1.setChildrenNum(Long.valueOf(inside1.getChildren().size()));
            inside1.setLevel(parent.getLevel() + 1);

            trackerDao.update(inside1);

            saveTracker(inside1);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {


    }
}
