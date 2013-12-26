package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.dev.Tracker;


import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
public interface TrackerDao {


    Long countByParentId(Long parentId);

    List<Tracker> findByParentId(Long parentId, int offset, int limit);


    List<Tracker> findByPathLike(String path);

    Tracker findOne(Long id);

    void save(Tracker parent);

    void update(Tracker parent);


}
