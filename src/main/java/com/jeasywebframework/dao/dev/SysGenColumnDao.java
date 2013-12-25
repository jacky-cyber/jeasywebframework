package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.dev.SysGenColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Repository
public interface SysGenColumnDao extends JpaRepository<SysGenColumn, Long> {

    List<SysGenColumn> findByTableName(String tableName);
}
