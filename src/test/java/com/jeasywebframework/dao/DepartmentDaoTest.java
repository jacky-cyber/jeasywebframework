package com.jeasywebframework.dao;

import com.jeasywebframework.dao.dept.DepartmentDao;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
//@Ignore
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void test_notNull() {
        Assert.notNull(departmentDao);
    }

    @Test
    public void test_countByParentId() {
        int o = 0;
        departmentDao.countByParentId(0L);
    }


    @Test
    public void test_findAll() {
        int o = 0;
        departmentDao.findAll();
    }


}
