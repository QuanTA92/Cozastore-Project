package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.request.CategoryRequest;
import com.cybersoft.cozastore.payload.response.CategoryResponse;
import com.cybersoft.cozastore.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getCategory(){

        List<CategoryResponse> list = categoryServiceImp.getAllCategory();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all category");
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertCategory(@RequestParam String nameCategory){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setNameCategory(nameCategory);

        boolean isSuccess = categoryServiceImp.insertCategory(categoryRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);

        if (isSuccess){
            baseResponse.setMessage("Add new size successfully");
            baseResponse.setData(nameCategory);
        } else {
            baseResponse.setMessage("Failed to add category");
        }

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
