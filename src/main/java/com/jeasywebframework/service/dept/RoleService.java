package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.SysDeptRole;
import com.jeasywebframework.domain.dept.SysDeptRoleResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
public interface RoleService {


    Page<SysDeptRole> findAll(Pageable pageable);


    SysDeptRole findOne(Long id);


    void delete(Long id);


    void save(SysDeptRole sysDeptRole, String resourceIds);


    void update(SysDeptRole sysDeptRole, String resourceIds);


    List<SysDeptRoleResource> findAllRoleResourceByRoleId(Long roleId);
}
