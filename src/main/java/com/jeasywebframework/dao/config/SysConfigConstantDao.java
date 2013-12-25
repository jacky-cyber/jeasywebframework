package com.jeasywebframework.dao.config;

import com.jeasywebframework.domain.config.SysConfigConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Repository
public interface SysConfigConstantDao extends JpaRepository<SysConfigConstant, Long> {
}
