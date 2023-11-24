package com.cybersoft.cozastore.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "id_blog")
    private BlogEntity blog;

    @Column(name = "create_date")
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogEntity getBlog() {
        return blog;
    }

    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
