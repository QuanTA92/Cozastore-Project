package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.ProductOrderRequest;
import com.cybersoft.cozastore.payload.response.ProductOrderResponse;

import java.io.IOException;
import java.util.List;

public interface ProductOrderServiceImp {

    boolean insertProductOrder(ProductOrderRequest productOrderRequest) throws IOException;

    List<ProductOrderResponse> getAllProductOrder();

}
