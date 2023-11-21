package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.OrderEntity;
import com.cybersoft.cozastore.payload.response.OrderResponse;
import com.cybersoft.cozastore.repository.OrderRepository;
import com.cybersoft.cozastore.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<OrderResponse> getOrderById(int idOrder) {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(OrderEntity orderEntity : orderEntities){

        }

        return null;
    }
}
