package com.jeasywebframework.dao.dev;


import com.jeasywebframework.domain.dev.SqlLog;
import org.apache.ibatis.annotations.Param;

/**
 * Created by  jeasywebframework@gmail.com on 13-12-25.
 */
public interface SqlLogDao {
    Long countAll();

    void findAll(@Param("offset") int offset, @Param("limit") int limit);

    void save(SqlLog sqlLog);

}
