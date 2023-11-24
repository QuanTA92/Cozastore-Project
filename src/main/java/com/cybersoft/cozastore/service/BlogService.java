package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.*;
import com.cybersoft.cozastore.entity.keys.BlogTagKeys;
import com.cybersoft.cozastore.payload.request.BlogRequest;
import com.cybersoft.cozastore.repository.BlogRepository;
import com.cybersoft.cozastore.repository.BlogTagRepository;
import com.cybersoft.cozastore.repository.TagRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class BlogService implements BlogServiceImp {

    @Value("${root.folder}")
    private String rootFolder;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BlogTagRepository blogTagRepository;

    @Override
    public boolean insertBlog(String title, MultipartFile file, String content, int idUser, String nameTag) throws IOException {
        String pathImage = rootFolder + "\\" + file.getOriginalFilename();
        Path path = Paths.get(rootFolder);
        Path pathImageCopy = Paths.get(pathImage);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        Files.copy(file.getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);

        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setTitle(title);
        blogEntity.setImage(file.getOriginalFilename());
        blogEntity.setContent(content);

        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Date createDate = Date.from(currentDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
        blogEntity.setCreateDate(createDate);

        // Kiểm tra xem UserEntity có tồn tại không
        UserEntity userEntity = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User not found"));
        blogEntity.setIdUser(userEntity);

        // Lưu BlogEntity vào cơ sở dữ liệu
        blogRepository.save(blogEntity);

        // Kiểm tra xem TagEntity có tồn tại không
        TagEntity tagEntity = tagRepository.findByName(nameTag);
        if (tagEntity == null) {
            tagEntity = new TagEntity();
            tagEntity.setName(nameTag);
            tagEntity.setCreateDate(new Date());
            // Lưu TagEntity vào cơ sở dữ liệu
            tagRepository.save(tagEntity);
        }

        // Tạo đối tượng BlogTagEntity
        BlogTagEntity blogTagEntity = new BlogTagEntity();
        blogTagEntity.setBlogTagKeys(new BlogTagKeys(blogEntity.getId(), tagEntity.getId()));
        blogTagEntity.setCreateDate(new Date());



        // Lưu BlogTagEntity vào cơ sở dữ liệu
        blogTagRepository.save(blogTagEntity);

        return true;
    }

}
