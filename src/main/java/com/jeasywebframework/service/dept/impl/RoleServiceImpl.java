package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.SysDeptRoleDao;
import com.jeasywebframework.dao.dept.SysDeptRoleResourceDao;
import com.jeasywebframework.domain.dept.SysDeptRole;
import com.jeasywebframework.domain.dept.SysDeptRoleResource;
import com.jeasywebframework.service.dept.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysDeptRoleDao sysDeptRoleDao;

    @Autowired
    private SysDeptRoleResourceDao sysDeptRoleResourceDao;

    @Override
    public Page<SysDeptRole> findAll(Pageable pageable) {
        return sysDeptRoleDao.findAll(pageable);
    }


    @Override
    public SysDeptRole findOne(Long id) {
        return sysDeptRoleDao.findOne(id);
    }


    @Override
    public void delete(Long id) {
        sysDeptRoleDao.delete(id);
    }

    @Transactional
    @Override
    public void save(SysDeptRole sysDeptRole, String resourceIds) {
        sysDeptRoleDao.save(sysDeptRole);


        Date now = new Date(System.currentTimeMillis());

        StringTokenizer stringTokenizer = new StringTokenizer(resourceIds, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String ID = stringTokenizer.nextToken();
            SysDeptRoleResource sysDeptRoleResource = new SysDeptRoleResource();
            sysDeptRoleResource.setRoleId(sysDeptRole.getId());
            sysDeptRoleResource.setResourceId(Long.parseLong(ID));

            sysDeptRoleResource.setCreateTime(now);
            sysDeptRoleResource.setUpdateTime(now);
            sysDeptRoleResource.setCreateUserId(sysDeptRole.getUpdateUserId());
            sysDeptRoleResource.setUpdateUserId(sysDeptRole.getUpdateUserId());

            sysDeptRoleResourceDao.save(sysDeptRoleResource);
        }
    }

    @Transactional
    @Override
    public void update(SysDeptRole sysDeptRole, String resourceIds) {
        List<SysDeptRoleResource> sysDeptRoleResources = sysDeptRoleResourceDao.findByRoleId(sysDeptRole.getId());
        sysDeptRoleResourceDao.delete(sysDeptRoleResources);

        Date now = new Date(System.currentTimeMillis());

        StringTokenizer stringTokenizer = new StringTokenizer(resourceIds, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String ID = stringTokenizer.nextToken();
            SysDeptRoleResource sysDeptRoleResource = new SysDeptRoleResource();
            sysDeptRoleResource.setRoleId(sysDeptRole.getId());
            sysDeptRoleResource.setResourceId(Long.parseLong(ID));

            sysDeptRoleResource.setCreateTime(now);
            sysDeptRoleResource.setUpdateTime(now);
            sysDeptRoleResource.setCreateUserId(sysDeptRole.getUpdateUserId());
            sysDeptRoleResource.setUpdateUserId(sysDeptRole.getUpdateUserId());

            sysDeptRoleResourceDao.save(sysDeptRoleResource);
        }

    }

    @Override
    public List<SysDeptRoleResource> findAllRoleResourceByRoleId(Long roleId) {
        return sysDeptRoleResourceDao.findByRoleId(roleId);
    }


}
