package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.Department;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
public interface DepartmentService {
    String CACHE_VALUE = "sys_dept_department";


    Department findOne(Long id);

    void saveAndFlush(Department department);


    void save(Department department);


    Long countByParentId(Long parentId);


    List<Department> findAll();


    Department findByCode(String code);
}
