package com.jeasywebframework.domain.cms;

import java.io.Serializable;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
public class Catalog implements Serializable {

    public static final String INFO_TYPE_NEWS = "news"; //新闻
    public static final String INFO_TYPE_PHOTO = "photo"; // 图集
    public static final String INFO_TYPE_DOWNLOAD = "download"; // 下载
    public static final String INFO_TYPE_VEDIO = "vedio"; // 视频
    public static final String INFO_TYPE_PRODUCT = "product"; // 产品



    private Long id; // 主键ID

    private Long parentId; // 上级分类ID

    private String name; // 名称

    private String code; // 编码

    private String keywords; // 关键词

    private String descp; // 描述

    private int level; // 等级

    private String type; // 节点类型


    private String faceImageUrl; // 封面图片url

    private int faceImageHeight; // 封面图片高度

    private int faceImageWidth; // 封面图片宽度


    private String targetUrl; // 链接 可以填写外部的链接地址直接跳出

    private String linkType; // _target _blank

    // ========================================================================================
    // ========================================================================================
    // ========================================================================================


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaceImageUrl() {
        return faceImageUrl;
    }

    public void setFaceImageUrl(String faceImageUrl) {
        this.faceImageUrl = faceImageUrl;
    }

    public int getFaceImageHeight() {
        return faceImageHeight;
    }

    public void setFaceImageHeight(int faceImageHeight) {
        this.faceImageHeight = faceImageHeight;
    }

    public int getFaceImageWidth() {
        return faceImageWidth;
    }

    public void setFaceImageWidth(int faceImageWidth) {
        this.faceImageWidth = faceImageWidth;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
}
