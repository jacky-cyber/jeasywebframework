package com.jeasywebframework.domain.dev;

import com.jeasywebframework.domain.BaseAudit;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by  jeasywebframework@gmail.com on 13-12-25.
 */
public class SqlLog extends BaseAudit implements Serializable {

    public SqlLog() {
        this.createUserId = 0L;
        this.createTime = new Date(System.currentTimeMillis());
        this.updateUserId = 0L;
        this.updateTime = new Date(System.currentTimeMillis());
    }

    private Long id;

    private String sqlType;

    private String sql;

    private String ip;

    // =====================================================================
    // =====================================================================
    // =====================================================================


    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
