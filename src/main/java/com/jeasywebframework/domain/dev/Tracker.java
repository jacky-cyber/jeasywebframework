package com.jeasywebframework.domain.dev;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
@Entity
@Table(name = "sys_dev_inside")
public class Tracker {

    public Tracker() {
        this.parentId = 0L;
        this.level = 0;
        this.path = "/";
        this.childrenNum = 0L;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private Long startTime;

    @Column(name = "end_time", nullable = false)
    private Long endTime;

    @Column(name = "thread_name", nullable = false)
    private String threadName;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String tag;

    private String descp;

    @Column(nullable = false)
    private Integer level;

    @Column(name = "children_num", nullable = false)
    private Long childrenNum;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Column(nullable = false)
    private String path;

    @Transient
    private List<Tracker> children = new ArrayList<Tracker>();

    // =========================================================================
    // =========================================================================
    // =========================================================================

    public void addChild(Tracker inside) {
        this.children.add(inside);
    }


    public List<Tracker> getChildren() {
        return children;
    }

    public void setChildren(List<Tracker> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public Long getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(Long childrenNum) {
        this.childrenNum = childrenNum;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
