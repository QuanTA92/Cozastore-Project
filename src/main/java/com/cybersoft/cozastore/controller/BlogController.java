package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.response.BlogResponse;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogServiceImp blogServiceImp;

    @PostMapping("")
    public ResponseEntity<?> insertBlog(@RequestParam String title, @RequestParam MultipartFile file,
                                        @RequestParam String content, @RequestParam int idUser,
                                        @RequestParam String nameTag) throws IOException {

        boolean isSuccess = blogServiceImp.insertBlog(title, file, content, idUser, nameTag);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Insert new Blog");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<?> getAllBlog(){

        List<BlogResponse> blogResponses = blogServiceImp.getAllBlog();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get All Blog");
        baseResponse.setData(blogResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/{idBlog}")
    public ResponseEntity<?> getBlogById(@PathVariable int idBlog){
        List<BlogResponse> blogResponses = blogServiceImp.getBlogDetailsByIdBlog(idBlog);

        if (blogResponses != null) {

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Get product details by ID");
            baseResponse.setData(blogResponses);
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setMessage("Product not found");
            errorResponse.setStatusCode(404);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

}
