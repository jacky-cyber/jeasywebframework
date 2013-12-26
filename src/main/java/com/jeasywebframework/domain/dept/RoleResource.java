package com.jeasywebframework.domain.dept;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public class RoleResource implements Serializable {


    private Long id;

    private Long roleId;

    private Long resourceId;

    private Long createUserId;

    private Long updateUserId;

    private Date createTime;

    private Date updateTime;


    // ========================================================================================
    // ========================================================================================
    // ========================================================================================


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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
}
