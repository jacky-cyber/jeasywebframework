package com.jeasywebframework.service.dev;

import com.jeasywebframework.domain.dev.SysDevSqlLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public interface SqlLogService {


    Page<SysDevSqlLog> findAll(Pageable pageable);


    void save(SysDevSqlLog sqlLog);

}
