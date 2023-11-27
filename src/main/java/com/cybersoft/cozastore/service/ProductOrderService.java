package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.*;
import com.cybersoft.cozastore.entity.keys.ProductOrderKeys;
import com.cybersoft.cozastore.payload.request.ProductOrderRequest;
import com.cybersoft.cozastore.payload.response.ProductOrderResponse;
import com.cybersoft.cozastore.payload.response.UserOrderHistoryResponse;
import com.cybersoft.cozastore.repository.*;
import com.cybersoft.cozastore.service.imp.ProductOrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class ProductOrderService implements ProductOrderServiceImp {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean insertProductOrder(ProductOrderRequest productOrderRequest) throws IOException {

        // Lấy thông tin từ CartEntity sử dụng idCart
        Optional<CartEntity> optionalCartEntity = cartRepository.findById(productOrderRequest.getIdCart());
        if (optionalCartEntity.isEmpty()) {
            // Xử lý khi không tìm thấy CartEntity
            return false;
        }

        CartEntity cartEntity = optionalCartEntity.get();
        ProductEntity productEntity = new ProductEntity();

        // Tạo mới ProductOrderEntity và OrderEntity
        ProductOrderEntity productOrderEntity = new ProductOrderEntity();
        OrderEntity orderEntity = new OrderEntity();

        // Thiết lập thông tin cho ProductOrderEntity
        productOrderEntity.getKeys().setIdProduct(cartEntity.getProduct().getId());

        productOrderEntity.setQuanity(cartEntity.getQuanity());
        productOrderEntity.setPrice(productOrderRequest.getPrice());

        // Thiết lập thông tin cho OrderEntity
        orderEntity.setUser(cartEntity.getUser());

        orderEntity.setStatus(statusRepository.findById(3).orElse(null));

        // Thiết lập createDate
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Date createDate = Date.from(currentDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
        productOrderEntity.setCreateDate(createDate);
        orderEntity.setCreateDate(createDate);

        // Lưu OrderEntity để có được id_order
        orderEntity = orderRepository.save(orderEntity);

        // Thiết lập id_order trong ProductOrderEntity
        productOrderEntity.getKeys().setIdOrder(orderEntity.getId());

        // Lưu ProductOrderEntity
        productOrderRepository.save(productOrderEntity);

        // Xóa CartEntity
        cartRepository.deleteById(productOrderRequest.getIdCart());

        return true;
    }

    @Override
    public List<ProductOrderResponse> getAllProductOrder() {
        List<ProductOrderEntity> productOrderEntities = productOrderRepository.findAll();
        List<ProductOrderResponse> productOrderResponses = new ArrayList<>();

        for (ProductOrderEntity productOrderEntity : productOrderEntities){

            ProductOrderResponse productOrderResponse = new ProductOrderResponse();
            productOrderResponse.setNameProduct(productOrderEntity.getProduct().getName());
            productOrderResponse.setIdOrder(productOrderEntity.getOrder().getId());
            productOrderResponse.setNameUser(productOrderEntity.getOrder().getUser().getUsername());
            productOrderResponse.setQuantity(productOrderEntity.getQuanity());
            productOrderResponse.setPrice(productOrderEntity.getPrice());
            productOrderResponse.setCreateDate(productOrderEntity.getCreateDate());
            productOrderResponse.setStatusOrder(productOrderEntity.getOrder().getStatus().getName());

            productOrderResponses.add(productOrderResponse);
        }

        return productOrderResponses;
    }



}
