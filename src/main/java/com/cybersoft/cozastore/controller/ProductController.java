package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import com.cybersoft.cozastore.service.ProductService;
import com.cybersoft.cozastore.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        List<ProductResponse> productResponseList = productServiceImp.getAllProduct();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Get all product");
        baseResponse.setData(productResponseList);
        baseResponse.setStatusCode(200);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);    }

    @PostMapping("")
    public ResponseEntity<?> insertProduct(@RequestParam String name, @RequestParam MultipartFile file, @RequestParam String description,
                                          @RequestParam double price, @RequestParam int quantity, @RequestParam int idColor,
                                          @RequestParam int idSize, @RequestParam int idCategory
    ) throws IOException {

        boolean isSuccess = productServiceImp.insertProduct(name, file, description, price, quantity, idColor, idSize, idCategory);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Insert Product successfully");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> editProduct(){

        return new ResponseEntity<>("Hello edit", HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getProductDetailsById(@PathVariable int idProduct) {
        List<ProductResponse> productResponse = productServiceImp.getProductById(idProduct);

        if (productResponse != null) {

            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Get product details by ID");
            baseResponse.setData(productResponse);
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.setMessage("Product not found");
            errorResponse.setStatusCode(404);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> deleteProductById(@PathVariable int idProduct){

        boolean isDeleted = productServiceImp.deleteProductById(idProduct);
        if (isDeleted){
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Product deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found or unable to delete", HttpStatus.OK);
        }
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<?> updateProductById(@PathVariable int idProduct, @RequestParam String name,
                                           @RequestParam MultipartFile file, @RequestParam String description, @RequestParam double price,
                                           @RequestParam int quantity, @RequestParam int idColor, @RequestParam int idSize, @RequestParam int idCategory
    ) throws IOException {
        boolean isUpdated = productServiceImp.updateProductById(idProduct, name, file, description, price, quantity,
                idColor, idSize, idCategory);

        if (isUpdated) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Product updated successfully");
            baseResponse.setStatusCode(200);

            // Lấy thông tin sản phẩm sau cập nhật
            List<ProductResponse> updateProductById = productServiceImp.getProductById(idProduct);

            // Đặt danh sách sản phẩm sau cập nhật vào trường data
            baseResponse.setData(updateProductById);

            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found or unable to update", HttpStatus.NOT_FOUND);
        }
    }

}
