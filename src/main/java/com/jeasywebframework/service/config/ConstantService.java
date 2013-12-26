package com.jeasywebframework.service.config;

import com.jeasywebframework.domain.config.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
public interface ConstantService {
    void saveAndFlush(Constant constant);

    Constant findOne(Long id);

    void save(Constant constant);


    Page<Constant> findAll(Pageable pageable);

}
