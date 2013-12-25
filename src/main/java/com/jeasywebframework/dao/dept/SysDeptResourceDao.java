package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.SysDeptResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
@Repository
public interface SysDeptResourceDao extends JpaRepository<SysDeptResource, Long> {

    @Query(value = " select count(id) from sys_dept_resource where parent_id = ?1", nativeQuery = true)
    Long countByParentId(Long parentId);

}
