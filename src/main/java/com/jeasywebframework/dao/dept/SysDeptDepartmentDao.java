package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.SysDeptDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Repository
public interface SysDeptDepartmentDao extends JpaRepository<SysDeptDepartment, Long> {

    @Query(value = " select count(id) from sys_dept_department where parent_id = ?1", nativeQuery = true)
    Long countByParentId(Long parentId);



}
