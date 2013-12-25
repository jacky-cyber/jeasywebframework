package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.SysDeptResource;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
public interface ResourceService {

    String CACHE_VALUE = "sys_dept_resource";


    List<SysDeptResource> findAll(Sort path);


    SysDeptResource findOne(Long id);


    Long countByParentId(Long parentId);


    void saveAndFlush(SysDeptResource sysDeptResource);


    void save(SysDeptResource sysDeptResource);


    void batchSave(List<SysDeptResource> resources);

}
