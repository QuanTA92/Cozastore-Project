package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.BlogEntity;
import com.cybersoft.cozastore.entity.CommentEntity;
import com.cybersoft.cozastore.payload.request.CommentRequest;
import com.cybersoft.cozastore.payload.response.CommentResponse;
import com.cybersoft.cozastore.repository.CommentRepository;
import com.cybersoft.cozastore.service.imp.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService implements CommentServiceImp {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean insertComment(CommentRequest commentRequest) {

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setName(commentRequest.getName());
        commentEntity.setEmail(commentRequest.getEmail());
        commentEntity.setContent(commentRequest.getContent());

        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(commentRequest.getIdBlog());
        commentEntity.setBlog(blogEntity);

        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        Date createDate = Date.from(currentDateTime.atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());

        commentEntity.setCreateDate(createDate);

        commentRepository.save(commentEntity);

        return false;
    }

    @Override
    public List<CommentResponse> getCommentByIdBlog(int idBlog) {
        List<CommentEntity> commentEntities = commentRepository.findAll();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntities){
            if (commentEntity.getBlog().getId() == idBlog){
                CommentResponse commentResponse = new CommentResponse();

                commentResponse.setName(commentEntity.getName());
                commentResponse.setEmail(commentEntity.getEmail());
                commentResponse.setContent(commentEntity.getContent());
                commentResponse.setIdBlog(commentEntity.getBlog().getId());
                commentResponse.setCreateDate(commentEntity.getCreateDate());

                commentResponses.add(commentResponse);

            }
        }


        return commentResponses;
    }


}
