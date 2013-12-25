package com.jeasywebframework.dao.dept;

import com.jeasywebframework.domain.dept.SysDeptUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
public interface SysDeptUserDaoPlus {
    Page<SysDeptUser> findByKeywordAndDeptId(Pageable pageable, String keyword, Long deptId);
}
