package com.jeasywebframework.domain.config;

import com.jeasywebframework.domain.BaseAudit;

import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-21.
 */
public class Dictitem extends BaseAudit implements Serializable {

    private Long id;

    private String name;

    private String text;

    private String value;

    private String groupName;

    private String descp;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

}
