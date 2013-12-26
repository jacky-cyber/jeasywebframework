package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.Role;
import com.jeasywebframework.domain.dept.RoleResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
public interface RoleService {

    String CACHE_VALUE = "sys_dept_role";


    Page<Role> findAll(Pageable pageable);


    Role findOne(Long id);


    void delete(Long id);


    void save(Role role, String resourceIds);


    void update(Role role, String resourceIds);


    List<RoleResource> findAllRoleResourceByRoleId(Long roleId);
}
