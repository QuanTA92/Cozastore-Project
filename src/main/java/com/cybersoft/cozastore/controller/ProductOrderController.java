package com.cybersoft.cozastore.controller;


import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.ProductOrderRequest;
import com.cybersoft.cozastore.payload.response.ProductOrderResponse;
import com.cybersoft.cozastore.service.imp.ProductOrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
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
    public ResponseEntity<?> insertProductOrder(
            @RequestParam int idCart,
            @RequestParam int idProduct,
            @RequestParam int quantity,
            @RequestParam double price,
            @RequestParam int idUser,
            @RequestParam int idStatus) {

        try {
            ProductOrderRequest productOrderRequest = new ProductOrderRequest();
            productOrderRequest.setIdCart(idCart);
            productOrderRequest.setIdProduct(idProduct);
            productOrderRequest.setQuantity(quantity);
            productOrderRequest.setPrice(price);
            productOrderRequest.setIdUser(idUser);
            productOrderRequest.setIdStatus(idStatus);

            boolean isSuccess = productOrderServiceImp.insertProductOrder(productOrderRequest);

            BaseResponse baseResponse = new BaseResponse();

            if (isSuccess) {
                baseResponse.setStatusCode(200);
                baseResponse.setMessage("Insert product order successful");
                baseResponse.setData(productOrderRequest);
                return new ResponseEntity<>(baseResponse, HttpStatus.OK);
            } else {
                baseResponse.setStatusCode(500);
                baseResponse.setMessage("Insert product order failed");
                baseResponse.setError("Details of the error");
                return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Log lỗi để kiểm tra nguyên nhân của lỗi
            e.printStackTrace();

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(500);
            baseResponse.setMessage("Insert product order failed. Check server logs for details.");
            baseResponse.setError(e.getMessage());
            return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
