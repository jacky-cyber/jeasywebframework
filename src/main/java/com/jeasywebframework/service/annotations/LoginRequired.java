package com.jeasywebframework.service.annotations;

import com.jeasywebframework.domain.dept.LoginType;

import java.lang.annotation.*;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {

    public LoginType type() default LoginType.Login;

    public String descp() default "";

    public String name() default "";


}
