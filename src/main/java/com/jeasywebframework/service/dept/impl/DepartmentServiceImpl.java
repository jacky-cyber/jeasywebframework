package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.SysDeptDepartmentDao;
import com.jeasywebframework.domain.dept.SysDeptDepartment;
import com.jeasywebframework.service.dept.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private CacheManager cacheManager;


    @Override
    public SysDeptDepartment findOne(Long id) {
        return sysDeptDepartmentDao.findOne(id);
    }

    @Override
    public void saveAndFlush(SysDeptDepartment sysDeptDepartment) {
        sysDeptDepartmentDao.saveAndFlush(sysDeptDepartment);
        Cache cache = cacheManager.getCache(CACHE_VALUE);
        cache.clear();
    }


    @Override
    public void save(SysDeptDepartment sysDeptDepartment) {
        sysDeptDepartmentDao.saveAndFlush(sysDeptDepartment);
        Cache cache = cacheManager.getCache(CACHE_VALUE);
        cache.clear();
    }

    @Override
    public Long countByParentId(Long parentId) {
        return sysDeptDepartmentDao.countByParentId(parentId);
    }

    @Override
    public List<SysDeptDepartment> findAll(Sort sort) {
        Cache cache = cacheManager.getCache(CACHE_VALUE);
        List<SysDeptDepartment> departments = cache.get("findAll", List.class);
        if (departments == null) {
            departments = sysDeptDepartmentDao.findAll(sort);
            cache.put("findAll", departments);
        }
        return departments;
    }

    @Override
    public SysDeptDepartment findByCode(String code) {
        return sysDeptDepartmentDao.findByCode(code);
    }


}
