package com.jeasywebframework.service.dev;

import com.jeasywebframework.domain.dev.SqlLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public interface SqlLogService {


    Page<SqlLog> findAll(Pageable pageable);


    void save(SqlLog sqlLog);

}
