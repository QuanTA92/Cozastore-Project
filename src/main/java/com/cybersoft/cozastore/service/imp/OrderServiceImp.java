package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.payload.response.OrderResponse;
import com.cybersoft.cozastore.payload.response.UserOrderHistoryResponse;

import java.util.List;

public interface OrderServiceImp {

    List<OrderResponse> getOrderById(int idOrder);

    boolean updateOrderStatusByIdOrder(OrderRequest orderRequest);

    List<UserOrderHistoryResponse> getAllOrderUserByIdUser(int idUser);


}
