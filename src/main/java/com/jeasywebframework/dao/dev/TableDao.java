package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.dev.GenTable;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public interface TableDao {
    GenTable findByName(String name);

    List<GenTable> findAll();


    void save(GenTable table);


    void update(GenTable table);


}
