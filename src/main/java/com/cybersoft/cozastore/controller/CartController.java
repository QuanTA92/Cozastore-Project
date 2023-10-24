package com.cybersoft.cozaStore.controller;

import com.cybersoft.cozaStore.payload.request.CartRequest;
import com.cybersoft.cozaStore.payload.response.BaseResponse;
import com.cybersoft.cozaStore.payload.response.CartResponse;
import com.cybersoft.cozaStore.service.CartService;
import com.cybersoft.cozaStore.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartServiceImp cartServiceImp;
    @GetMapping("/{idUser}")
    public ResponseEntity<?> getCarts(@PathVariable int idUser){

        List<CartResponse> cartResponseList = cartServiceImp.getCart(idUser);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Get cart info by user id");
        baseResponse.setData(cartResponseList);
        baseResponse.setStatusCode(200);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> insertProductIntoCart(@RequestBody CartRequest cartRequest){
        boolean isSuccess = cartServiceImp.insertProductIntoCart(cartRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(isSuccess ? "Insert Successfully" : "Insert Failed");
        baseResponse.setMessage("Insert Product Into Cart");
        baseResponse.setStatusCode(200);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
