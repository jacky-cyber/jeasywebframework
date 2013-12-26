package com.jeasywebframework.service.dept;

import com.jeasywebframework.domain.dept.Resource;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-24.
 */
public interface ResourceService {


    List<Resource> findAll();


    Resource findOne(Long id);


    Long countByParentId(Long parentId);


    void saveAndFlush(Resource resource);


    void save(Resource resource);


    void batchSave(List<Resource> resources);

}
