package com.cybersoft.cozastore.service.imp;

import com.cybersoft.cozastore.payload.request.CommentRequest;
import com.cybersoft.cozastore.payload.response.CommentResponse;

import java.util.List;

public interface CommentServiceImp {

    boolean insertComment(CommentRequest commentRequest);

    List<CommentResponse> getCommentByIdBlog(int idBlog);


}
