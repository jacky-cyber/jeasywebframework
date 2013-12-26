package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public interface UserService {

    String CACHE_VALUE = "sys_dept_user";

    boolean hasAccess(Long hostId, String url);

    User findByCookieUsernameAndPwd(String id, String pwd);

    User loginCheck(String username, String password);

    Page<User> findByKeywordAndDeptId(Pageable pageable, String keyword, Long deptId);

    void delete(long id);

    void save(User user);

    User findOne(Long id);

    void saveAndFlush(User user);

}
