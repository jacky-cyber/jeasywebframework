package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.SysDeptDepartment;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
public interface DepartmentService {
    String CACHE_VALUE = "sys_dept_department";


    SysDeptDepartment findOne(Long id);

    void saveAndFlush(SysDeptDepartment sysDeptDepartment);


    void save(SysDeptDepartment sysDeptDepartment);


    Long countByParentId(Long parentId);


    List<SysDeptDepartment> findAll(Sort sort);


    SysDeptDepartment findByCode(String code);
}
