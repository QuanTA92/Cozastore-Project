package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.response.OrderResponse;

import java.util.List;

public interface OrderServiceImp {

    List<OrderResponse> getOrderById(int idOrder);
}
