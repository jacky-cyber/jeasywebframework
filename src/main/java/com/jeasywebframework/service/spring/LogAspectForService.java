package com.jeasywebframework.service.spring;

import com.jeasywebframework.domain.dev.Tracker;
import com.jeasywebframework.service.dev.TrackerHolder;
import com.jeasywebframework.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by jeasywebframework@gmail.com on 13-12-4.
 */
public class LogAspectForService {
    private Logger logger = LoggerFactory.getLogger(LogAspectForService.class);


    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info("Beginning method: " + joinPoint.toLongString());

        Tracker root = TrackerHolder.getInstance().getRoot();
        Tracker insideParent = TrackerHolder.getInstance().getCurrent();
        Tracker tracker = null;

        if (root != null && insideParent != null) {
            tracker = new Tracker();
            tracker.setThreadName(Thread.currentThread().getName());
            String tag = joinPoint.toShortString();
//execution(DepartmentServiceImpl.findAll())
            int i1 = tag.indexOf("(");
            String s1 = tag.substring(i1 + 1, tag.length() - 3);

            tracker.setTag(s1);
            tracker.setDescp(joinPoint.toLongString());
            tracker.setIp(IpUtil.getLocalIp());
            tracker.setStartTime(System.currentTimeMillis());

//            logger.debug("$$$$$$$$$$$$$$add Tracker: " + tracker.getTag());

            TrackerHolder.getInstance().setCurrent(tracker);
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            if (root != null && insideParent != null) {
                tracker.setEndTime(System.currentTimeMillis());
                insideParent.addChild(tracker);
                TrackerHolder.getInstance().setCurrent(insideParent);
            }

        }
        return result;
    }

}
