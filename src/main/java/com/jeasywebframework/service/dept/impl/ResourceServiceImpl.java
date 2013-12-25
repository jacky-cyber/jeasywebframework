package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.SysDeptResourceDao;
import com.jeasywebframework.domain.dept.SysDeptResource;
import com.jeasywebframework.service.dept.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<SysDeptResource> findAll(Sort sort) {
        return sysDeptResourceDao.findAll(sort);
    }

    @Override
    public List<SysDeptResource> findAll() {
        return sysDeptResourceDao.findAll();
    }

    @Override
    public SysDeptResource findOne(Long id) {
        return sysDeptResourceDao.findOne(id);
    }

    @Override
    public Long countByParentId(Long parentId) {
        return sysDeptResourceDao.countByParentId(parentId);
    }

    @Override
    public void saveAndFlush(SysDeptResource sysDeptResource) {
        sysDeptResourceDao.saveAndFlush(sysDeptResource);
    }

    @Override
    public void save(SysDeptResource sysDeptResource) {
        sysDeptResourceDao.save(sysDeptResource);
    }

    @Override
    public void batchSave(List<SysDeptResource> resources) {

    }


}
