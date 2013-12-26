package com.jeasywebframework.domain.dept;

import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public class Role {

    private Long id;

    private String name;

    private String descp;

    private String enabled;

    private Long oorder;

    private Long createUserId;

    private Long updateUserId;

    private Date createTime;

    private Date updateTime;


    // ========================================================================================
    // ========================================================================================
    // ========================================================================================


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

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Long getOorder() {
        return oorder;
    }

    public void setOorder(Long oorder) {
        this.oorder = oorder;
    }

}
