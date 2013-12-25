package com.jeasywebframework.domain.dev;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Entity
@Table(name = "sys_dev_gen_column")
public class SysGenColumn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    private String name;

    @Column(name = "small_name")
    private String smallName;

    @Column(name = "big_name")
    private String bigName;

    private String cname;

    private String descp;

    @Column(name = "db_type")
    private String dbType;

    @Column(name = "db_key")
    private String dbKey;

    @Column(name = "db_null")
    private String dbNull;


    @Column(name = "java_type")
    private String javaType;

    @Column(name = "html_type")
    private String htmlType;

    private String required;

    @Column(name = "max_length")
    private int maxLength;

    @Column(name = "min_length")
    private int minLength;

    @Column(name = "required_msg")
    private String requiredMsg;

    @Column(name = "max_val")
    private String maxVal;

    @Column(name = "min_val")
    private String minVal;

    @Column(name = "date_format")
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
