package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CarouselEntity;
import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.payload.request.CarouselRequest;
import com.cybersoft.cozastore.repository.CarouselRepository;
import com.cybersoft.cozastore.repository.CategoryRepository;
import com.cybersoft.cozastore.service.imp.CarouselServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CarouselService implements CarouselServiceImp {

    @Value("${root.folder}")
    private String rootFolder;

    @Autowired
    private CarouselRepository carouselRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean insertCarousel(CarouselRequest carouselRequest) throws IOException {

        String pathImage = rootFolder + "\\" + carouselRequest.getImage().getOriginalFilename();
        Path path = Paths.get(rootFolder);
        Path pathImageCopy = Paths.get(pathImage);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        Files.copy(carouselRequest.getImage().getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);

        CarouselEntity carouselEntity = new CarouselEntity();
        carouselEntity.setTitle(carouselRequest.getTitle());
        carouselEntity.setImage(carouselRequest.getImage().getOriginalFilename());
        carouselEntity.setContent(carouselRequest.getContent());


        CategoryEntity categoryEntity = categoryRepository.findById(carouselRequest.getIdCategory()).orElseGet(CategoryEntity::new);
        categoryEntity.setId(carouselRequest.getIdCategory());

        carouselEntity.setCategoryEntity(categoryEntity);


        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Date createDate = Date.from(currentDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
        carouselEntity.setCreateDate(createDate);


        carouselRepository.save(carouselEntity);

        return false;
    }
}
