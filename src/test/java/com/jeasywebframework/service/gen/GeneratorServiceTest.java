package com.jeasywebframework.service.gen;

import com.jeasywebframework.domain.dev.SysGenTable;
import com.jeasywebframework.service.dev.GeneratorService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Ignore
@ContextConfiguration("classpath:applicationContext.xml")
public class GeneratorServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private GeneratorService generatorService;

    @Test
    public void test_notNull() {
        Assert.notNull(generatorService);
    }

    @Test
    public void test_findAll() {
        List<SysGenTable> tableList = generatorService.findAllTable();
        for (SysGenTable table : tableList) {
            System.out.println("==============>" + table.getName());
        }
    }

}
