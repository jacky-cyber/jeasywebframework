package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.dev.SysDevSqlLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by  jeasywebframework@gmail.com on 13-12-25.
 */
@Repository
public interface SysDevSqlLogDao extends JpaRepository<SysDevSqlLog, Long> {
}
