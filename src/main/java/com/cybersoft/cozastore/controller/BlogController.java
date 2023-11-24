package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogServiceImp blogServiceImp;

    @PostMapping("")
    public ResponseEntity<?> insertBlog(@RequestParam String title, @RequestParam MultipartFile file, @RequestParam String content, @RequestParam int idUser, @RequestParam String nameTag) throws IOException {

        boolean isSuccess = blogServiceImp.insertBlog(title, file, content, idUser, nameTag);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Insert new product");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }


}
