package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.OrderRequest;
import com.cybersoft.cozastore.payload.response.OrderResponse;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import com.cybersoft.cozastore.payload.response.UserOrderHistoryResponse;
import com.cybersoft.cozastore.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @PutMapping("/{idOrder}")
    public ResponseEntity<?> updateStatusOrderByIdOrder(@PathVariable int idOrder, @RequestParam int idStatus) throws IOException {

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdOrder(idOrder);
        orderRequest.setIdStatus(idStatus);

        try {
            orderServiceImp.updateOrderStatusByIdOrder(orderRequest);

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Order updated successfully");
            baseResponse.setStatusCode(200);
            baseResponse.setData(orderRequest);

            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error updating order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getOrderUserByIdUser(@PathVariable int idUser){

        List<UserOrderHistoryResponse> userOrderHistoryResponses = orderServiceImp.getAllOrderUserByIdUser(idUser);

        if (userOrderHistoryResponses != null) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Get order user details by ID user");
            baseResponse.setData(userOrderHistoryResponses);
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setMessage("Order user not found");
            errorResponse.setStatusCode(404);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}


