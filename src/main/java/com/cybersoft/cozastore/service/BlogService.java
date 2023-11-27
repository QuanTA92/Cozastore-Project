package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.*;
import com.cybersoft.cozastore.entity.keys.BlogTagKeys;
import com.cybersoft.cozastore.payload.response.BlogResponse;
import com.cybersoft.cozastore.repository.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private CommentRepository commentRepository;

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

    @Override
    public List<BlogResponse> getAllBlog() {

        List<BlogEntity> blogEntities = blogRepository.findAll();

        List<BlogResponse> blogResponses = new ArrayList<>();
        for (BlogEntity blogEntity : blogEntities) {
            BlogResponse blogResponse = new BlogResponse();
            List<String> nameTags = new ArrayList<>();

            // Lấy danh sách các BlogTagEntity liên quan đến BlogEntity
            List<BlogTagEntity> blogTagEntities = blogTagRepository.findByBlog(blogEntity);

            for (BlogTagEntity blogTagEntity : blogTagEntities) {
                // Lấy thông tin TagEntity từ BlogTagEntity
                TagEntity tagEntity = blogTagEntity.getTag();
                if (tagEntity != null) {
                    // Thêm tên tag vào danh sách
                    nameTags.add(tagEntity.getName());
                }
            }

            // Thiết lập thông tin cho BlogResponse
            blogResponse.setIdBlog(blogEntity.getId());
            blogResponse.setTitle(blogEntity.getTitle());
            blogResponse.setImage(blogEntity.getImage());
            blogResponse.setContent(blogEntity.getContent());
            blogResponse.setNameUser(blogEntity.getIdUser().getUsername());
            blogResponse.setCreateDate(String.valueOf(blogEntity.getCreateDate()));
            blogResponse.setNameTag(nameTags.toString()); // Đặt danh sách nameTags vào BlogResponse
            blogResponses.add(blogResponse);
        }

        return blogResponses;
    }

    @Override
    public List<BlogResponse> getBlogDetailsByIdBlog(int idBlog) {

        List<BlogEntity> blogEntities = blogRepository.findAll();

        List<BlogResponse> blogResponses = new ArrayList<>();
        for (BlogEntity blogEntity : blogEntities) {
            if (blogEntity.getId() == idBlog) {
                BlogResponse blogResponse = new BlogResponse();
                List<String> nameTags = new ArrayList<>();

                // Lấy danh sách các BlogTagEntity liên quan đến BlogEntity
                List<BlogTagEntity> blogTagEntities = blogTagRepository.findByBlog(blogEntity);

                for (BlogTagEntity blogTagEntity : blogTagEntities) {
                    // Lấy thông tin TagEntity từ BlogTagEntity
                    TagEntity tagEntity = blogTagEntity.getTag();
                    if (tagEntity != null) {
                        // Thêm tên tag vào danh sách
                        nameTags.add(tagEntity.getName());
                    }
                }

                // Thiết lập thông tin cho BlogResponse
                blogResponse.setIdBlog(blogEntity.getId());
                blogResponse.setTitle(blogEntity.getTitle());
                blogResponse.setImage(blogEntity.getImage());
                blogResponse.setContent(blogEntity.getContent());
                blogResponse.setNameUser(blogEntity.getIdUser().getUsername());
                blogResponse.setCreateDate(String.valueOf(blogEntity.getCreateDate()));
                blogResponse.setNameTag(nameTags.toString());
                blogResponses.add(blogResponse);
            }
        }

        return blogResponses;
    }


}
