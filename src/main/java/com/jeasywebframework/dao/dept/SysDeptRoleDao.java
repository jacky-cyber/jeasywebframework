package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.SysDeptRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
@Repository
public interface SysDeptRoleDao extends JpaRepository<SysDeptRole, Long> {
}
