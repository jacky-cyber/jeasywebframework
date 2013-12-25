package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.SysDeptResource;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
public interface ResourceService {


    List<SysDeptResource> findAll(Sort path);


    List<SysDeptResource> findAll();


    SysDeptResource findOne(Long id);


    Long countByParentId(Long parentId);


    void saveAndFlush(SysDeptResource sysDeptResource);


    void save(SysDeptResource sysDeptResource);


    void batchSave(List<SysDeptResource> resources);

}
