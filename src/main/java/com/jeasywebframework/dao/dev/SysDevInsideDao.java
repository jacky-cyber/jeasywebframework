package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.dev.Tracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
@Repository
public interface SysDevInsideDao extends JpaRepository<Tracker, Long> {


    Page<Tracker> findByParentId(Long parentId, Pageable pageable);


    List<Tracker> findByPathLike(String path, Sort sort);

}
