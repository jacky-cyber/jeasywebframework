package com.jeasywebframework.service.dev.impl;

import com.jeasywebframework.dao.dev.SysDevSqlLogDao;
import com.jeasywebframework.domain.dev.SysDevSqlLog;
import com.jeasywebframework.service.dev.SqlLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Service
public class SqlLogServiceImpl implements SqlLogService {

    @Autowired
    private SysDevSqlLogDao sysDevSqlLogDao;

    @Override
    public Page<SysDevSqlLog> findAll(Pageable pageable) {
        return sysDevSqlLogDao.findAll(pageable);
    }

    @Override
    public void save(SysDevSqlLog sqlLog) {
        sysDevSqlLogDao.save(sqlLog);
    }


}
