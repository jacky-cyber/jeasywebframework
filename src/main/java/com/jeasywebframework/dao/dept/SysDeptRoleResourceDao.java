package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.SysDeptRoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Repository
public interface SysDeptRoleResourceDao extends JpaRepository<SysDeptRoleResource, Long> {


    List<SysDeptRoleResource> findByRoleId(Long roleId);
}
