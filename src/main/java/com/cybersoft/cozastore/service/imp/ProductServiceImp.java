package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.response.CartResponse;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductServiceImp {

    boolean insertProduct(String name, MultipartFile file, String description , double price,
                          int quantity, int idColor, int idSize, int idCategory ) throws IOException;

    List<ProductResponse> getAllProduct();

    List<ProductResponse> getProductById(int idProduct);

    boolean deleteProductById(int idProduct);

    boolean updateProductById(int idProduct, String name, MultipartFile file, String description , double price,
                          int quantity, int idColor, int idSize, int idCategory ) throws IOException;

}
