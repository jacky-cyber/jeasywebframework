package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.DepartmentDao;
import com.jeasywebframework.domain.dept.Department;
import com.jeasywebframework.service.dept.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;


    @Override
    public Department findOne(Long id) {
        return departmentDao.findOne(id);
    }

    @Override
    public void saveAndFlush(Department department) {
        departmentDao.update(department);

    }


    @Override
    public void save(Department department) {
        departmentDao.save(department);

    }

    @Override
    public Long countByParentId(Long parentId) {
        return departmentDao.countByParentId(parentId);
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = departmentDao.findAll();
        return departments;
    }

    @Override
    public Department findByCode(String code) {
        return departmentDao.findByCode(code);
    }


}
