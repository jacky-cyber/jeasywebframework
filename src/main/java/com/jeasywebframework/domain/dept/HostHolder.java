package com.jeasywebframework.domain.dept;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
public class HostHolder {

    public static final String COOKIE_KEY_PWD = "_$p";
    public static final String COOKIE_KEY_USERNAME = "_$u";


    public static final String REQUEST_KEY_HOLDER = "hostHolder";



    private User host;
    private Department department;
    private List<Role> roleList;
    private List<Resource> resourceList;
    private List<Resource> moduleList;


    // ==========================================================================================
    // ==========================================================================================
    // ==========================================================================================


    public List<Resource> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Resource> moduleList) {
        this.moduleList = moduleList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
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
