package com.jeasywebframework.domain.dept;

import com.jeasywebframework.domain.BaseAudit;

import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public class RoleResource extends BaseAudit implements Serializable {


    private Long id;

    private Long roleId;

    private Long resourceId;


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


}
