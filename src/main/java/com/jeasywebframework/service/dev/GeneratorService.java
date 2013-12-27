package com.jeasywebframework.service.dev;

import com.jeasywebframework.domain.dev.ColumnInfo;
import com.jeasywebframework.domain.dev.TableInfo;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public interface GeneratorService {

    String domainPkg = "com.jeasywebframework.domain";
    String daoPkg = "com.jeasycms.dao";
    String controllerPkg = "com.jeasywebframework.web.controller";



    List<TableInfo> findAllTable();


    TableInfo findByName(String name);

    List<ColumnInfo> findAllColumn(String name);

    void saveAll(TableInfo table, List<ColumnInfo> columns);


}
