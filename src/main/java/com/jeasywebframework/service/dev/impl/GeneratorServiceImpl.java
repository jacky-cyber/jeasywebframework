package com.jeasywebframework.service.dev.impl;

import com.jeasywebframework.dao.dev.ColumnDao;
import com.jeasywebframework.dao.dev.TableDao;
import com.jeasywebframework.domain.dev.GenColumn;
import com.jeasywebframework.domain.dev.GenTable;
import com.jeasywebframework.service.dev.GeneratorService;
import com.jeasywebframework.utils.NameUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {


    private static final Logger logger = LoggerFactory.getLogger(GeneratorServiceImpl.class);



    @Autowired
    private DataSource dataSource;


    @Autowired
    private TableDao tableDao;

    @Autowired
    private ColumnDao columnDao;

    @Override
    public List<GenTable> findAllTable() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            List<GenTable> tableList = new ArrayList<GenTable>();

            resultSet = statement.executeQuery("show tables");
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                GenTable table = new GenTable();
                table.setName(name);
                tableList.add(table);
            }


            List<GenTable> rst = tableDao.findAll();

            Map<String, GenTable> map = list2Map(rst);

            List<GenTable> lastList = new ArrayList<GenTable>();

            for (GenTable table : tableList) {
                if (map.containsKey(table.getName())) {
                    GenTable ttt = map.get(table.getName());
                    buildTable(ttt);
                    lastList.add(ttt);
                } else {
                    buildTable(table);
                    lastList.add(table);
                }
            }

            return lastList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Collections.emptyList();
    }

    private void buildTable(GenTable table) {
        String bigName = NameUtil.getBigName(table.getName());

        if (StringUtils.isEmpty(table.getDomainPkg())) {
            table.setDomainPkg(domainPkg);
        }
        if (StringUtils.isEmpty(table.getDomainClass())) {
            table.setDomainClass(bigName);
        }

        if (StringUtils.isEmpty(table.getDaoPkg())) {
            table.setDaoPkg(daoPkg);
        }
        if (StringUtils.isEmpty(table.getDaoClass())) {
            table.setDaoClass(bigName + "Dao");
        }

        if (StringUtils.isEmpty(table.getControllerPkg())) {
            table.setControllerPkg(controllerPkg);
        }
        if (StringUtils.isEmpty(table.getControllerClass())) {
            table.setControllerClass(bigName + "Controller");
        }

        if (StringUtils.isEmpty(table.getUrlPrefix())) {
            table.setUrlPrefix("/" + StringUtils.replace(table.getName(), "_", "/") + "/");
        }
        if (StringUtils.isEmpty(table.getVelocityPathPrefix())) {
            table.setVelocityPathPrefix(StringUtils.replace(table.getName(), "_", "/") + "/");
        }


    }


    @Override
    public GenTable findByName(String name) {

        GenTable table = tableDao.findByName(name);
        if (table == null) {
            table = new GenTable();
            table.setName(name);
        }
        buildTable(table);

        return table;
    }

    @Override
    public List<GenColumn> findAllColumn(String name) {
        List<GenColumn> columnList = columnDao.findByTableName(name);
        Map<String, GenColumn> map = columnList2Map(columnList);


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            List<GenColumn> columnList1 = new ArrayList<GenColumn>();

            resultSet = statement.executeQuery("desc " + name);
            while (resultSet.next()) {
//                        +--------------+--------------+------+-----+---------+----------------+
//                        | Field        | Type         | Null | Key | Default | Extra          |
//                        +--------------+--------------+------+-----+---------+----------------+
//                        | id           | bigint(20)   | NO   | PRI | NULL    | auto_increment |
                GenColumn sgc = new GenColumn();
                sgc.setName(resultSet.getString(1));
                sgc.setDbKey(resultSet.getString(4));
                sgc.setDbType(resultSet.getString(2));
                sgc.setDbNull(resultSet.getString(3));

                columnList1.add(sgc);
            }


            List<GenColumn> lastList = new ArrayList<GenColumn>();

            for (GenColumn c : columnList1) {
                if (map.containsKey(c.getName())) {
                    GenColumn sss = map.get(c.getName());
                    buildColumn(sss);
                    lastList.add(sss);
                } else {
                    buildColumn(c);
                    lastList.add(c);
                }
            }

            return lastList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void saveAll(GenTable table, List<GenColumn> columns) {
        if(table.getId()!=null && table.getId().intValue()>0){
            tableDao.update(table);
        }else{
            tableDao.save(table);
        }
        for (GenColumn column : columns) {
            if(column.getId()!=null && column.getId().intValue()>0){
                columnDao.update(column);
            }else{
                columnDao.save(column);
            }
        }
    }


    private void buildColumn(GenColumn c) {
        String dbType = c.getDbType();

        if (StringUtils.isEmpty(c.getJavaType())) {
            if (dbType.indexOf("int") > -1) {
                c.setJavaType("java.lang.Integer");
            }

            if (dbType.indexOf("bigint") > -1) {
                c.setJavaType("java.lang.Long");
            }

            if (dbType.indexOf("double") > -1) {
                c.setJavaType("java.lang.Double");
            }
            if (dbType.indexOf("float") > -1) {
                c.setJavaType("java.lang.Float");
            }
            if (dbType.indexOf("datetime") > -1) {
                c.setJavaType("java.util.Date");
            }
            if (dbType.indexOf("varchar") > -1) {
                c.setJavaType("java.lang.String");
            }
        }

        if (StringUtils.isEmpty(c.getMaxVal())) {
            c.setMaxVal("0");
        }

        if (StringUtils.isEmpty(c.getMinVal())) {
            c.setMinVal("0");
        }


        if (StringUtils.isEmpty(c.getHtmlType())) {
            if (dbType.indexOf("int") > -1) {
                c.setHtmlType("text");
            }

            if (dbType.indexOf("bigint") > -1) {
                c.setHtmlType("text");
            }

            if (dbType.indexOf("double") > -1) {
                c.setHtmlType("text");
            }
            if (dbType.indexOf("float") > -1) {
                c.setHtmlType("text");
            }
            if (dbType.indexOf("datetime") > -1) {
                c.setHtmlType("date_input");
            }
            if (dbType.indexOf("varchar") > -1) {
                c.setJavaType("text");
            }
        }

        if (StringUtils.isEmpty(c.getRequired())) {
            if (StringUtils.equals(c.getDbNull().toUpperCase(), "YES")) {
                c.setRequired("N");
            } else {
                c.setRequired("Y");
            }
        }


        if (StringUtils.isEmpty(c.getDateFormat())) {
            c.setDateFormat("yyyy-MM-dd");
        }

        if (StringUtils.isEmpty(c.getSmallName())) {
            c.setSmallName(NameUtil.getSmallName(c.getName()));
        }

        if (StringUtils.isEmpty(c.getBigName())) {
            c.setBigName(NameUtil.getBigName(c.getName()));
        }

    }


    private Map<String, GenColumn> columnList2Map(List<GenColumn> columns) {
        Map<String, GenColumn> map = new HashMap<String, GenColumn>();
        for (GenColumn column : columns) {
            map.put(column.getName(), column);
        }
        return map;
    }

    private Map<String, GenTable> list2Map(List<GenTable> tables) {
        Map<String, GenTable> map = new HashMap<String, GenTable>();

        for (GenTable table : tables) {
            map.put(table.getName(), table);
        }

        return map;
    }


}
