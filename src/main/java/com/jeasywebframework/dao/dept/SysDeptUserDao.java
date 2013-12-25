package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.SysDeptUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Repository
public interface SysDeptUserDao extends JpaRepository<SysDeptUser, Long>, SysDeptUserDaoPlus {


    SysDeptUser findByUsername(String username);
}
