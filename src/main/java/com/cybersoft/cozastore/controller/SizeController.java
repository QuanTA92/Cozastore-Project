package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.SizeRequest;
import com.cybersoft.cozastore.payload.response.SizeResponse;
import com.cybersoft.cozastore.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    private SizeServiceImp sizeServiceImp;

    @GetMapping("/{idSize}")
    public ResponseEntity<?> getSizeName(@PathVariable int idSize) {
        List<SizeResponse> sizeResponses = sizeServiceImp.getSizeNameById(idSize);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get size by id");
        baseResponse.setData(sizeResponses);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<?> insertSize(@RequestParam String nameSize) {
        SizeRequest sizeRequest = new SizeRequest();
        sizeRequest.setNameSize(nameSize);

        // Thực hiện thêm nameSize vào cơ sở dữ liệu ở đây
        boolean isSuccess = sizeServiceImp.insertSize(sizeRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);

        if (isSuccess) {
            baseResponse.setMessage("Add new size successfully!");
            baseResponse.setData(nameSize);
        } else {
            baseResponse.setMessage("Failed to add size");
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllSize(){
        List<SizeResponse> sizeResponses = sizeServiceImp.getAllSize();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all size");
        baseResponse.setData(sizeResponses);

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }
}
