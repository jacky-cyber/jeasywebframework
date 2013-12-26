package com.jeasywebframework.service.dev;

import com.jeasywebframework.domain.dev.GenColumn;
import com.jeasywebframework.domain.dev.GenTable;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public interface GeneratorService {

    String domainPkg = "com.jeasywebframework.domain";
    String daoPkg = "com.jeasycms.dao";
    String controllerPkg = "com.jeasywebframework.web.controller";



    List<GenTable> findAllTable();


    GenTable findByName(String name);

    List<GenColumn> findAllColumn(String name);

    void saveAll(GenTable table, List<GenColumn> columns);


}
