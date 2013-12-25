package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.dev.SysGenTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Repository
public interface SysGenTableDao extends JpaRepository<SysGenTable,Long>{
    SysGenTable findByName(String name);

}
