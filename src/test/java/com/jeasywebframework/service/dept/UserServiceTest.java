package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@Ignore
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void test_loginCheck() {
        User user = userService.loginCheck("admin", "admin");
        Assert.notNull(user);
    }


}

