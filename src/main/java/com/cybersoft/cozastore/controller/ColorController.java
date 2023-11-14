package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.ColorRequest;
import com.cybersoft.cozastore.payload.response.ColorResponse;
import com.cybersoft.cozastore.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorServiceImp colorServiceImp;


    @GetMapping("/{idColor}")
    public ResponseEntity<?> getColorNameById(@PathVariable int idColor){

        List<ColorResponse> colorResponses = colorServiceImp.getColorNameById(idColor);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get color name ");
        baseResponse.setData(colorResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<?> insertColor(@RequestParam String nameColor) {
        ColorRequest colorRequest = new ColorRequest();
        colorRequest.setNameColor(nameColor);

        // Thực hiện thêm nameColor vào cơ sở dữ liệu ở đây
        boolean isSuccess = colorServiceImp.insertNewColor(colorRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);

        if (isSuccess) {
            baseResponse.setMessage("Add new color successfully!");
            baseResponse.setData(nameColor);
        } else {
            baseResponse.setMessage("Failed to add color");
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<?> getAllColor(){
        List<ColorResponse> colorResponses = colorServiceImp.getAllColor();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all color");
        baseResponse.setData(colorResponses);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }

}
