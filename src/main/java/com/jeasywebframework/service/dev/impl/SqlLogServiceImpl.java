package com.jeasywebframework.service.dev.impl;

import com.jeasywebframework.dao.dev.SqlLogDao;
import com.jeasywebframework.domain.dev.SqlLog;
import com.jeasywebframework.service.dev.SqlLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Service
public class SqlLogServiceImpl implements SqlLogService {

    @Autowired
    private SqlLogDao sqlLogDao;

    @Override
    public Page<SqlLog> findAll(Pageable pageable) {
        Long count = sqlLogDao.countAll();
        List<SqlLog> sqlLogList = Collections.emptyList();
        if (count.intValue() > 0) {
            sqlLogDao.findAll(pageable.getOffset(), pageable.getPageSize());
        }

        return new PageImpl<SqlLog>(sqlLogList, pageable, count);
    }

    @Override
    public void save(SqlLog sqlLog) {
        sqlLogDao.save(sqlLog);
    }


}
