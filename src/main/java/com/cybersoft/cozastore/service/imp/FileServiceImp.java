package com.cybersoft.cozastore.service.imp;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileServiceImp {

    String uploadFile(MultipartFile file) throws IOException;
    // upload file lên sẽ là bỏ file hoặc không bỏ file nếu nó không chạy nữa thì nó throws exeception

    byte[] downloadFile(String fileName) throws IOException;


}
