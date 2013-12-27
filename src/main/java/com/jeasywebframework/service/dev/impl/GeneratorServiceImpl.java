package com.jeasywebframework.service.dev.impl;

import com.jeasywebframework.dao.dev.ColumnDao;
import com.jeasywebframework.dao.dev.TableDao;
import com.jeasywebframework.domain.dev.ColumnInfo;
import com.jeasywebframework.domain.dev.TableInfo;
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
    public List<TableInfo> findAllTable() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            List<TableInfo> tableList = new ArrayList<TableInfo>();

            resultSet = statement.executeQuery("show tables");
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                TableInfo table = new TableInfo();
                table.setName(name);
                tableList.add(table);
            }


            List<TableInfo> rst = tableDao.findAll();

            Map<String, TableInfo> map = list2Map(rst);

            List<TableInfo> lastList = new ArrayList<TableInfo>();

            for (TableInfo table : tableList) {
                if (map.containsKey(table.getName())) {
                    TableInfo ttt = map.get(table.getName());
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

    private void buildTable(TableInfo table) {
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
    public TableInfo findByName(String name) {

        TableInfo table = tableDao.findByName(name);
        if (table == null) {
            table = new TableInfo();
            table.setName(name);
        }
        buildTable(table);

        return table;
    }

    @Override
    public List<ColumnInfo> findAllColumn(String name) {
        List<ColumnInfo> columnList = columnDao.findByTableName(name);
        Map<String, ColumnInfo> map = columnList2Map(columnList);


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            List<ColumnInfo> columnList1 = new ArrayList<ColumnInfo>();

            resultSet = statement.executeQuery("desc " + name);
            while (resultSet.next()) {
//                        +--------------+--------------+------+-----+---------+----------------+
//                        | Field        | Type         | Null | Key | Default | Extra          |
//                        +--------------+--------------+------+-----+---------+----------------+
//                        | id           | bigint(20)   | NO   | PRI | NULL    | auto_increment |
                ColumnInfo sgc = new ColumnInfo();
                sgc.setName(resultSet.getString(1));
                sgc.setDbKey(resultSet.getString(4));
                sgc.setDbType(resultSet.getString(2));
                sgc.setDbNull(resultSet.getString(3));

                columnList1.add(sgc);
            }


            List<ColumnInfo> lastList = new ArrayList<ColumnInfo>();

            for (ColumnInfo c : columnList1) {
                if (map.containsKey(c.getName())) {
                    ColumnInfo sss = map.get(c.getName());
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
    public void saveAll(TableInfo table, List<ColumnInfo> columns) {
        if(table.getId()!=null && table.getId().intValue()>0){
            tableDao.update(table);
        }else{
            tableDao.save(table);
        }
        for (ColumnInfo column : columns) {
            if(column.getId()!=null && column.getId().intValue()>0){
                columnDao.update(column);
            }else{
                columnDao.save(column);
            }
        }
    }


    private void buildColumn(ColumnInfo c) {
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


    private Map<String, ColumnInfo> columnList2Map(List<ColumnInfo> columns) {
        Map<String, ColumnInfo> map = new HashMap<String, ColumnInfo>();
        for (ColumnInfo column : columns) {
            map.put(column.getName(), column);
        }
        return map;
    }

    private Map<String, TableInfo> list2Map(List<TableInfo> tables) {
        Map<String, TableInfo> map = new HashMap<String, TableInfo>();

        for (TableInfo table : tables) {
            map.put(table.getName(), table);
        }

        return map;
    }


}
