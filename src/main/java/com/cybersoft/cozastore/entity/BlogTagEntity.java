package com.cybersoft.cozastore.entity;


import com.cybersoft.cozastore.entity.keys.BlogTagKeys;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "blog_tag")
public class BlogTagEntity {

    @EmbeddedId
    private BlogTagKeys blogTagKeys;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "id_blog", insertable = false, updatable = false)
    private BlogEntity blog;

    @ManyToOne
    @JoinColumn(name = "id_tag", insertable = false, updatable = false)
    private TagEntity tag;



    public BlogTagKeys getBlogTagKeys() {
        return blogTagKeys;
    }

    public void setBlogTagKeys(BlogTagKeys blogTagKeys) {
        this.blogTagKeys = blogTagKeys;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BlogEntity getBlog() {
        return blog;
    }

    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }
}
