package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.*;
import com.cybersoft.cozastore.payload.response.ProductDetailsResponse;
import com.cybersoft.cozastore.payload.response.ProductResponse;
import com.cybersoft.cozastore.payload.response.SizeResponse;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.repository.SizeRepository;
import com.cybersoft.cozastore.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceImp {

    @Value("${root.folder}")
    private String rootFolder;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public boolean insertProduct(String name, MultipartFile file, String description , double price, int quantity, int idColor, int idSize, int idCategory) throws IOException {

        String pathImage = rootFolder + "\\" + file.getOriginalFilename();


        Path path = Paths.get(rootFolder);
        Path pathImageCopy = Paths.get(pathImage);

        if(!Files.exists(path)){
            Files.createDirectory(path);
        }

        Files.copy(file.getInputStream(),pathImageCopy, StandardCopyOption.REPLACE_EXISTING);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);


        productEntity.setImage(file.getOriginalFilename());
        productEntity.setDescription(description);
        productEntity.setPrice(price);
        productEntity.setQuanity(quantity);

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(idColor);
        productEntity.setColor(colorEntity);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(idSize);
        productEntity.setSize(sizeEntity);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(idCategory);
        productEntity.setCategory(categoryEntity);

        productRepository.save(productEntity);

        return false;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            ProductResponse productResponse = new ProductResponse();

            productResponse.setIdProduct(productEntity.getId());

            productResponse.setNameProduct(productEntity.getName());
            productResponse.setQuantity(productEntity.getQuanity());
            productResponse.setImage(productEntity.getImage());
            productResponse.setPrice(productEntity.getPrice());
            productResponse.setDescription(productEntity.getDescription());
            productResponse.setIdSize(productEntity.getSize().getId());
            productResponse.setIdColor(productEntity.getColor().getId());

            productResponse.setIdCategory(productEntity.getCategory().getId());
            productResponses.add(productResponse);
        }

        return productResponses;
    }




    public String getColorNameById(int idColor) {
        ColorEntity colorEntity = colorRepository.findById(idColor).orElse(null);
        if (colorEntity != null) {
            return colorEntity.getName(); // Lấy tên của idColor
        }
        return null;
    }

    @Override
    public List<ProductResponse> getProductById(int idProduct) {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            if (productEntity.getId() == idProduct) {
                ProductResponse productResponse = new ProductResponse();

                productResponse.setIdProduct(productEntity.getId());
                productResponse.setNameProduct(productEntity.getName());
                productResponse.setImage(productEntity.getImage());
                productResponse.setPrice(productEntity.getPrice());
                productResponse.setQuantity(productEntity.getQuanity());
                productResponse.setDescription(productEntity.getDescription());
                productResponse.setIdSize(productEntity.getSize().getId());
                productResponse.setIdColor(productEntity.getColor().getId());
                productResponse.setIdCategory(productEntity.getCategory().getId());

                productResponses.add(productResponse);
            }
        }

        return productResponses;
    }

    @Override
    public boolean deleteProductById(int idProduct) {
        if (productRepository.existsById(idProduct)) {

            productRepository.deleteById(idProduct);

            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean updateProductById(int idProduct, String name, MultipartFile file, String description, double price, int quantity, int idColor, int idSize, int idCategory) throws IOException {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(idProduct);

        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();

            // Xoá ảnh cũ
            String oldImage = productEntity.getImage();
            if (oldImage != null) {
                Files.deleteIfExists(Paths.get(rootFolder, oldImage));
            }

            // Lưu ảnh mới
            String newImage = file.getOriginalFilename();
            Path newPathImageCopy = Paths.get(rootFolder, newImage);
            Files.copy(file.getInputStream(), newPathImageCopy, StandardCopyOption.REPLACE_EXISTING);

            // Cập nhật thông tin sản phẩm
            productEntity.setName(name);
            productEntity.setImage(newImage);
            productEntity.setDescription(description);
            productEntity.setPrice(price);
            productEntity.setQuanity(quantity);

            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setId(idColor);
            productEntity.setColor(colorEntity);

            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setId(idSize);
            productEntity.setSize(sizeEntity);

            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(idCategory);
            productEntity.setCategory(categoryEntity);

            productRepository.save(productEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ProductDetailsResponse> getProductDetails(int idSize, int idColor) {
        List<SizeEntity> sizeEntities = sizeRepository.findAll();
        List<ColorEntity> colorEntities = colorRepository.findAll();
        List<ProductDetailsResponse> productDetailsResponses = new ArrayList<>();

        for (SizeEntity sizeEntity : sizeEntities) {
            for (ColorEntity colorEntity : colorEntities) {
                if (sizeEntity.getId() == idSize && colorEntity.getId() == idColor) {
                    ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();

                    productDetailsResponse.setIdSize(sizeEntity.getId());
                    productDetailsResponse.setNameSize(sizeEntity.getName());

                    productDetailsResponse.setIdColor(colorEntity.getId());
                    productDetailsResponse.setNameColor(colorEntity.getName());

                    productDetailsResponses.add(productDetailsResponse);
                }
            }
        }

        return productDetailsResponses;
    }



}
