package com.jeasywebframework.domain.dept;

import com.jeasywebframework.domain.BaseAudit;

import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public class Role extends BaseAudit implements Serializable {

    private Long id;

    private String name;

    private String descp;

    private String enabled;

    private Long oorder;



    // ========================================================================================
    // ========================================================================================
    // ========================================================================================



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
