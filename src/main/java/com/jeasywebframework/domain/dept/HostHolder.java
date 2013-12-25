package com.jeasywebframework.domain.dept;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public class HostHolder {

    public static final String COOKIE_KEY_PWD = "_$p";
    public static final String COOKIE_KEY_USERNAME = "_$u";


    public static final String REQUEST_KEY_HOLDER = "_$host_holder";



    private SysDeptUser host;
    private SysDeptDepartment department;
    private List<SysDeptRole> roleList;
    private List<SysDeptResource> resourceList;
    private List<SysDeptResource> moduleList;


    // ==========================================================================================
    // ==========================================================================================
    // ==========================================================================================


    public List<SysDeptResource> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<SysDeptResource> moduleList) {
        this.moduleList = moduleList;
    }

    public SysDeptDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SysDeptDepartment department) {
        this.department = department;
    }

    public List<SysDeptRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysDeptRole> roleList) {
        this.roleList = roleList;
    }

    public List<SysDeptResource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<SysDeptResource> resourceList) {
        this.resourceList = resourceList;
    }

    public SysDeptUser getHost() {
        return host;
    }

    public void setHost(SysDeptUser host) {
        this.host = host;
    }


    public boolean isLogin() {
        return host != null && StringUtils.equals(host.getEnabled(), "Y");
    }


    public Long getHostId() {
        if (host != null) {
            return host.getId();
        } else {
            return -1L;
        }
    }


}
