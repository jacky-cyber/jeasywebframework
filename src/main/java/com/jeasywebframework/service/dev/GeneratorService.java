package com.jeasywebframework.service.dev;

import com.jeasywebframework.domain.dev.SysGenColumn;
import com.jeasywebframework.domain.dev.SysGenTable;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public interface GeneratorService {

    String domainPkg = "com.jeasywebframework.domain";
    String daoPkg = "com.jeasycms.dao";
    String controllerPkg = "com.jeasywebframework.web.controller";



    List<SysGenTable> findAllTable();


    SysGenTable findByName(String name);

    List<SysGenColumn> findAllColumn(String name);

    void saveAll(SysGenTable table, List<SysGenColumn> columns);


}
