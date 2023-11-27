package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.OrderEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.ProductOrderEntity;
import com.cybersoft.cozastore.entity.StatusEntity;
import com.cybersoft.cozastore.entity.keys.ProductOrderKeys;
import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.payload.response.OrderResponse;
import com.cybersoft.cozastore.payload.response.UserOrderHistoryResponse;
import com.cybersoft.cozastore.repository.OrderRepository;
import com.cybersoft.cozastore.repository.ProductOrderRepository;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.repository.UserRepository;
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

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;




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

    @Override
    public List<UserOrderHistoryResponse> getAllOrderUserByIdUser(int idUser) {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<UserOrderHistoryResponse> userOrderHistoryResponses = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntities) {
            if (orderEntity.getUser().getId() == idUser) {
                for (ProductOrderEntity productOrderEntity : orderEntity.getProductOrderEntities()) {
                    UserOrderHistoryResponse userOrderHistoryResponse = new UserOrderHistoryResponse();

                    if (productOrderEntity.getProduct() != null) {
                        userOrderHistoryResponse.setIdUser(orderEntity.getUser().getId());
                        userOrderHistoryResponse.setIdOrder(orderEntity.getId());
                        userOrderHistoryResponse.setNameUser(orderEntity.getUser().getUsername());
                        userOrderHistoryResponse.setNameProduct(productOrderEntity.getProduct().getName());
                        userOrderHistoryResponse.setQuantity(productOrderEntity.getQuanity());
                        userOrderHistoryResponse.setPrice(productOrderEntity.getPrice());
                        userOrderHistoryResponse.setNameStatus(orderEntity.getStatus().getName());
                        userOrderHistoryResponse.setCreateDate(String.valueOf(orderEntity.getCreateDate()));

                        userOrderHistoryResponses.add(userOrderHistoryResponse);
                    }
                }
            }
        }

        return userOrderHistoryResponses;
    }



}
