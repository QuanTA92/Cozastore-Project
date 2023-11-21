package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CartEntity;
import com.cybersoft.cozastore.entity.OrderEntity;
import com.cybersoft.cozastore.entity.ProductOrderEntity;
import com.cybersoft.cozastore.entity.keys.ProductOrderKeys;
import com.cybersoft.cozastore.payload.request.ProductOrderRequest;
import com.cybersoft.cozastore.payload.response.ProductOrderResponse;
import com.cybersoft.cozastore.repository.CartRepository;
import com.cybersoft.cozastore.repository.OrderRepository;
import com.cybersoft.cozastore.repository.ProductOrderRepository;
import com.cybersoft.cozastore.service.imp.ProductOrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductOrderService implements ProductOrderServiceImp {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean insertProductOrder(ProductOrderRequest productOrderRequest) {

        try {
            ProductOrderEntity productOrderEntity = new ProductOrderEntity();

            // Thiết lập giá trị cho keys
            ProductOrderKeys keys = new ProductOrderKeys();
            keys.setIdProduct(productOrderRequest.getIdProduct());
            keys.setIdOrder(productOrderRequest.getIdOrder());

            productOrderEntity.setKeys(keys);

            // Thiết lập các giá trị khác của productOrderEntity
            productOrderEntity.setQuanity(productOrderRequest.getQuantity());
            productOrderEntity.setPrice(productOrderRequest.getPrice());

            TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
            Calendar calendar = Calendar.getInstance(timeZone);

            productOrderEntity.setCreateDate(calendar.getTime());

            productOrderRepository.save(productOrderEntity);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false khi có lỗi
        }
    }


    @Override
    public List<ProductOrderResponse> getAllProductOrder() {
        List<ProductOrderEntity> productOrderEntities = productOrderRepository.findAll();
        List<ProductOrderResponse> productOrderResponses = new ArrayList<>();

        for (ProductOrderEntity productOrderEntity : productOrderEntities){

            ProductOrderResponse productOrderResponse = new ProductOrderResponse();

            productOrderResponse.setIdProduct(productOrderEntity.getProduct().getId());
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
