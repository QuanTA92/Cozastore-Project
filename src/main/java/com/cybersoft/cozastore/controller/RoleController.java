package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.response.RoleResponse;
import com.cybersoft.cozastore.service.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImp roleServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllRole(){
        List<RoleResponse> roleResponses = roleServiceImp.getAllRole();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all role");
        baseResponse.setData(roleResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
