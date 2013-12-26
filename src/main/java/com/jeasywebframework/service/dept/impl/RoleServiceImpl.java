package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.RoleDao;
import com.jeasywebframework.dao.dept.RoleResourceDao;
import com.jeasywebframework.domain.dept.Role;
import com.jeasywebframework.domain.dept.RoleResource;
import com.jeasywebframework.service.dept.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleResourceDao roleResourceDao;


    @Override
    public Page<Role> findAll(Pageable pageable) {

        Long count = roleDao.countAll();
        List<Role> roles = Collections.emptyList();
        if (count.intValue() > 0) {
            roles = roleDao.findAll(pageable.getOffset(), pageable.getPageSize());
        }
        return new PageImpl<Role>(roles, pageable, count);
    }


    @Override
    public Role findOne(Long id) {
        return roleDao.findOne(id);
    }


    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Transactional
    @Override
    public void save(Role role, String resourceIds) {
        roleDao.save(role);


        Date now = new Date(System.currentTimeMillis());

        StringTokenizer stringTokenizer = new StringTokenizer(resourceIds, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String ID = stringTokenizer.nextToken();
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(role.getId());
            roleResource.setResourceId(Long.parseLong(ID));

            roleResource.setCreateTime(now);
            roleResource.setUpdateTime(now);
            roleResource.setCreateUserId(role.getUpdateUserId());
            roleResource.setUpdateUserId(role.getUpdateUserId());

            roleResourceDao.save(roleResource);
        }
    }

    @Transactional
    @Override
    public void update(Role role, String resourceIds) {
        roleResourceDao.deleteByRoleId(role.getId());


        Date now = new Date(System.currentTimeMillis());

        StringTokenizer stringTokenizer = new StringTokenizer(resourceIds, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String ID = stringTokenizer.nextToken();
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(role.getId());
            roleResource.setResourceId(Long.parseLong(ID));

            roleResource.setCreateTime(now);
            roleResource.setUpdateTime(now);
            roleResource.setCreateUserId(role.getUpdateUserId());
            roleResource.setUpdateUserId(role.getUpdateUserId());

            roleResourceDao.save(roleResource);
        }

    }

    @Override
    public List<RoleResource> findAllRoleResourceByRoleId(Long roleId) {
        return roleResourceDao.findByRoleId(roleId);
    }


}
