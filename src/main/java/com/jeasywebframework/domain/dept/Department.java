package com.jeasywebframework.domain.dept;

import com.jeasywebframework.domain.BaseAudit;

import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public class Department extends BaseAudit implements Serializable {

    private Long id;

    private Long oorder;

    private String enabled;

    private String name;

    private String code;

    private String descp;

    private Integer level;

    private Long childrenNum;

    private Long parentId;

    private String path;


    // ========================================================================================
    // ========================================================================================
    // ========================================================================================


    public Long getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(Long childrenNum) {
        this.childrenNum = childrenNum;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOorder() {
        return oorder;
    }

    public void setOorder(Long oorder) {
        this.oorder = oorder;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
