package com.cybersoft.cozastore.entity;

import javax.persistence.*;
import javax.swing.*;
import java.util.Date;
import java.util.List;

@Entity(name = "blog")
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "blog")
    private List<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "blog")
    private List<BlogTagEntity> blogTagEntities;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<BlogTagEntity> getBlogTagEntities() {
        return blogTagEntities;
    }

    public void setBlogTagEntities(List<BlogTagEntity> blogTagEntities) {
        this.blogTagEntities = blogTagEntities;
    }

    public List<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(List<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserEntity getIdUser() {
        return userEntity;
    }

    public void setIdUser(UserEntity idUser) {
        this.userEntity = idUser;
    }
}
