package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.response.BlogResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogServiceImp {

    boolean insertBlog(String title, MultipartFile file, String content, int idUser, String nameTag) throws IOException;

    List<BlogResponse> getAllBlog();

    List<BlogResponse> getBlogDetailsByIdBlog(int idBlog);

}
