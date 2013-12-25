package com.jeasywebframework.domain.dev;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Entity
@Table(name = "sys_gen_table")
public class SysGenTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String cname;

    private String descp;


    @Column(name = "domain_pkg")
    private String domainPkg;

    @Column(name = "domain_class")
    private String domainClass;

    @Column(name = "dao_pkg")
    private String daoPkg;

    @Column(name = "dao_class")
    private String daoClass;

    @Column(name = "controller_pkg")
    private String controllerPkg;

    @Column(name = "controller_class")
    private String controllerClass;

    @Column(name = "url_prefix")
    private String urlPrefix;

    @Column(name = "velocity_path_prefix")
    private String velocityPathPrefix;


    public String smallFirst(String clazz) {
        String a = clazz.substring(0, 1);
        String b = clazz.substring(1, clazz.length());
        return a.toLowerCase() + b;
    }



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

    public String getDomainPkg() {
        return domainPkg;
    }

    public void setDomainPkg(String domainPkg) {
        this.domainPkg = domainPkg;
    }

    public String getDomainClass() {
        return domainClass;
    }

    public void setDomainClass(String domainClass) {
        this.domainClass = domainClass;
    }

    public String getDaoPkg() {
        return daoPkg;
    }

    public void setDaoPkg(String daoPkg) {
        this.daoPkg = daoPkg;
    }

    public String getDaoClass() {
        return daoClass;
    }

    public void setDaoClass(String daoClass) {
        this.daoClass = daoClass;
    }

    public String getControllerPkg() {
        return controllerPkg;
    }

    public void setControllerPkg(String controllerPkg) {
        this.controllerPkg = controllerPkg;
    }

    public String getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(String controllerClass) {
        this.controllerClass = controllerClass;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getVelocityPathPrefix() {
        return velocityPathPrefix;
    }

    public void setVelocityPathPrefix(String velocityPathPrefix) {
        this.velocityPathPrefix = velocityPathPrefix;
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
}
