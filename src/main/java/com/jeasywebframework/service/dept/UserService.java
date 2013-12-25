package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.SysDeptUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public interface UserService {

    String CACHE_VALUE = "sys_dept_user";

    boolean hasAccess(Long hostId, String url);

    SysDeptUser findByCookieUsernameAndPwd(String id, String pwd);

    SysDeptUser loginCheck(String username, String password);

    Page<SysDeptUser> findByKeywordAndDeptId(Pageable pageable, String keyword, Long deptId);

    void delete(long id);

    void save(SysDeptUser sysDeptUser);

    SysDeptUser findOne(Long id);

    void saveAndFlush(SysDeptUser sysDeptUser);

}
