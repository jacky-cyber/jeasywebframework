package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.SysDeptUserDao;
import com.jeasywebframework.domain.dept.SysDeptUser;
import com.jeasywebframework.service.dept.UserService;
import com.jeasywebframework.utils.DESUtil;
import com.jeasywebframework.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private SysDeptUserDao sysDeptUserDao;

    @Override
    public boolean hasAccess(Long hostId, String url) {
        return true;
    }

    @Override
    public SysDeptUser findByCookieUsernameAndPwd(String username, String pwd) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)) {
            return null;
        }

        try {
            String _username = DESUtil.decrypt(username);
            String _pwd = DESUtil.decrypt(pwd);

            SysDeptUser sysDeptUser = sysDeptUserDao.findByUsername(_username);
            if (sysDeptUser == null) {
                return null;
            }

            String salt = sysDeptUser.getSalt();
            String CP = MD5Util.getMD5Str(sysDeptUser.getPassword()) + salt;
            String CP1 = MD5Util.getMD5Str(CP);

            if (StringUtils.equals(CP1, _pwd)) {
                return sysDeptUser;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("decrypt username or password failure.", e);
        }

        return null;
    }

    @Override
    public SysDeptUser loginCheck(String username, String password) {
        SysDeptUser sysDeptUser = sysDeptUserDao.findByUsername(username);

        if (sysDeptUser == null) {
            return null;
        }

        String pwd1 = MD5Util.getMD5Str(password) + sysDeptUser.getSalt();
        String pwd = MD5Util.getMD5Str(pwd1);

        if (StringUtils.equals(pwd, sysDeptUser.getPassword())) {
            return sysDeptUser;
        }

        return null;
    }


    @Override
    public Page<SysDeptUser> findByKeywordAndDeptId(Pageable pageable, String keyword, Long deptId) {
        return sysDeptUserDao.findByKeywordAndDeptId(pageable, keyword, deptId);
    }

    @Override
    public void delete(long id) {
        sysDeptUserDao.delete(id);
    }

    @Override
    public void save(SysDeptUser sysDeptUser) {
        sysDeptUserDao.save(sysDeptUser);
    }

    @Override
    public SysDeptUser findOne(Long id) {
        return sysDeptUserDao.findOne(id);
    }

    @Override
    public void saveAndFlush(SysDeptUser sysDeptUser) {
        sysDeptUserDao.saveAndFlush(sysDeptUser);
    }


}
