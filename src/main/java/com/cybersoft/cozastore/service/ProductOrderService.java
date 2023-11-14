package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.ProductOrderEntity;
import com.cybersoft.cozastore.payload.request.ProductOrderRequest;
import com.cybersoft.cozastore.payload.response.ProductOrderResponse;
import com.cybersoft.cozastore.repository.ProductOrderRepository;
import com.cybersoft.cozastore.service.imp.ProductOrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductOrderService implements ProductOrderServiceImp {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Override
    public boolean insertProductOrder(ProductOrderRequest productOrderRequest) {

        try {
            ProductOrderEntity productOrderEntity = new ProductOrderEntity();
            productOrderEntity.getProduct().setId(productOrderRequest.getIdOrder());
            productOrderEntity.getOrder().setId(productOrderRequest.getIdOrder());
            productOrderEntity.setQuanity(productOrderRequest.getQuantity());
            productOrderEntity.setPrice(productOrderRequest.getPrice());

            TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
            Calendar calendar = Calendar.getInstance(timeZone);

            // Đặt createDate thành thời gian hiện tại theo múi giờ Hà Nội
            productOrderEntity.setCreateDate(calendar.getTime());

            productOrderRepository.save(productOrderEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public List<ProductOrderResponse> getAllProductOrder() {
        List<ProductOrderEntity> productOrderEntities = productOrderRepository.findAll();
        List<ProductOrderResponse> productOrderResponses = new ArrayList<>();

        for (ProductOrderEntity productOrderEntity : productOrderEntities){

            ProductOrderResponse productOrderResponse = new ProductOrderResponse();

            productOrderResponse.setIdProduct(productOrderEntity.getProduct().getId());
            productOrderResponse.setIdOrder(productOrderEntity.getOrder().getId());
            productOrderResponse.setQuantity(productOrderEntity.getQuanity());
            productOrderResponse.setPrice(productOrderEntity.getPrice());
            productOrderResponse.setCreateDate(productOrderEntity.getCreateDate());

            productOrderResponses.add(productOrderResponse);
        }

        return productOrderResponses;
    }


}
