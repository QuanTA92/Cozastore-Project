package com.cybersoft.cozastore.controller;


import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.ProductOrderRequest;
import com.cybersoft.cozastore.payload.response.ProductOrderResponse;
import com.cybersoft.cozastore.service.imp.ProductOrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-order")
public class ProductOrderController {

    @Autowired
    private ProductOrderServiceImp productOrderServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllProductOrder(){
        List<ProductOrderResponse> productOrderResponses = productOrderServiceImp.getAllProductOrder();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get product order");
        baseResponse.setData(productOrderResponses);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertProductOrder(@RequestBody ProductOrderRequest productOrderRequest){
        boolean isSuccess = productOrderServiceImp.insertProductOrder(productOrderRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Insert product order");
        baseResponse.setData(isSuccess ? "Insert Successfully" : "Insert Failed");

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
