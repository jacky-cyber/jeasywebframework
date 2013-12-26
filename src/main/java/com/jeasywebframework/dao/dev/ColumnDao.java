package com.jeasywebframework.dao.dev;

import com.jeasywebframework.domain.dev.GenColumn;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public interface ColumnDao {

    List<GenColumn> findByTableName(String tableName);

    void update(GenColumn column);

    void save(GenColumn column);

}
