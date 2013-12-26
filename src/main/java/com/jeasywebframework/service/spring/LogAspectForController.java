package com.jeasywebframework.service.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yfwangbing on 13-12-26.
 */
public class LogAspectForController {

    private static final Logger logger = LoggerFactory.getLogger(LogAspectForController.class);


    //    @Around("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..))")
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("==============>" + pjp.toLongString());

        try {
            Object retVal = pjp.proceed();
            return retVal;
        } finally {

        }

    }


}
