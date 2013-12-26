package com.jeasywebframework.service.config.impl;

import com.jeasywebframework.domain.config.Constant;
import com.jeasywebframework.service.config.ConstantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by wangbing on 13-12-26.
 */
@Service
public class ConstantServiceImpl implements ConstantService {
    @Override
    public void saveAndFlush(Constant constant) {

    }

    @Override
    public Constant findOne(Long id) {
        return null;
    }

    @Override
    public void save(Constant constant) {

    }

    @Override
    public Page<Constant> findAll(Pageable pageable) {
        return null;
    }
}
