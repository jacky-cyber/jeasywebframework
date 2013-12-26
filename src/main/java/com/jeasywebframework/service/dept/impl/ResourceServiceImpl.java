package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.ResourceDao;
import com.jeasywebframework.domain.dept.Resource;
import com.jeasywebframework.service.dept.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;


    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }


    @Override
    public Resource findOne(Long id) {
        return resourceDao.findOne(id);
    }

    @Override
    public Long countByParentId(Long parentId) {
        return resourceDao.countByParentId(parentId);
    }

    @Override
    public void saveAndFlush(Resource resource) {
        resourceDao.update(resource);
    }


    @Override
    public void save(Resource resource) {
        resourceDao.save(resource);
    }

    @Transactional
    @Override
    public void batchSave(List<Resource> resources) {
        for (Resource resource : resources) {
            resourceDao.save(resource);
        }
    }


}
