package com.jeasywebframework.domain.cms;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Entity()
@Table(name = "cms_article")
public class CmsArticle implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键id

    /**
     *
     */
    @Column(name = "catalog_id")
    private Long catalogId; // 分类

    private String title; // 标题

    private String keywords; // 关键词

    private String descp; // 描述


    @Column(name = "publish_time")
    private Date publishTime; // 发布时间


    @Column(name = "from_source")
    private String from; // 来源


    @Column(name = "from_link_url")
    private String fromUrl; // 来源链接


    private String author; // 作者

    @Column(name = "face_image_url")
    private String faceImageUrl; // 标题图片

    @Column(name = "face_image_height")
    private int faceImageHeight;

    @Column(name = "face_image_width")
    private int faceImageWidth;


    @Column(columnDefinition = "text")
    private String content; // 正文


    @Column(name = "create_admin_user_id")
    private Long createAdminUserId; // 创建人id

    @Column(name = "create_time")
    private Date createTime; // 创建时间

    @Column(name = "update_time")
    private Date updateTime; // 更新时间

    @Column(name = "update_admin_user_id")
    private Long updateAdminUserId; // 更新人id


    // ========================================================================================
    // ========================================================================================
    // ========================================================================================

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateAdminUserId() {
        return createAdminUserId;
    }

    public void setCreateAdminUserId(Long createAdminUserId) {
        this.createAdminUserId = createAdminUserId;
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

    public Long getUpdateAdminUserId() {
        return updateAdminUserId;
    }

    public void setUpdateAdminUserId(Long updateAdminUserId) {
        this.updateAdminUserId = updateAdminUserId;
    }
}
