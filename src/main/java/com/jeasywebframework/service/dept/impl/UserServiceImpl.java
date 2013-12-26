package com.jeasywebframework.service.dept.impl;

import com.jeasywebframework.dao.dept.UserDao;
import com.jeasywebframework.domain.dept.User;
import com.jeasywebframework.service.dept.UserService;
import com.jeasywebframework.utils.DESUtil;
import com.jeasywebframework.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDao userDao;

    @Override
    public boolean hasAccess(Long hostId, String url) {
        return true;
    }

    @Override
    public User findByCookieUsernameAndPwd(String username, String pwd) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)) {
            return null;
        }

        try {
            String _username = DESUtil.decrypt(username);
            String _pwd = DESUtil.decrypt(pwd);

            User user = userDao.findByUsername(_username);
            if (user == null) {
                return null;
            }

            String salt = user.getSalt();
            String CP = MD5Util.getMD5Str(user.getPassword()) + salt;
            String CP1 = MD5Util.getMD5Str(CP);

            if (StringUtils.equals(CP1, _pwd)) {
                return user;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("decrypt username or password failure.", e);
        }

        return null;
    }

    @Override
    public User loginCheck(String username, String password) {
        User user = userDao.findByUsername(username);

        if (user == null) {
            return null;
        }

        String pwd1 = MD5Util.getMD5Str(password) + user.getSalt();
        String pwd = MD5Util.getMD5Str(pwd1);

        if (StringUtils.equals(pwd, user.getPassword())) {
            return user;
        }

        return null;
    }


    @Override
    public Page<User> findByKeywordAndDeptId(Pageable pageable, String keyword, Long deptId) {
        Long count = userDao.countByKeywordAndDeptId(keyword, deptId);
        List<User> userList = userDao.findByKeywordAndDeptId(keyword, deptId, pageable.getOffset(), pageable.getPageSize());

        return new PageImpl<User>(userList,pageable,count);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findOne(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public void saveAndFlush(User user) {
        userDao.update(user);
    }


}
