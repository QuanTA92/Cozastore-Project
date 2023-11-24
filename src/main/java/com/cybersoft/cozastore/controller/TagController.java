package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.response.TagResponse;
import com.cybersoft.cozastore.service.imp.TagServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagServiceImp tagServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllTag(){
        List<TagResponse> tagResponses = tagServiceImp.getAllTag();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all status");
        baseResponse.setData(tagResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
