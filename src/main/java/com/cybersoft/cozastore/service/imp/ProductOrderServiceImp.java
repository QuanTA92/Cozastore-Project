package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.ProductOrderRequest;
import com.cybersoft.cozastore.payload.response.ProductOrderResponse;

import java.util.List;

public interface ProductOrderServiceImp {

    boolean insertProductOrder(ProductOrderRequest productOrderRequest);

    List<ProductOrderResponse> getAllProductOrder();

}
