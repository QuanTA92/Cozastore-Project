package com.cybersoft.cozastore.payload.request;

import org.springframework.web.multipart.MultipartFile;


public class BlogRequest {

    private String title;

    private MultipartFile image;

    private String content;

    private int idUser;

    private String nameTag;

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }



}
