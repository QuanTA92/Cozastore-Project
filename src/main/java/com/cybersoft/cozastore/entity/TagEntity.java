package com.cybersoft.cozastore.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "tag")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany
    @JoinColumn(name = "tag")
    private List<BlogTagEntity> blogTagEntities;


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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<BlogTagEntity> getBlogTagEntities() {
        return blogTagEntities;
    }

    public void setBlogTagEntities(List<BlogTagEntity> blogTagEntities) {
        this.blogTagEntities = blogTagEntities;
    }
}
