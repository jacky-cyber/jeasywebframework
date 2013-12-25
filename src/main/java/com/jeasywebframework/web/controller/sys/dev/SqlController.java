package com.jeasywebframework.web.controller.sys.dev;

import com.jeasywebframework.dao.dev.SysDevSqlLogDao;
import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dev.SysDevSqlLog;
import com.jeasywebframework.utils.AjaxUtil;
import com.jeasywebframework.utils.IpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Controller
@RequestMapping("/dev/sql/")
public class SqlController {

    private static final Logger logger = LoggerFactory.getLogger(SqlController.class);

    @Autowired
    private SysDevSqlLogDao sysDevSqlLogDao;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return "sys/dev/sql/index";
    }

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public String sqlLogHtml() {
        return "sys/dev/sql/list";
    }

    @RequestMapping(value = "/list.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject sqlLogAjax(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "rows", defaultValue = "20") int rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<SysDevSqlLog> pageRst = sysDevSqlLogDao.findAll(pageable);
        return AjaxUtil.jqGridJson(pageRst);
    }


    /**
     * 通用查询
     *
     * @param sql sql语句
     * @return Json；
     */
    @RequestMapping(value = "/query.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject query(String sql, HttpServletRequest request, HostHolder hostHolder) {
        logger.debug("开始执行通用查询，sql=[" + sql + "]......");

        if (StringUtils.isBlank(sql)) {
            return AjaxUtil.failure("SQL语句不能为空值！");
        }
        try {
            //记录SQL操作日志
            SysDevSqlLog sqlLog = new SysDevSqlLog();
            Date now = new Date(System.currentTimeMillis());

            sqlLog.setCreateUserId(hostHolder.getHostId());
            sqlLog.setCreateTime(now);
            sqlLog.setUpdateUserId(hostHolder.getHostId());
            sqlLog.setUpdateTime(now);

            sqlLog.setSql(sql);
            sqlLog.setIp(IpUtil.getIp(request));

        } catch (Exception ex) {
            logger.error("记录用户操作记录失败！", ex);
        }


        if (StringUtils.isBlank(sql)) {
            return AjaxUtil.failure("SQL语句不能为空值！");
        }

        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            resultSet = statement.getResultSet();
            int num = resultSet.getMetaData().getColumnCount();
            int count = 0;
            JSONArray jsonArray = new JSONArray();


            while (resultSet.next()) {

                count++;
                JSONObject jo = new JSONObject();

                for (int i = 0; i < num; i++) {
                    String columnName = resultSet.getMetaData().getColumnName(i + 1);
                    String val = resultSet.getString(columnName);

                    logger.debug("=======" + columnName + "==========" + val);

                    if (StringUtils.isBlank(val)) {
                        jo.put(columnName, "NULL");
                    } else {
                        jo.put(columnName, val);
                    }

                    logger.debug("====================>" + jo);
                }

                jsonArray.add(jo);

                if (count > 5000) {
                    logger.error("通用查询返回记录最大为5000！！！");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("count", count);
                    jsonObject.put("list", jsonArray);

                    jsonObject.put("errorMsg", "通用查询返回记录最大为5000！！！");
                    return jsonObject;
                }

            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", count);
            jsonObject.put("list", jsonArray);
            return jsonObject;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return AjaxUtil.failure(e.toString());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error(ex.toString());
            }
        }

    }

    /**
     * 通用查询更新操作
     *
     * @param sql
     * @return
     */
    @RequestMapping(value = "/update.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject update(String sql, HttpServletRequest request) {

        logger.debug("开始执行通用查询，sql=[" + sql + "]......");


        if (StringUtils.isBlank(sql)) {
            return AjaxUtil.failure("SQL语句不能为空值！");
        }


        try { //记录SQL操作日志

        } catch (Exception ex) {
            logger.error("记录用户操作记录失败！", ex);
        }

        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            int update = statement.executeUpdate(sql);

            return AjaxUtil.success("更新sql语句成功，更新了[" + update + "]条记录！");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return AjaxUtil.failure(e.toString());
        } finally {
            try {

                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error(ex.toString());
            }
        }

    }

}
