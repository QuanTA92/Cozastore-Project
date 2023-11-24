package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.SizeRequest;
import com.cybersoft.cozastore.payload.request.StatusRequest;
import com.cybersoft.cozastore.payload.response.StatusResponse;
import com.cybersoft.cozastore.service.imp.StatusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusServiceImp statusServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllStatus(){
        List<StatusResponse> statusResponses = statusServiceImp.getAllStatus();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all status");
        baseResponse.setData(statusResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertStatus(@RequestBody StatusRequest statusRequest){
        boolean isSuccess = statusServiceImp.insertStatus(statusRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Insert status product order");
        baseResponse.setData(isSuccess ? "Insert Successfully" : "Insert Failed");

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{idStatus}")
    public ResponseEntity<?> deleteStatusById(@PathVariable int idStatus){

        boolean isDeleted = statusServiceImp.deleteStatusById(idStatus);
        if (isDeleted){
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Product deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found or unable to delete", HttpStatus.OK);
        }

    }

}
