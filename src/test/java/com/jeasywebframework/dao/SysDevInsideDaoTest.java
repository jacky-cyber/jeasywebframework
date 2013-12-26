package com.jeasywebframework.dao;

import com.jeasywebframework.dao.dev.SysDevInsideDao;
import com.jeasywebframework.domain.dev.Tracker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
//@Ignore
@ContextConfiguration("classpath:applicationContext.xml")
public class SysDevInsideDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private SysDevInsideDao sysDevInsideDao;


    @Test
    public void test_findAll() {
        List<Tracker> list = sysDevInsideDao.findByPathLike("/1%", new Sort(Sort.Direction.DESC, "path"));
        Assert.isTrue(list.size()==2);
    }


}
