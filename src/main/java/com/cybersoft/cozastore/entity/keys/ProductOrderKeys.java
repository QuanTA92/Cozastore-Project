package com.cybersoft.cozastore.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
public class ProductOrderKeys implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "id_product")
    private int idProduct;


    @Column(name = "id_order")
    private int idOrder;

    public ProductOrderKeys() {
        // Constructor không đối số
    }

    public ProductOrderKeys(int idProduct, int idOrder) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
}
