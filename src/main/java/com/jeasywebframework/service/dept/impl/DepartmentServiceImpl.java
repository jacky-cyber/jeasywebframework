package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.SysDeptDepartmentDao;
import com.jeasywebframework.domain.dept.SysDeptDepartment;
import com.jeasywebframework.service.dept.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private SysDeptDepartmentDao sysDeptDepartmentDao;

    @Override
    public SysDeptDepartment findOne(Long id) {
        return sysDeptDepartmentDao.findOne(id);
    }

    @Override
    public void saveAndFlush(SysDeptDepartment sysDeptDepartment) {
        sysDeptDepartmentDao.saveAndFlush(sysDeptDepartment);
    }

    @Override
    public List<SysDeptDepartment> findAll() {
        return sysDeptDepartmentDao.findAll();
    }

    @Override
    public void save(SysDeptDepartment sysDeptDepartment) {
        sysDeptDepartmentDao.saveAndFlush(sysDeptDepartment);
    }

    @Override
    public Long countByParentId(Long parentId) {
        return sysDeptDepartmentDao.countByParentId(parentId);
    }

    @Override
    public List<SysDeptDepartment> findAll(Sort sort) {
        return sysDeptDepartmentDao.findAll(sort);
    }


}
