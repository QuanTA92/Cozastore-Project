package com.cybersoft.cozastore.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private StatusEntity status;

    @OneToMany(mappedBy = "order")
    private List<ProductOrderEntity> productOrderEntities;

    @Column(name = "create_date")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<ProductOrderEntity> getProductOrderEntities() {
        return productOrderEntities;
    }

    public void setProductOrderEntities(List<ProductOrderEntity> productOrderEntities) {
        this.productOrderEntities = productOrderEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
