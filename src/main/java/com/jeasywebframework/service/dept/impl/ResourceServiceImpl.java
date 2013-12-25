package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.SysDeptResourceDao;
import com.jeasywebframework.domain.dept.SysDeptResource;
import com.jeasywebframework.service.dept.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SysDeptResourceDao sysDeptResourceDao;

    @Cacheable(value = ResourceService.CACHE_VALUE)
    @Override
    public List<SysDeptResource> findAll(Sort sort) {
        return sysDeptResourceDao.findAll(sort);
    }


    @Override
    public SysDeptResource findOne(Long id) {
        return sysDeptResourceDao.findOne(id);
    }

    @Override
    public Long countByParentId(Long parentId) {
        return sysDeptResourceDao.countByParentId(parentId);
    }

    @CacheEvict(value = ResourceService.CACHE_VALUE)
    @Override
    public void saveAndFlush(SysDeptResource sysDeptResource) {
        sysDeptResourceDao.saveAndFlush(sysDeptResource);
    }


    @CacheEvict(value = ResourceService.CACHE_VALUE)
    @Override
    public void save(SysDeptResource sysDeptResource) {
        sysDeptResourceDao.save(sysDeptResource);
    }

    @CacheEvict(value = ResourceService.CACHE_VALUE)
    @Override
    public void batchSave(List<SysDeptResource> resources) {
        sysDeptResourceDao.save(resources);
    }


}
