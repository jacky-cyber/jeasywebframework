package com.jeasywebframework.dao;

import com.jeasywebframework.dao.dept.UserDao;
import com.jeasywebframework.domain.dept.User;
import com.jeasywebframework.utils.MD5Util;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Ignore
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;

    @Test
    public void testNotNull() {
        Assert.notNull(userDao);
    }

    @Test
    public void add_user() {
        User user = new User();

        user.setUsername("admin");

        String salt = RandomStringUtils.randomNumeric(8);
        String pwd = MD5Util.getMD5Str("admin") + salt;
        String password = MD5Util.getMD5Str(pwd);

        user.setPassword(password);
        user.setSalt(salt);

        Date now = new Date(System.currentTimeMillis());
        user.setCreateTime(now);
        user.setCreateUserId(0L);
        user.setUpdateTime(now);
        user.setUpdateUserId(0L);
        user.setMobile("159****");

        user.setEnabled("Y");
        user.setName("admin");
        user.setBirthday(now);
        user.setDepartmentId(0L);
        user.setEmail("wangchangbing@gmail.com");
        user.setOorder(100L);
        user.setSex("男");

        userDao.save(user);
    }


}
