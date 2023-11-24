package com.cybersoft.cozastore.controller;


import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.CarouselRequest;
import com.cybersoft.cozastore.service.imp.CarouselServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselServiceImp carouselServiceImp;

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertCarousel(@RequestParam String title, @RequestParam MultipartFile image, @RequestParam String content, @RequestParam int idCategory) {
        try {
            CarouselRequest carouselRequest = new CarouselRequest();
            carouselRequest.setTitle(title);
            carouselRequest.setImage(image);
            carouselRequest.setContent(content);
            carouselRequest.setIdCategory(idCategory);

            boolean isSuccess = carouselServiceImp.insertCarousel(carouselRequest);

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(200);

            return new ResponseEntity<>(baseResponse, HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
