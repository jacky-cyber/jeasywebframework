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
        logger.info("Beginning method: " + joinPoint.toLongString());

        Tracker insideParent = TrackerHolder.getInstance().getCurrent();
        Tracker tracker = null;

        if (insideParent != null) {
            tracker = new Tracker();
            tracker.setThreadName(Thread.currentThread().getName());
            tracker.setTag(joinPoint.toShortString());
            tracker.setDescp(joinPoint.toLongString());
            tracker.setIp(IpUtil.getLocalIp());
            tracker.setStartTime(System.currentTimeMillis());
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            if (insideParent != null) {
                tracker.setEndTime(System.currentTimeMillis());
                insideParent.addChild(tracker);
                TrackerHolder.getInstance().setCurrent(insideParent);
            }

        }
        return result;
    }

}
