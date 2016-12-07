package com.example.administrator.ninegridview.bean;

import com.lzy.ninegrid.ImageInfo;

import java.util.List;

public class InfoBean {
    private int type;
    private String userImage;
    private String userName;
    private String contact;
    private String work;
    private String content;
    private List<ImageInfo> list;
    private String webImage;
    private String webContent;
    private String webUrl;

    public InfoBean() {
    }

    public InfoBean(int type, String userImage, String userName, String contact, String work, String content, List<ImageInfo> list, String webImage, String webContent, String webUrl) {
        this.type = type;
        this.userImage = userImage;
        this.userName = userName;
        this.contact = contact;
        this.work = work;
        this.content = content;
        this.list = list;
        this.webImage = webImage;
        this.webContent = webContent;
        this.webUrl = webUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ImageInfo> getList() {
        return list;
    }

    public void setList(List<ImageInfo> list) {
        this.list = list;
    }

    public String getWebImage() {
        return webImage;
    }

    public void setWebImage(String webImage) {
        this.webImage = webImage;
    }

    public String getWebContent() {
        return webContent;
    }

    public void setWebContent(String webContent) {
        this.webContent = webContent;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
