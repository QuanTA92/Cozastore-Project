package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.BlogRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BlogServiceImp {

    boolean insertBlog(String title, MultipartFile file, String content, int idUser, String nameTag) throws IOException;

}
