package com.simplem.personal_blog.vo;

import java.util.Date;

/**
 * ClassName: FirstPageBlog
 * Package: com.simplem.personal_blog.vo
 * Description：
 * Author: simpleM
 * Date: 2020/10/23 20:32
 */
public class FirstPageBlog {
    //博客信息
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Date updateTime;
    private String description;

    //user
    private String nickname;
    private String avatar;

    //type
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "FirstPageBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", views=" + views +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
