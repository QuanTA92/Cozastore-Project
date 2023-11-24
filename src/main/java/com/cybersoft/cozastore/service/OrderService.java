package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.OrderEntity;
import com.cybersoft.cozastore.entity.StatusEntity;
import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.payload.response.OrderResponse;
import com.cybersoft.cozastore.repository.OrderRepository;
import com.cybersoft.cozastore.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean updateOrderStatusByIdOrder(OrderRequest orderRequest) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderRequest.getIdOrder());

        if(optionalOrderEntity.isPresent()){
            OrderEntity orderEntity = optionalOrderEntity.get();

            StatusEntity statusEntity = new StatusEntity();
            statusEntity.setId(orderRequest.getIdStatus());
            orderEntity.setStatus(statusEntity);
            orderRepository.save(orderEntity);
            return true;
        } else {
            return false;
        }
    }


}
