package com.jeasywebframework.domain.dev;

import com.jeasywebframework.domain.BaseAudit;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
public class ColumnInfo extends BaseAudit implements Serializable {

    public ColumnInfo() {
        this.createUserId = 0L;
        this.createTime = new Date(System.currentTimeMillis());
        this.updateUserId = 0L;
        this.updateTime = new Date(System.currentTimeMillis());
    }

    private Long id;

    private String tableName;

    private String name;

    private String smallName;

    private String bigName;

    private String cname;

    private String descp;

    private String dbType;

    private String dbKey;

    private String dbNull;

    private String javaType;

    private String htmlType;

    private String required;

    private int maxLength;

    private int minLength;

    private String requiredMsg;

    private String maxVal;

    private String minVal;

    private String dateFormat;


    // ========================================================================================
    // ========================================================================================
    // ========================================================================================


    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getBigName() {
        return bigName;
    }

    public void setBigName(String bigName) {
        this.bigName = bigName;
    }

    public String getDbNull() {
        return dbNull;
    }

    public void setDbNull(String dbNull) {
        this.dbNull = dbNull;
    }

    public String getDbKey() {
        return dbKey;
    }

    public void setDbKey(String dbKey) {
        this.dbKey = dbKey;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public String getRequiredMsg() {
        return requiredMsg;
    }

    public void setRequiredMsg(String requiredMsg) {
        this.requiredMsg = requiredMsg;
    }

    public String getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(String maxVal) {
        this.maxVal = maxVal;
    }

    public String getMinVal() {
        return minVal;
    }

    public void setMinVal(String minVal) {
        this.minVal = minVal;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
