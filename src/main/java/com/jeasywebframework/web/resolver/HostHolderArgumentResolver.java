package com.jeasywebframework.web.resolver;

import com.jeasywebframework.domain.dept.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public class HostHolderArgumentResolver implements HandlerMethodArgumentResolver {



    private static final Logger logger = LoggerFactory.getLogger(HostHolderArgumentResolver.class);



    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().equals(HostHolder.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return (HostHolder) request.getAttribute(HostHolder.REQUEST_KEY_HOLDER);
    }


}
