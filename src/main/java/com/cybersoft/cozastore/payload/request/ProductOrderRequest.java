package com.cybersoft.cozastore.payload.request;

import java.util.Date;

public class ProductOrderRequest {

    private int idCart;

    private int idProduct;

//    private int quantity;

    private double price;

    private int idUser;

//    private int idStatus;

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

//    public int getIdStatus() {
//        return idStatus;
//    }
//
//    public void setIdStatus(int idStatus) {
//        this.idStatus = idStatus;
//    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }


//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
