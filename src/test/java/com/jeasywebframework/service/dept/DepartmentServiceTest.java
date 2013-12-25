package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.SysDeptUser;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DepartmentService departmentService;

    public void test_notNull() {
        Assert.notNull(departmentService);
    }

    @Test
    public void test_findAll() {
        departmentService.findAll(new Sort(Sort.Direction.ASC, "path"));

        departmentService.findAll(new Sort(Sort.Direction.ASC, "path"));


    }


}

