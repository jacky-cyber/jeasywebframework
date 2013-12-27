package com.jeasywebframework.domain.config;


import com.jeasywebframework.domain.BaseAudit;

import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public class Constant extends BaseAudit implements Serializable {

    private Long id;

    private String name;

    private String descp;

    private String val;

    private String enabled;



    // ================================

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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

}
