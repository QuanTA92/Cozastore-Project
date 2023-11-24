package com.cybersoft.cozastore.entity;


import com.cybersoft.cozastore.entity.keys.ProductOrderKeys;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "product_order")
public class ProductOrderEntity {

    @EmbeddedId
    private ProductOrderKeys keys; // đặt tên gì cũng dược không quan trọng

    @Column(name = "quanity")
    private int quanity;

    @Column(name = "price")
    private double price;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false) // thêm vào đê tránh đi vòng lặp vô tận
    private OrderEntity order;

    public ProductOrderKeys getKeys() {
        return keys;
    }

    public void setKeys(ProductOrderKeys keys) {
        this.keys = keys;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductOrderEntity() {
        this.keys = new ProductOrderKeys();
    }

}
