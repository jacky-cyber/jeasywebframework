package com.jeasywebframework.dao;

import com.jeasywebframework.dao.dept.SysDeptDepartmentDao;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
//@Ignore
@ContextConfiguration("classpath:applicationContext.xml")
public class SysDeptDepartmentDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SysDeptDepartmentDao sysDeptDepartmentDao;

    @Test
    public void test_notNull() {
        Assert.notNull(sysDeptDepartmentDao);
    }

    @Test
    public void test_countByParentId() {
        int o = 0;

        sysDeptDepartmentDao.countByParentId(0L);
    }


    @Test
    public void test_findAll() {
        int o = 0;
        sysDeptDepartmentDao.findAll(new Sort(Sort.Direction.ASC, "path"));
    }


}
